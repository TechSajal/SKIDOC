package com.example.humandisclass.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.humandisclass.R
import com.example.humandisclass.Util.getProgressDrawble
import com.example.humandisclass.Util.loadImage
import kotlinx.android.synthetic.main.activity_information.*


class InformationActivity : AppCompatActivity() {
    private lateinit var disimage:ImageView
    private lateinit var disname:TextView
    private lateinit var disdisc:TextView
    private lateinit var dissym1:TextView
    private lateinit var dissym2:TextView
    private lateinit var dissym3:TextView
    private lateinit var discau1:TextView
    private lateinit var discau2:TextView
    private lateinit var discau3:TextView
    private lateinit var dispre1:TextView
    private lateinit var dispre2:TextView
    private lateinit var dispre3:TextView
    private lateinit var distreat1:TextView
    private lateinit var distreat2:TextView
    private lateinit var distreat3:TextView
    private lateinit var disdia1:TextView
    private lateinit var disdia2:TextView
    private lateinit var disdia3:TextView
    private lateinit var seeadvicebut:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        disimage = findViewById(R.id.image_information)
        disname = findViewById(R.id.disease_name_information)
        disdisc = findViewById(R.id.disc_information)
        dissym1 = findViewById(R.id.symptoms1)
        dissym2 = findViewById(R.id.symptoms2)
        dissym3 = findViewById(R.id.symptoms3)
        discau1 = findViewById(R.id.causes1)
        discau2 = findViewById(R.id.causes2)
        discau3 = findViewById(R.id.causes3)
        dispre1 = findViewById(R.id.preventions1)
        dispre2 = findViewById(R.id.prevention2)
        dispre3 = findViewById(R.id.prevention3)
        distreat1 = findViewById(R.id.treatment1)
        distreat2 = findViewById(R.id.treatment2)
        distreat3 = findViewById(R.id.treatment3)
        disdia1 = findViewById(R.id.diagnosis1)
        disdia2 = findViewById(R.id.diagnosis2)
        disdia3 = findViewById(R.id.diagnosis3)
        seeadvicebut = findViewById(R.id.seeadvice)
        val i =intent
        val progressdialog = getProgressDrawble(this)
        val image:String = i.getStringExtra("image").toString()
        val name:String = i.getStringExtra("name").toString()
        val disc:String = i.getStringExtra("description").toString()
        val symptoms1:String = i.getStringExtra("symptions1").toString()
        val symptoms2:String = i.getStringExtra("symptions2").toString()
        val symptoms3:String = i.getStringExtra("symptions3").toString()
        val causes1:String = i.getStringExtra("causes1").toString()
        val causes2:String = i.getStringExtra("causes2").toString()
        val causes3:String = i.getStringExtra("causes3").toString()
        val prevention1:String = i.getStringExtra("preventions1").toString()
        val prevention2:String = i.getStringExtra("preventions2").toString()
        val prevention3:String = i.getStringExtra("preventions3").toString()
        val treatment1:String = i.getStringExtra("treatment1").toString()
        val treatment2:String = i.getStringExtra("treatment2").toString()
        val treatment3:String = i.getStringExtra("treatment3").toString()
        val diagnosis1:String = i.getStringExtra("diagnosis1").toString()
        val diagnosis2:String = i.getStringExtra("diagnosis2").toString()
        val diagnosis3:String = i.getStringExtra("diagnosis3").toString()
        seeadvicebut.setOnClickListener {
            val i = Intent(this,NameDiseaseAdvice::class.java)
              i.putExtra("disname",name)
            this.startActivity(i)
        }
        disname.text = name
        disimage.loadImage(image,progressdialog)
        disdisc.text = disc
        dissym1.text = symptoms1
        dissym2.text = symptoms2
        dissym3.text = symptoms3
        discau1.text = causes1
        discau2.text = causes2
        discau3.text = causes3
        dispre1.text =prevention1
        dispre2.text = prevention2
        dispre3.text = prevention3
        distreat1.text = treatment1
        distreat2.text = treatment2
        distreat3.text = treatment3
        disdia1.text = diagnosis1
        disdia2.text =diagnosis2
        disdia3.text = diagnosis3





    }
}