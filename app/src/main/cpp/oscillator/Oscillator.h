//
// Created by Go Shibata on 2022/05/07.
//

#ifndef MUS_OSCILLATOR_H
#define MUS_OSCILLATOR_H

#include <thread>
#include <oboe/Oboe.h>
#include "OscillatorRenderer.h"

class Oscillator : public oboe::AudioStreamDataCallback, oboe::AudioStreamErrorCallback {
public:
    void setup();

    void setNoteNumber(int32_t noteNumber);

    void setA4Frequency(int32_t a4Frequency);

    void play();

    void stop();

    oboe::DataCallbackResult onAudioReady(oboe::AudioStream *audioStream, void *audioData, int32_t numFrames) override;

    void onErrorAfterClose(oboe::AudioStream *audioStream, oboe::Result error) override;

private:
    std::unique_ptr<std::thread> thread;
    std::mutex lock;
    std::unique_ptr<OscillatorRenderer> renderer;
    std::shared_ptr<oboe::AudioStream> stream;

    bool openStream();

    void innerPlay();

    void innerStop();

    inline static int constexpr CHANNEL_COUNT = oboe::ChannelCount::Mono;
};


#endif //MUS_OSCILLATOR_H
