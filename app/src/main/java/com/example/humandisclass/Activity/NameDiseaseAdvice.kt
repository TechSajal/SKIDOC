package com.example.humandisclass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Adapters.NameDiseaseAdviceAdapter
import com.example.humandisclass.Data.getadvice
import com.example.humandisclass.R
import com.google.firebase.firestore.FirebaseFirestore

class NameDiseaseAdvice : AppCompatActivity() {
    private lateinit var recyclerViewnameAdvice: RecyclerView
    private lateinit var adapternameadvice: NameDiseaseAdviceAdapter
    private lateinit var frameLayoutnoadvice: FrameLayout
    private lateinit var addyouradvice:TextView
    private lateinit var back:ImageView
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_disease_advice)
        frameLayoutnoadvice = findViewById(R.id.frame_noadvice)
        addyouradvice = findViewById(R.id.addyouradvice_text)
        addyouradvice.setOnClickListener {
            val i = Intent(this,AddAdviceActivity::class.java)
            startActivity(i)
        }
        back = findViewById(R.id.back_act_name_disease_advice)
        back.setOnClickListener {
            onBackPressed()
        }

        frameLayoutnoadvice.visibility =View.GONE
        adapternameadvice = NameDiseaseAdviceAdapter(this, arrayListOf())
        recyclerViewnameAdvice = findViewById<RecyclerView?>(R.id.recycler_view_name_disease_advice)
            .apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = adapternameadvice
                setHasFixedSize(true)
            }
        db.collection("comments").get().addOnSuccessListener { document ->
            val lists:ArrayList<getadvice> = ArrayList()
            for (i in document.documents){
                val advice =i.toObject(getadvice::class.java)
                val name =intent.getStringExtra("disname")
                Log.println(Log.ASSERT,name,"name")
                Log.println(Log.ASSERT, advice!!.dis_name,"advice")
                if (advice.dis_name == name){
                    lists.add(advice)
                }
            }
            if (lists.size>0){
                recyclerViewnameAdvice.visibility = View.VISIBLE
                frameLayoutnoadvice.visibility =View.GONE
                adapternameadvice.nameadvice(lists)
            }else{
                recyclerViewnameAdvice.visibility = View.GONE
                frameLayoutnoadvice.visibility =View.VISIBLE

            }

        }
    }
}