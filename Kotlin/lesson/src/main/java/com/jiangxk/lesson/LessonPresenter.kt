package com.jiangxk.lesson

import com.google.gson.reflect.TypeToken
import com.jiangxk.base.http.EntityCallback
import com.jiangxk.base.http.HttpClient
import com.jiangxk.base.utils.toast
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
                    activity.showResult(lessons)
                }
            }

            override fun onFailure(message: String?) {
                activity.runOnUiThread {
                    activity.toast(message)
                }
            }
        })
    }

    fun showPlayback() =
        activity.showResult(lessons.filter { it.state == State.PLAYBACK })
}