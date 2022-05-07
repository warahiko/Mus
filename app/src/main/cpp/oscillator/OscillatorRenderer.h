//
// Created by Go Shibata on 2022/05/07.
//

#ifndef MUS_OSCILLATORRENDERER_H
#define MUS_OSCILLATORRENDERER_H

#include <cstdint>

class OscillatorRenderer {
public:
    OscillatorRenderer(
            int32_t channelCount,
            int32_t sampleRate = DEFAULT_SAMPLE_RATE,
            int32_t noteNumber = A4_NOTE_NUMBER,
            int32_t a4Frequency = DEFAULT_A4_FREQUENCY,
            float amplitude = DEFAULT_AMPLITUDE
    );

    void setNoteNumber(int32_t noteNumber);

    void setA4Frequency(int32_t a4Frequency);

    void render(
            float *audioData,
            int32_t numFrames
    );

private:
    int32_t noteNumber;
    int32_t a4Frequency;
    int32_t channelCount;
    int32_t sampleRate;
    float amplitude;

    double phase = 0.0;
    double phaseIncrement = 0.0;

    void setPhaseIncrement();

    static int32_t constexpr DEFAULT_A4_FREQUENCY = 440;
    static int32_t constexpr DEFAULT_SAMPLE_RATE = 48000;
    static float constexpr DEFAULT_AMPLITUDE = 0.5f;
    static int32_t constexpr A4_NOTE_NUMBER = 69;
};


#endif //MUS_OSCILLATORRENDERER_H
