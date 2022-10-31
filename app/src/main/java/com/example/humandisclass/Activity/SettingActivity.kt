package com.example.humandisclass.Activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage
import com.example.humandisclass.databinding.ActivitySettingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile2.*
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding
    private var img :String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        relativelayout_editprofile.setOnClickListener {
            val intent = Intent(this,EditProfileActivity2::class.java)
            startActivity(intent)
        }

        val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(currentuserid).addSnapshotListener(this,){value,error->
            if (value!!.exists()){
                val image  = value.getString("userimage")
                img = image
                val progressdialog = getProgressDrawble(this)
                profile_image.loadImage(image,progressdialog)
                setting_act_email.text = value.getString("email")
                settings_phoneno_edittext.setText(value.getString("phoneno"))
                setting_show_age_edittext.setText(value.getString("age"))
                setting_show_gender_edittext.setText(value.getString("gender"))
                setting_skin_disease_autocomptextview.setText(value.getString("disease"))
                setting_skin_blood_group_edittext.setText(value.getString("bloodgroup"))
                setting_act_name.text = value.getString("fullname")
            }
        }
    }
}