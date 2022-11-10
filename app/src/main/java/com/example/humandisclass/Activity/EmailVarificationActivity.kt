package com.example.humandisclass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EmailVarificationActivity : AppCompatActivity() {
    private lateinit var varifybutton:Button
    private lateinit var firebaseauth: FirebaseAuth
    private  lateinit var db : FirebaseFirestore
    private lateinit var resendlink:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_varification)
        db = FirebaseFirestore.getInstance()
        firebaseauth = FirebaseAuth.getInstance()
        varifybutton = findViewById(R.id.emailverified)
        resendlink = findViewById(R.id.resendverificationlink)
        varifybutton.setOnClickListener {
            val isverified = firebaseauth.currentUser?.isEmailVerified
            if (isverified == true){
                 val i = Intent(this,InfromationActivity::class.java)
                    this.startActivity(i)
            }else if (isverified == false){
                Toast.makeText(this,"Please Verify  your email",Toast.LENGTH_LONG).show()
            }
        }
        resendlink.setOnClickListener {
            firebaseauth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                Toast.makeText(this,"Link has been send to you on your email",Toast.LENGTH_LONG).show()
            }
                ?.addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
                }
        }
    }
}