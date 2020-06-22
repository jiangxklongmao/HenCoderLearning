package com.jiangxk.hencoderlearningview.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.activity.animation.AnimationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dashBoard = findViewById<Button>(R.id.btn_dash_board)
        val pie = findViewById<Button>(R.id.btn_pie)
        val circleAvatar = findViewById<Button>(R.id.btn_circle_avatar)
        val circleProgress = findViewById<Button>(R.id.btn_circle_progress)
        val btnTextWrap = findViewById<Button>(R.id.btn_text_wrap)
        val btnGeometricTransformation = findViewById<Button>(R.id.btn_geometric_transformation)
        val btnAnimationSet = findViewById<Button>(R.id.btn_animation_set)
        val btnMaterialEditText = findViewById<Button>(R.id.btn_material_edit_text)
        val btnLabelLayout = findViewById<Button>(R.id.btn_label_layout)
        val btnScalableImageView = findViewById<Button>(R.id.btn_scalable_image_view)

        dashBoard.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
        }

        pie.setOnClickListener {
            startActivity(Intent(this, PieActivity::class.java))
        }

        circleAvatar.setOnClickListener {
            startActivity(Intent(this, CircleAvatarActivity::class.java))
        }

        circleProgress.setOnClickListener {
            startActivity(Intent(this, ProgressActivity::class.java))
        }

        btnTextWrap.setOnClickListener {
            startActivity(Intent(this, TextWrapActivity::class.java))
        }

        btnGeometricTransformation.setOnClickListener {
            startActivity(Intent(this, GeometricTransformationActivity::class.java))
        }

        btnAnimationSet.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }

        btnMaterialEditText.setOnClickListener {
            startActivity(Intent(this, MaterialEditTextActivity::class.java))
        }

        btnLabelLayout.setOnClickListener {
            startActivity(Intent(this, LabelLayoutActivity::class.java))
        }

        btnScalableImageView.setOnClickListener {
            startActivity(Intent(this, ScalableImageViewActivity::class.java))
        }

    }
}
