package com.example.humandisclass.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.Activity.Helper.BindingLoginActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var newuser :TextView
    private lateinit var loginback:ImageView
    private lateinit var continuelogin:Button
    private lateinit var firebaseauth:FirebaseAuth
    @SuppressLint("LongLogTag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginback = findViewById(R.id.login_back)
        newuser = findViewById(R.id.newuser)
        newuser.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        newuser.text = "Sign In"
        firebaseauth = FirebaseAuth.getInstance()
        continuelogin = findViewById(R.id.login_continue)
        BindingLoginActivity().EmailLoginBinding(binding)
        BindingLoginActivity().PasswordLoginBinding(binding)
        loginback.setOnClickListener {
            val intent = Intent(this,LandingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        newuser.setOnClickListener {
            val intent = Intent(this,SigninActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        continuelogin.setOnClickListener {
            val email = binding.textInputEmailLogin.text.toString()
            val pass = binding.textInputPasswordLogin.text.toString()
            if(email.isEmpty() || pass.isEmpty()){
                Toast.makeText(this,"Plz fill all the fields",Toast.LENGTH_LONG).show()
            }else{
               firebaseauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                   if(it.isSuccessful){
                      val intent = Intent(this,MainActivity::class.java)
                       startActivity(intent)
                   }else{
                       Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                   }
               }
            }
        }



    }






}