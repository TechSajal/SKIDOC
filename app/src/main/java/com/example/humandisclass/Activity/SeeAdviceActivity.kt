package com.example.humandisclass.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.humandisclass.Frament.MainFragment
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage
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
            delete.visibility = View.VISIBLE
        }else{
            delete.visibility =View.GONE
        }
         delete.setOnClickListener {
             db.collection("comments").document(id.toString()).delete().addOnSuccessListener {
                 Toast.makeText(this,"Advice deleted",Toast.LENGTH_LONG).show()

             }
                 .addOnFailureListener {
                     Toast.makeText(this,"Plz try again later",Toast.LENGTH_LONG).show()
                 }
         }


    }
}