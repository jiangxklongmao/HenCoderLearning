package com.jiangxk.lesson

import com.google.gson.reflect.TypeToken
import com.jiangxk.base.http.EntityCallback
import com.jiangxk.base.http.HttpClient
import com.jiangxk.base.utils.Utils
import com.jiangxk.lesson.entity.Lesson
import com.jiangxk.lesson.entity.State

/**
 * @description com.jiangxk.lesson
 * @author jiangxk
 * @time 2020-05-13  23:06
 */
class LessonPresenter(private val activity: LessonActivity) {
    companion object {
        const val LESSON_PATH = "lessons"
    }

    private var lessons = listOf<Lesson>()

    private val type = object : TypeToken<List<Lesson>>() {}.type


    fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                lessons = entity
                activity.runOnUiThread {
                    activity.showResult(lessons as MutableList<Lesson>)
                }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread {
                    Utils.toast(message)
                }
            }
        })
    }

    fun showPlayback() {
        val playbackLessons = mutableListOf<Lesson>()
        for (lesson in lessons) {
            if (lesson.state == State.PLAYBACK) {
                playbackLessons.add(lesson)
            }
        }
        activity.showResult(playbackLessons)
    }
}