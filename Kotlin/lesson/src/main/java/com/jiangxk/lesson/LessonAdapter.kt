package com.jiangxk.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jiangxk.base.BaseViewHolder
import com.jiangxk.lesson.entity.Lesson
import com.jiangxk.lesson.entity.State

/**
 * @description com.jiangxk.lesson
 * @author jiangxk
 * @time 2020-05-13  23:40
 */
class LessonAdapter : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {
    private var list = listOf<Lesson>()

    fun updateAndNotify(list: List<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(parent).onCreate(parent)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }


    inner class LessonViewHolder(itemVIew: View) : BaseViewHolder(itemVIew) {
        fun onCreate(parent: ViewGroup): LessonViewHolder {
            return LessonViewHolder(
                LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_lesson, parent, false)
            )
        }

        fun onBind(lesson: Lesson) {
            setText(R.id.tv_date, lesson.date ?: "日期待定")
            setText(R.id.tv_content, lesson.content)

            lesson.state?.let {
                setText(R.id.tv_state, it.stateName())
                val colorRes = when (it) {
                    State.PLAYBACK -> {
                        R.color.playback
                    }
                    State.LIVE -> R.color.live
                    State.WAIT -> R.color.wait
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }

    }

}