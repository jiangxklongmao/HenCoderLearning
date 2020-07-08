package com.jiangxk.hencoderlearningview.activity.constraintLayout

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.transition.TransitionSet
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_constraint_start.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.constraintLayout
 * @author jiangxk
 * @time 2020/7/8  10:28 PM
 */
class ConstraintSetXActivity : AppCompatActivity() {

    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_start)

        constraint_layout.setOnClickListener { if (isStart) startToEnd() else endToStart() }

    }


    private fun startToEnd() {
        isStart = false
        val constraintSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstraintSetXActivity, R.layout.activity_constraint_end)
        }

        TransitionManager.beginDelayedTransition(constraint_layout)
        constraintSet.applyTo(constraint_layout)
    }

    private fun endToStart() {
        isStart = true
        val constraintSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstraintSetXActivity, R.layout.activity_constraint_start)
        }

        TransitionManager.beginDelayedTransition(
            constraint_layout,
            TransitionSet()
                .addTransition(AutoTransition())
                .setDuration(1000)
        )
        constraintSet.applyTo(constraint_layout)
    }

}