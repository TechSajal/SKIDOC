package com.example.humandisclass.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.Helper.BindingLoginActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
               firebaseauth.signInWithEmailAndPassword(email,pass).addOnSuccessListener {
                    val isverify = firebaseauth.currentUser?.isEmailVerified
                   if(isverify == true){
                      val intent = Intent(this,MainActivity::class.java)
                       startActivity(intent)
                       Toast.makeText(this,getString(R.string.welcome),Toast.LENGTH_LONG).show()
                       finish()
                   }else{
                       val intent = Intent(this,EmailVarificationActivity::class.java)
                       this.startActivity(intent)
                       finish()
                   }
               }
                   .addOnFailureListener {
                       Toast.makeText(this,"Plz Signin",Toast.LENGTH_LONG).show()
                   }
            }
        }



    }






}