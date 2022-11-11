package com.example.humandisclass.Activity

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.humandisclass.Adapters.ViewPagerAdapter
import com.example.humandisclass.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import me.relex.circleindicator.CircleIndicator3

class LandingActivity : AppCompatActivity() {
   private val images = mutableListOf<Int>()
   private val description = mutableListOf<String>()
    private lateinit var textviewtrouble :TextView
    private lateinit var viewpager2:ViewPager2
    private lateinit var emailedittext:TextInputEditText
    private lateinit var emailtextvie:TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        emailedittext = findViewById(R.id.textinputedittextstarted)
        emailtextvie = findViewById(R.id.textinputlayoutstarted)
        textviewtrouble = findViewById(R.id.textviewtroublesignin)
        textviewtrouble.paintFlags =Paint.UNDERLINE_TEXT_FLAG
        textviewtrouble.text = "Trouble signing in?"
        posttolist()
        viewpager2 = findViewById(R.id.viewpager1)
        val adapter = ViewPagerAdapter(images,description)
        viewpager2.adapter = adapter
         val indicator:CircleIndicator3=findViewById(R.id.indicator)
        indicator.setViewPager(viewpager2)

        emailedittext.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)
        }
        emailtextvie.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up)

        }


    }
    private fun addtolist(image:Int,desc:String){
        images.add(image)
        description.add(desc)
    }
    private fun posttolist(){
        addtolist(R.drawable.main1image2,"Your Complete skincare companion,in your pocket")
        addtolist(R.drawable.main1image,"Scan your skin disease to find information of the disease")
        addtolist(R.drawable.main1image3,"Read patients' stories and book doctor appointments")

    }
}