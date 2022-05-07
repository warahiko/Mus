#include <jni.h>
#include <string>
#include "util/logging.h"

extern "C" {
JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_setNoteNumber(
        JNIEnv *env,
        jobject thiz,
        jint note_number) {
    LOGD("setNoteNumber: %d", note_number);
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_setA4Frequency(
        JNIEnv *env,
        jobject thiz,
        jint a4_frequency) {
    LOGD("setA4Frequency: %d", a4_frequency);
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_play(
        JNIEnv *env,
        jobject thiz) {
    LOGD("play");
}

JNIEXPORT void JNICALL
Java_io_github_warahiko_mus_ui_tuner_oscillator_Oscillator_stop(
        JNIEnv *env,
        jobject thiz) {
    LOGD("stop");
}
}
