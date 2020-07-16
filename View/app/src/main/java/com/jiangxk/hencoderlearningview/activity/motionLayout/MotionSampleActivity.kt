package com.jiangxk.hencoderlearningview.activity.motionLayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_motion_sample.*

/**
 * @description com.jiangxk.hencoderlearningview.activity.motionLayout
 * @author jiangxk
 * @time 2020/7/11  12:41 PM
 */
class MotionSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_sample)

        rating_film_rating.rating = 4.5f
        text_film_title.text = getString(R.string.film_title)
        text_film_description.text = getString(R.string.film_description)
    }
}