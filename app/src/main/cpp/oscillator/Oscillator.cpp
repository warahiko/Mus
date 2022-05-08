//
// Created by Go Shibata on 2022/05/07.
//

#include <thread>
#include <mutex>
#include "Oscillator.h"
#include "../util/logging.h"

void Oscillator::setup() {
    openStream();
    this->renderer = std::make_unique<OscillatorRenderer>(
            CHANNEL_COUNT,
            stream->getSampleRate()
    );
}

void Oscillator::setNoteNumber(int32_t noteNumber) {
    renderer->setNoteNumber(noteNumber);
}

void Oscillator::setA4Frequency(int32_t a4Frequency) {
    renderer->setA4Frequency(a4Frequency);
}

void Oscillator::play() {
    std::lock_guard<std::mutex> localLock(lock);
    if (!stream) {
        openStream();
    }
    innerPlay();
}

void Oscillator::stop() {
    renderer->stop();
}

void Oscillator::innerStop() {
    std::lock_guard<std::mutex> localLock(lock);
    if (stream) {
        stream->stop();
        stream->close();
        stream.reset();
        renderer->reset();
    }
}

oboe::DataCallbackResult Oscillator::onAudioReady(
        oboe::AudioStream *audioStream,
        void *audioData,
        int32_t numFrames) {
    auto *outputBuffer = static_cast<float *>(audioData);
    auto result = renderer->render(outputBuffer, numFrames);
    if (result == OscillatorRenderer::Result::STOPPED) {
        if (thread && thread->joinable()) {
            thread->join();
            thread.reset();
        }
        thread = std::make_unique<std::thread>([this] {
            innerStop();
        });
    }
    return oboe::DataCallbackResult::Continue;
}

void Oscillator::onErrorAfterClose(oboe::AudioStream *audioStream, oboe::Result error) {
}

bool Oscillator::openStream() {
    oboe::AudioStreamBuilder builder;
    builder.setFormat(oboe::AudioFormat::Float)
            ->setFormatConversionAllowed(true)
            ->setPerformanceMode(oboe::PerformanceMode::LowLatency)
            ->setSharingMode(oboe::SharingMode::Exclusive)
            ->setSampleRateConversionQuality(oboe::SampleRateConversionQuality::Medium)
            ->setChannelCount(CHANNEL_COUNT)
            ->setDataCallback(this)
            ->setErrorCallback(this);

    oboe::Result result = builder.openStream(stream);
    if (result != oboe::Result::OK) {
        LOGE("Failed to open stream. Error: %s", oboe::convertToText(result));
        return false;
    }

    stream->setBufferSizeInFrames(
            stream->getFramesPerBurst() * 2
    );

    return true;
}

void Oscillator::innerPlay() {
    oboe::Result result = stream->requestStart();
    if (result != oboe::Result::OK) {
        LOGE("Failed to start stream. Error: %s", oboe::convertToText(result));
    }
}
