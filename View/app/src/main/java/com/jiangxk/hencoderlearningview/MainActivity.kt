package com.jiangxk.hencoderlearningview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dashBoard = findViewById<Button>(R.id.btn_dash_board)
        val pie = findViewById<Button>(R.id.btn_pie)
        val circleAvatar = findViewById<Button>(R.id.btn_circle_avatar)

        dashBoard.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
        }

        pie.setOnClickListener {
            startActivity(Intent(this, PieActivity::class.java))
        }

        circleAvatar.setOnClickListener {
            startActivity(Intent(this, CircleAvatarActivity::class.java))
        }


    }
}
