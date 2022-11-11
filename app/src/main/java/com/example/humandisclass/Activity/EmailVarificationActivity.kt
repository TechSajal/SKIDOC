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
    private val currentuserid = FirebaseAuth.getInstance().currentUser!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_varification)
        varifybutton = findViewById(R.id.emailverified)
        resendlink = findViewById(R.id.resendverificationlink)
        varifybutton.setOnClickListener {
            val isverified = currentuserid.isEmailVerified
            if (isverified){
                 val i = Intent(this,InfromationActivity::class.java)
                    finish()
                    this.startActivity(i)
            }else if (!isverified){
                Toast.makeText(this,"Please Verify  your email",Toast.LENGTH_LONG).show()
            }
        }
        resendlink.setOnClickListener {

            FirebaseAuth.getInstance().currentUser?.sendEmailVerification()?.addOnSuccessListener {
                Toast.makeText(this,"Link has been send to you on your email",Toast.LENGTH_LONG).show()
            }
                ?.addOnFailureListener {
                    Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun onRestart() {
        super.onRestart()
        currentuserid.reload()
    }

}