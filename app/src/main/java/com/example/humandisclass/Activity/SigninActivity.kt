package com.example.humandisclass.Activity

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.Data.Informationdata
import com.example.humandisclass.Helper.BindingSigninActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signin.*

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
        frame_signin_load.visibility = View.GONE
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
            frame_signin_load.visibility=View.VISIBLE
            lottie_signin_load.playAnimation()
            val email = binding.textInputEmailSignin.text.toString()
            val pass = binding.textInputPasswordSignin.text.toString()
            val conpass = binding.textInputConfirmpasswordSignin.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() && conpass.isNotEmpty()){
                if(pass == conpass){
                    firebaseauth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val userprofilesignindata = Informationdata(email,"","","","","",0,FirebaseAuth.getInstance().currentUser!!.uid,"No","https://firebasestorage.googleapis.com/v0/b/skidoc-efa6f.appspot.com/o/DummyProfileImage.png?alt=media&token=94419938-344e-4631-b4f8-86db0410b1ab")
                            db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(userprofilesignindata).addOnCompleteListener {
                                 firebaseauth.currentUser?.sendEmailVerification()!!.addOnSuccessListener {
                                     lottie_signin_load.cancelAnimation()
                                     frame_signin_load.visibility =View.GONE
                                     val intent = Intent(this,EmailVarificationActivity::class.java)
                                      finish()
                                     this.startActivity(intent)
                                 }
                                     .addOnFailureListener { it1 ->
                                         lottie_signin_load.cancelAnimation()
                                         frame_signin_load.visibility =View.GONE
                                         Toast.makeText(this,it1.toString(),Toast.LENGTH_LONG).show()
                                     }
                            }.addOnFailureListener { it2 ->
                                lottie_signin_load.cancelAnimation()
                                frame_signin_load.visibility =View.GONE
                                Toast.makeText(this,it2.toString(),Toast.LENGTH_LONG).show()
                            }

                        }else{
                            lottie_signin_load.cancelAnimation()
                            frame_signin_load.visibility =View.GONE
                            Toast.makeText(this,"This user is already present in out database",Toast.LENGTH_LONG).show()
                        }
                    }
                }else{
                    lottie_signin_load.cancelAnimation()
                    frame_signin_load.visibility =View.GONE
                    Toast.makeText(this,"Plz fill the password same",Toast.LENGTH_LONG).show()
                }
            }else{
                lottie_signin_load.cancelAnimation()
                frame_signin_load.visibility =View.GONE
                Toast.makeText(this,"Plz fill all the fields",Toast.LENGTH_LONG).show()
            }
        }
    }
}