package com.example.humandisclass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.humandisclass.Activity.Data.getadvice
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityAddAdviceBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add_advice.*
import kotlinx.android.synthetic.main.activity_edit_profile2.*

class AddAdviceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddAdviceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddAdviceBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        frame_addadvice.visibility = View.GONE
        back_act_add_adv.setOnClickListener {
            onBackPressed()
        }
        submitadvice.setOnClickListener {
            if (add_advice_autocomplete.text.isNullOrEmpty() || add_user_advice_edittext2.text.isNullOrEmpty()){
                Toast.makeText(this,"Please fill all the details",Toast.LENGTH_LONG).show()
            }else{
                frame_addadvice.visibility =View.VISIBLE
                lottie_addadvice.playAnimation()
                val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
                val db = FirebaseFirestore.getInstance()
                db.collection("users").document(currentuserid).get().addOnSuccessListener {document ->
                    val data = hashMapOf(
                        "name" to document.get("fullname").toString(),
                        "age" to document.get("age").toString(),
                        "disease" to document.get("disease").toString(),
                        "image" to document.get("userimage").toString(),
                        "dis_name" to add_advice_autocomplete.text.toString(),
                        "dis_disc" to add_user_advice_edittext2.text.toString(),
                        "uid" to currentuserid,
                        "id" to currentuserid
                    )
                    db.collection("comments").add(data).addOnSuccessListener { document ->
                        db.collection("comments").document(document.id).update(mapOf("id" to document.id)).addOnSuccessListener {
                            frame_addadvice.visibility =View.GONE
                            lottie_addadvice.visibility =View.GONE
                            val i= Intent(this,MainActivity::class.java)
                                i.putExtra("place",1)
                               startActivity(i)
                               finish()
                        }.addOnFailureListener {
                                Toast.makeText(this,"WDWFWF",Toast.LENGTH_LONG).show()
                            }
                    }
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        val skindis= resources.getStringArray(R.array.SkinDiseaseconfirm)
        val skindisadapter = ArrayAdapter(this,R.layout.dropdown_skindis,skindis)
        add_advice_autocomplete.setAdapter(skindisadapter)
    }


}