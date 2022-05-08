//
// Created by Go Shibata on 2022/05/07.
//

#include "OscillatorRenderer.h"
#include <cmath>

OscillatorRenderer::OscillatorRenderer(
        int32_t channelCount,
        int32_t sampleRate,
        int32_t noteNumber,
        int32_t a4Frequency,
        float amplitude
) {
    this->channelCount = channelCount;
    this->sampleRate = sampleRate;
    this->noteNumber = noteNumber;
    this->a4Frequency = a4Frequency;
    this->amplitude = amplitude;
    setPhaseIncrement();
}

void OscillatorRenderer::setPhaseIncrement() {
    double frequency = pow(2.0, ((noteNumber - A4_NOTE_NUMBER) / 12.0)) * a4Frequency;
    this->phaseIncrement = M_PI * 2 * frequency / sampleRate;
}

void OscillatorRenderer::setNoteNumber(int32_t noteNumber) {
    this->noteNumber = noteNumber;
    setPhaseIncrement();
}

void OscillatorRenderer::setA4Frequency(int32_t a4Frequency) {
    this->a4Frequency = a4Frequency;
    setPhaseIncrement();
}

OscillatorRenderer::Result OscillatorRenderer::render(
        float *audioData,
        int32_t numFrames
) {
    for (int i = 0; i < numFrames; ++i) {
        float sampledValue;
        if (isStopped) {
            sampledValue = 0.0;
        } else {
            sampledValue = (float) (sin(phase) * amplitude);
        }
        for (int j = 0; j < channelCount; ++j) {
            audioData[i * channelCount + j] = sampledValue;
        }

        phase += phaseIncrement;
        if (phase > M_PI * 2) phase -= M_PI * 2;
    }
    if (isStopping) {
        applyHannWindow(audioData, numFrames);
        isStopping = false;
        isStopped = true;
        return OscillatorRenderer::Result::STOPPED;
    } else {
        return OscillatorRenderer::Result::CONTINUED;
    }
}

void OscillatorRenderer::stop() {
    isStopping = true;
}

void OscillatorRenderer::reset() {
    phase = 0.0;
    isStopping = false;
    isStopped = false;
}

void OscillatorRenderer::applyHannWindow(float *audioData, int32_t numFrames) {
    float phaseIncrement = M_PI / numFrames;
    float phase = M_PI + phaseIncrement;
    for (int frame = 0; frame < numFrames; ++frame) {
        auto value = (float) (0.5 * (1 - cos(phase)));
        for (int j = 0; j < channelCount; ++j) {
            audioData[frame * channelCount + j] = audioData[frame * channelCount + j] * value;
        }

        phase += phaseIncrement;
        if (phase > M_PI * 2) phase -= M_PI * 2;
    }
}
