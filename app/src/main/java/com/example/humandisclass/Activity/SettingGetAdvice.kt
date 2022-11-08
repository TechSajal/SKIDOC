package com.example.humandisclass.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.humandisclass.Activity.Adapters.NameDiseaseAdviceAdapter
import com.example.humandisclass.Activity.Data.getadvice
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivitySeeAdviceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_setting_get_advice.*

class SettingGetAdvice : AppCompatActivity() {
    private lateinit var adaptersettinggetadvice:NameDiseaseAdviceAdapter
    private lateinit var recyclerviewsettinggetadvice:RecyclerView
    private lateinit var frametext:FrameLayout
    val db = FirebaseFirestore.getInstance()
    val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_get_advice)
        frametext = findViewById(R.id.frame_settinggetadvice)
        recyclerviewsettinggetadvice = findViewById(R.id.recycler_view_settinggetadvices)
        adaptersettinggetadvice = NameDiseaseAdviceAdapter(this, arrayListOf())
        recyclerviewsettinggetadvice.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = adaptersettinggetadvice
            setHasFixedSize(true)
        }
        db.collection("comments").get().addOnSuccessListener { document ->
            val lists:ArrayList<getadvice> = ArrayList()
            for (i in document.documents){
                val advice =i.toObject(getadvice::class.java)
                if (advice!!.uid == currentuserid){
                    Log.println(Log.ASSERT,advice.uid,"uid")
                    Log.println(Log.ASSERT,currentuserid,"cuid")
                    lists.add(advice)
                }
            }
            if(lists.size>0){
                recycler_view_settinggetadvices.visibility = View.VISIBLE
                frametext.visibility = View.GONE
                adaptersettinggetadvice.nameadvice(lists)
            }else{
                recycler_view_settinggetadvices.visibility = View.GONE
                frametext.visibility = View.VISIBLE
            }
        }
    }
}