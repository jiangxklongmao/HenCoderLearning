package com.jiangxk.hencoderlearningview.activity.motionLayout

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.jiangxk.hencoderlearningview.R

/**
 * @description com.jiangxk.hencoderlearningview.activity.motionLayout
 * @author jiangxk
 * @time 2020/7/11  12:59 PM
 */
class TransitionActivity : AppCompatActivity() {

    private var toggle = true

    private lateinit var root: ConstraintLayout
    private lateinit var ratingFilmRating: RatingBar
    private lateinit var textFilmTitle: TextView
    private lateinit var textFilmDescription: TextView
    private lateinit var imageFilmCover: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        bindData()
    }


    private fun bindData() {

        root = findViewById(R.id.root)
        ratingFilmRating = findViewById(R.id.rating_film_rating)
        textFilmTitle = findViewById(R.id.text_film_title)
        textFilmDescription = findViewById(R.id.text_film_description)
        imageFilmCover = findViewById(R.id.image_film_cover)

        ratingFilmRating.rating = 4.5f
        textFilmTitle.text = getString(R.string.film_title)
        textFilmDescription.text = getString(R.string.film_description)

        imageFilmCover.setOnClickListener {

            val startScene = Scene.getSceneForLayout(root, R.layout.include_go_start, this)
            val endScene = Scene.getSceneForLayout(root, R.layout.include_go_end, this)

            if (toggle) {
                TransitionManager.go(endScene)
            } else {
                TransitionManager.go(startScene)
            }
            toggle = !toggle
            bindData()
        }
    }


}