package com.jiangxk.hencoderlearningview.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.hencoderlearningview.R
import kotlinx.android.synthetic.main.activity_material_edittext.*

/**
 * @description com.jiangxk.hencoderlearningview.activity
 * @author jiangxk
 * @time 2020-06-09  20:17
 */
class MaterialEditTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_edittext)


        mEdt.postDelayed({
            mEdt.useFloatingLabel = false

            mEdt.postDelayed({
                mEdt.useFloatingLabel = true
            }, 5000)

        }, 5000)

    }


}