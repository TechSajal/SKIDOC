package com.example.humandisclass.Activity

import android.graphics.Paint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.humandisclass.Activity.Adapters.ViewPagerAdapter
import com.example.humandisclass.R
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {
   private val images = mutableListOf<Int>()
   private val description = mutableListOf<String>()
    private lateinit var textviewtrouble :TextView
    private lateinit var viewpager2:ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textviewtrouble = findViewById(R.id.textviewtroublesignin)
        textviewtrouble.paintFlags =Paint.UNDERLINE_TEXT_FLAG
        textviewtrouble.text = "Trouble signing in?"
        posttolist()
        viewpager2 = findViewById(R.id.viewpager1)
        val adapter = ViewPagerAdapter(images,description)
        viewpager2.adapter = adapter
         val indicator:CircleIndicator3=findViewById(R.id.indicator)
        indicator.setViewPager(viewpager2)
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