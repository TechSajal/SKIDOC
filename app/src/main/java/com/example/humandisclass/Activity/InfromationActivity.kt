package com.example.humandisclass.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.humandisclass.Activity.Data.Informationdata
import com.example.humandisclass.Activity.Helper.BindingInfromationActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityInfromationBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class InfromationActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private  lateinit var db : FirebaseFirestore
    private lateinit var userid:String
    private lateinit var binding:ActivityInfromationBinding
    private lateinit var email:TextInputEditText
    private lateinit var phoneno:TextInputEditText
    private lateinit var bloodgroup: AutoCompleteTextView
    private lateinit var gendertextview:AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfromationBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        setContentView(binding.root)
        BindingInfromationActivity().ageInfromationbinding(binding)
        BindingInfromationActivity().phonenoInfromationbinding(binding)
        BindingInfromationActivity().fullnameInfromationbinding(binding)
        userid = FirebaseAuth.getInstance().currentUser!!.uid
        email = findViewById(R.id.act_inf_edittext_email)
        phoneno = findViewById(R.id.act_inf_edittext_phoneno)
        email.setText(auth.currentUser?.email)
        bloodgroup= findViewById(R.id.act_inf_edittext_bloodgroup)
        gendertextview = findViewById(R.id.act_inf_edittext_gender)
        binding.actInfContinue.setOnClickListener {
              binding.actInfContainerPhoneno.helperText =BindingInfromationActivity().validphoneno(binding)
              binding.actInfContainerAge.helperText = BindingInfromationActivity().validage(binding)
              binding.actInfContainerFullname.helperText=BindingInfromationActivity().validfullname(binding)
            if (binding.actInfContainerPhoneno.helperText == null && binding.actInfContainerFullname.helperText ==null &&
                binding.actInfContainerAge.helperText ==null){
                savedatatofirebase(
                    binding.actInfEdittextEmail.text.toString(),
                    binding.actInfEdittextPhoneno.text.toString(),
                    binding.actInfEdittextFullname.text.toString(),
                    binding.actInfEdittextBloodgroup.text.toString(),
                    binding.actInfEdittextGender.text.toString(),
                    binding.actInfEdittextAge.text.toString(),
                    1
                )
        }
        }
    }

    fun savedatatofirebase(
        email:String,
        phoneno:String,
        fullname:String,
        broodgroup:String,
        gender:String,
        age:String,
        completed:Int
    ){
          val map = mapOf(
              "fullname" to fullname,
              "phoneno" to phoneno,
              "bloodgroup" to broodgroup,
              "gender" to gender,
              "age" to age,
              "completed" to completed
          )
        val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
           db.collection("users").document(currentuserid).update(map)
               .addOnSuccessListener {
                   Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show()
               }
               .addOnFailureListener {
                   Toast.makeText(this,it.toString(),Toast.LENGTH_LONG).show()
               }


    }

    override fun onResume() {
        super.onResume()
        val languages =  resources.getStringArray(R.array.BloodGroup)
        val arrayadapter = ArrayAdapter(this,R.layout.dropdown_item,languages)
        bloodgroup.setAdapter(arrayadapter)

        val gender = resources.getStringArray(R.array.gender)
        val genderarrayadaper = ArrayAdapter(this,R.layout.dropdown_gender,gender)
        gendertextview.setAdapter(genderarrayadaper)
    }
}