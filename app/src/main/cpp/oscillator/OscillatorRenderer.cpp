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

void OscillatorRenderer::render(
        float *audioData,
        int32_t numFrames
) {
    for (int i = 0; i < numFrames; ++i) {
        auto sampledValue = (float) (sin(phase) * amplitude);
        for (int j = 0; j < channelCount; ++j) {
            audioData[i * channelCount + j] = sampledValue;
        }

        phase += phaseIncrement;
        if (phase > M_PI * 2) phase -= M_PI * 2;
    }
}
