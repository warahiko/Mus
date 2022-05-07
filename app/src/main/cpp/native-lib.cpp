#include <jni.h>
#include <string>
#include "oscillator/Oscillator.h"

extern "C" {

std::unique_ptr<Oscillator> oscillator;

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_setup(
        JNIEnv *env,
        jobject thiz) {
    if (!oscillator) {
        oscillator = std::make_unique<Oscillator>();
        oscillator->setup();
    }
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_dispose(
        JNIEnv *env,
        jobject thiz) {
    oscillator->stop();
    oscillator.reset();
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_setNoteNumber(
        JNIEnv *env,
        jobject thiz,
        jint note_number) {
    oscillator->setNoteNumber(note_number);
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_setA4Frequency(
        JNIEnv *env,
        jobject thiz,
        jint a4_frequency) {
    oscillator->setA4Frequency(a4_frequency);
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_play(
        JNIEnv *env,
        jobject thiz) {
    oscillator->play();
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_stop(
        JNIEnv *env,
        jobject thiz) {
    oscillator->stop();
}
}
