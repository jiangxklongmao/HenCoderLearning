package com.jiangxk.hencoderlearningkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.jiangxk.base.utils.CacheUtils
import com.jiangxk.base.utils.Utils
import com.jiangxk.base.utils.toast
import com.jiangxk.hencoderlearningkotlin.entiy.User
import com.jiangxk.hencoderlearningkotlin.widget.CodeView
import com.jiangxk.lesson.LessonActivity

class MainActivity : AppCompatActivity() {

    private val userNameKey = "username"
    private val passwordKey = "password"

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCode: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etCode = findViewById(R.id.et_code)

        etUsername.setText(CacheUtils.get(userNameKey))
        etPassword.setText(CacheUtils.get(passwordKey))

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val imgCode = findViewById<CodeView>(R.id.code_view)
        btnLogin.setOnClickListener {
            login()
        }
        imgCode.setOnClickListener {
            if (it is CodeView) {
                it.updateCode()
            }
        }
    }


    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        val code = etCode.text.toString()

        val user = User(username, password, code)

        if (verify(user)) {
            CacheUtils.save(userNameKey, username)
            CacheUtils.save(passwordKey, password)
            startActivity(Intent(this, LessonActivity::class.java))
        }
    }

    /**
     * 验证
     * @param user User
     * @return Boolean
     */
    private fun verify(user: User): Boolean {
        if (user.username.length < 4) {
            toast("用户名不合法")
            return false
        }
        if (user.password.length < 4) {
            toast("密码不合法")
            return false
        }
        return true
    }

}
