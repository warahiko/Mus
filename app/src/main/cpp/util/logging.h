//
// Created by Go Shibata on 2022/05/07.
//

#ifndef MUS_LOGGING_H
#define MUS_LOGGING_H

#include <android/log.h>

#define TAG "mus Native"
#define LOGD(...) ((void)__android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__))

#endif //MUS_LOGGING_H
