package com.example.humandisclass.Activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.humandisclass.Activity.Adapters.NameDiseaseAdviceAdapter
import com.example.humandisclass.Activity.Adapters.NameDiseaseSettingAdapter
import com.example.humandisclass.Activity.Data.getadvice
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivitySeeAdviceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_setting_get_advice.*

class SettingGetAdvice : AppCompatActivity() {
    private lateinit var adaptersettinggetadvice:NameDiseaseSettingAdapter
    private lateinit var recyclerviewsettinggetadvice:RecyclerView
    private lateinit var frametext:FrameLayout
    private lateinit var framedeleteall:FrameLayout
    private lateinit var lottiedeleteall:LottieAnimationView
    private lateinit var deleteall:ImageView
    private lateinit var back:ImageView
    val db = FirebaseFirestore.getInstance()
    val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_get_advice)
        back = findViewById(R.id.back_settinggetadvice)
        back.setOnClickListener {
            onBackPressed()
        }
        framedeleteall = findViewById(R.id.frame_deleteall)
        lottiedeleteall = findViewById(R.id.lottie_deleteall)
        framedeleteall.visibility = View.GONE
        frametext = findViewById(R.id.frame_settinggetadvice)
        deleteall = findViewById(R.id.deleteall_settingyouradvice)
        deleteall.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete")
            builder.setMessage("Are you sure you want to delete the product")
            builder.setIcon(R.drawable.ic_delete)
            builder.setPositiveButton("Yes"){ dialoginterface, _ ->
                framedeleteall.visibility =View.VISIBLE
                lottiedeleteall.playAnimation()
                db.collection("comments").get().addOnSuccessListener { document ->
                    for (i in document.documents){
                        val advice =i.toObject(getadvice::class.java)
                        if (advice!!.uid == currentuserid){
                            db.collection("comments").document(advice.id!!).delete().addOnSuccessListener {
                            }
                                .addOnFailureListener {
                                    lottiedeleteall.cancelAnimation()
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
                                }
                        }

                    }
                    lottiedeleteall.cancelAnimation()
                    finish()
                    startActivity(intent)
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

        recyclerviewsettinggetadvice = findViewById(R.id.recycler_view_settinggetadvices)
        adaptersettinggetadvice = NameDiseaseSettingAdapter(this, arrayListOf())
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

    override fun onRestart() {
        super.onRestart()
        finish()
        startActivity(intent)
    }
}