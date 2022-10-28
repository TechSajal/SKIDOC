package com.example.humandisclass.Activity

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.Activity.Data.Informationdata
import com.example.humandisclass.Activity.Helper.BindingSigninActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class SigninActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var back:ImageView
    private lateinit var termscond:TextView
    private lateinit var firebaseauth:FirebaseAuth
    private  lateinit var db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = FirebaseFirestore.getInstance()
        BindingSigninActivity().EmailLoginBinding(binding)
        BindingSigninActivity().PasswordLoginBinding(binding)
        BindingSigninActivity().phonenosigninbinding(binding);
        firebaseauth = FirebaseAuth.getInstance()
        termscond = findViewById(R.id.termsandconditions)
       back = findViewById(R.id.signin_back)
        back.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        termscond.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        termscond.text = "Terms & Conditions"

        binding.signinContinue.setOnClickListener {
            val email = binding.textInputEmailSignin.text.toString()
            val pass = binding.textInputPasswordSignin.text.toString()
            val conpass = binding.textInputConfirmpasswordSignin.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() && conpass.isNotEmpty()){
                if(pass == conpass){
                    firebaseauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val userprofilesignindata = Informationdata(email,"","","","","",0,FirebaseAuth.getInstance().currentUser!!.uid,"No","https://firebasestorage.googleapis.com/v0/b/skidoc-efa6f.appspot.com/o/DummyProfileImage.png?alt=media&token=94419938-344e-4631-b4f8-86db0410b1ab")
                            db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(userprofilesignindata).addOnCompleteListener {
                                val intent = Intent(this,InfromationActivity::class.java)
                                startActivity(intent)
                            }.addOnFailureListener {
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
                            }

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Plz fill the password same",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Plz fill all the fields",Toast.LENGTH_LONG).show()
            }
        }
    }
}