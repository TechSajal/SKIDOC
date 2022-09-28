package com.example.humandisclass.Activity

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.humandisclass.Activity.Helper.BindingLoginActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var newuser :TextView
    private lateinit var loginback:ImageView
    private lateinit var continuelogin:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginback = findViewById(R.id.login_back)
        newuser = findViewById(R.id.newuser)
        newuser.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        newuser.text = "Sign In"
        continuelogin = findViewById(R.id.login_continue)
        BindingLoginActivity().EmailLoginBinding(binding)
        BindingLoginActivity().PasswordLoginBinding(binding)
        loginback.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        newuser.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        continuelogin.setOnClickListener {
            val intent = Intent(this,InfromationActivity::class.java)
            startActivity(intent)
        }

    }






}