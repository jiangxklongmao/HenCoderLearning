package com.jiangxk.lesson.entity

/**
 * @description com.jiangxk.lesson.entity
 * @author jiangxk
 * @time 2020-05-13  22:53
 */

data class Lesson(val date: String?, val content: String, val state: State?)

enum class State {
    PLAYBACK {
        override fun stateName(): String {
            return "有回放"
        }
    },
    LIVE {
        override fun stateName(): String {
            return "正在直播"
        }
    },
    WAIT {
        override fun stateName(): String {
            return "等待中"
        }
    };

    abstract fun stateName(): String
}