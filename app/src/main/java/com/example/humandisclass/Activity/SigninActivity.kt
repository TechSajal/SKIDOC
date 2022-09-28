package com.example.humandisclass.Activity

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.humandisclass.Activity.Helper.BindingSigninActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityLoginBinding
import com.example.humandisclass.databinding.ActivityMainBinding
import com.example.humandisclass.databinding.ActivitySigninBinding

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var back:ImageView
    private lateinit var termscond:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        BindingSigninActivity().EmailLoginBinding(binding)
        BindingSigninActivity().PasswordLoginBinding(binding)
        BindingSigninActivity().phonenologinbinding(binding);
        termscond = findViewById(R.id.termsandconditions)
       back = findViewById(R.id.signin_back)
        back.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        termscond.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        termscond.text = "Terms & Conditions"
    }
}