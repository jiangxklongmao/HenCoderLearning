package com.jiangxk.hencoderlearningview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import com.jiangxk.hencoderlearningview.View.DashBoardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dashBoard = findViewById<Button>(R.id.btn_dash_board)
        val pie = findViewById<Button>(R.id.btn_pie)

        dashBoard.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
        }

        pie.setOnClickListener {
            startActivity(Intent(this, PieActivity::class.java))
        }


    }
}
