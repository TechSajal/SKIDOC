package com.example.humandisclass.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.example.humandisclass.Frament.MainFragment
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SeeAdviceActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var name:TextView
    private lateinit var age:TextView
    private lateinit var disease:TextView
    private lateinit var adv_dis:TextView
    private lateinit var adv_disc:TextView
    private lateinit var delete:ImageView
    private lateinit var fraamedelete:FrameLayout
    private lateinit var lottiedelete:LottieAnimationView
    private lateinit var back:ImageView
    private lateinit var banner:FrameLayout
    private lateinit var botton:MaterialButton
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_advice)
        image = findViewById(R.id.commentimage_seeadvice)
        name = findViewById(R.id.commentname_seeadvice)
        age = findViewById(R.id.commonyear_seeadvice)
        disease = findViewById(R.id.disease_seeadvice)
        adv_dis = findViewById(R.id.commentdisname_seeadvice)
        adv_disc = findViewById(R.id.commentdisdisc_seeadvice)
        delete = findViewById(R.id.delete_advice)
        fraamedelete = findViewById(R.id.frame_delete_seeadvice)
        lottiedelete = findViewById(R.id.lottie_delete_seeadvice)
        banner = findViewById(R.id.add_scantodisease_seeadvice)
        banner.setOnClickListener {
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("place",2)
            startActivity(i)
            finish()
        }
        botton = findViewById(R.id.getstartedseeadvicebotton)
        botton.setOnClickListener {
            val i =Intent(this,MainActivity::class.java)
            i.putExtra("place",2)
            startActivity(i)
            finish()
        }
        fraamedelete.visibility =View.GONE
        back = findViewById(R.id.back_act_see_adv)
        back.setOnClickListener {
            onBackPressed()
        }
        val progressdialog = getProgressDrawble(this)
        image.loadImage(intent.getStringExtra("image"),progressdialog)
        name.text = intent.getStringExtra("name")
        val agei = intent.getStringExtra("age")
        age.text = "$agei years"
        disease.text = intent.getStringExtra("disease")
        adv_dis.text = intent.getStringExtra("dis_name")
        adv_disc.text = intent.getStringExtra("dis_disc")
        val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()
        val uid =intent.getStringExtra("uid")
        val id =intent.getStringExtra("id")
        if (uid.equals(currentuserid)){
            if(intent.getIntExtra("place",0)==2){
                delete.visibility =View.GONE
            }else if(intent.getIntExtra("place",0)==3){
                delete.visibility =View.VISIBLE
            }
            else{
                delete.visibility = View.VISIBLE
            }
        }else{
            delete.visibility =View.GONE
        }
         delete.setOnClickListener {
             val builder = AlertDialog.Builder(this)
             builder.setTitle("Delete")
             builder.setMessage("Are you sure you want to delete the product")
             builder.setIcon(R.drawable.ic_delete)
             builder.setPositiveButton("Yes"){ dialoginterface, _ ->
                 fraamedelete.visibility =View.VISIBLE
                 lottiedelete.playAnimation()
                 db.collection("comments").document(id.toString()).delete().addOnSuccessListener {
                     if (intent.getIntExtra("place",0)==3){
                         onBackPressed()
                     }else {
                         val i = Intent(this, MainActivity::class.java)
                         i.putExtra("place", 1)
                         lottiedelete.cancelAnimation()
                         fraamedelete.visibility = View.GONE
                         startActivity(i)
                         finish()
                     }
                 }.addOnFailureListener{
                     Toast.makeText(this,"Could not delete plz try again later",Toast.LENGTH_LONG).show()
                     lottiedelete.cancelAnimation()
                     fraamedelete.visibility = View.GONE
                 }
                 dialoginterface.dismiss()
                 }
             builder.setNegativeButton("No"){ dialoginterface, _ ->
                 dialoginterface.dismiss()
             }
             val alertDialog: AlertDialog = builder.create()
             alertDialog.setCancelable(false)
             alertDialog.show()
         }


    }
}