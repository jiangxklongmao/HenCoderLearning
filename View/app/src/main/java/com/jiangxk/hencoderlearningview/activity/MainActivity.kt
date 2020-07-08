package com.jiangxk.hencoderlearningview.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.jiangxk.hencoderlearningview.R
import com.jiangxk.hencoderlearningview.activity.animation.AnimationActivity
import com.jiangxk.hencoderlearningview.activity.constraintLayout.ConstraintLayoutActivity
import com.jiangxk.hencoderlearningview.activity.drag.DragHelperActivity
import com.jiangxk.hencoderlearningview.activity.hook.HookActivity
import com.jiangxk.hencoderlearningview.activity.multiTouch.MultiTouchActivity
import kotlinx.android.synthetic.main.activity_main.*

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
        val btnMultiTouch = findViewById<Button>(R.id.btn_multi_touch)
        val btnSimplePager = findViewById<Button>(R.id.btn_simple_pager)
        val btnDragHelper = findViewById<Button>(R.id.btn_drag_helper)

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

        btnMultiTouch.setOnClickListener {
            startActivity(Intent(this, MultiTouchActivity::class.java))
        }

        btnSimplePager.setOnClickListener {
            startActivity(Intent(this, SimplePagerActivity::class.java))
        }

        btnDragHelper.setOnClickListener {
            startActivity(Intent(this, DragHelperActivity::class.java))
        }

        btn_hook.setOnClickListener {
            startActivity(Intent(this, HookActivity::class.java))
        }

        nested_scalable_image_view.setOnClickListener {
            startActivity(Intent(this, NestedScalableImageViewActivity::class.java))
        }

        constraint_layout.setOnClickListener {
            startActivity(Intent(this, ConstraintLayoutActivity::class.java))
        }


    }
}
