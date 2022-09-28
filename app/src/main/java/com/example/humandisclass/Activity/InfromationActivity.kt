package com.example.humandisclass.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.humandisclass.R
import com.google.android.material.textfield.TextInputEditText

class InfromationActivity : AppCompatActivity() {
    private lateinit var email:TextInputEditText
    private lateinit var phoneno:TextInputEditText
    private lateinit var bloodgroup: AutoCompleteTextView
    private lateinit var gendertextview:AutoCompleteTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infromation)
        email = findViewById(R.id.act_inf_edittext_email)
        phoneno = findViewById(R.id.act_inf_edittext_phoneno)
        phoneno.setText("9958969106")
        email.setText("Sajalkaushal3@gmail.com")
        bloodgroup= findViewById(R.id.act_inf_edittext_bloodgroup)
        gendertextview = findViewById(R.id.act_inf_edittext_gender)

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