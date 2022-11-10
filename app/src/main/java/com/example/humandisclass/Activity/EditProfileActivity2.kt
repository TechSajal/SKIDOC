package com.example.humandisclass.Activity

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.humandisclass.Activity.Helper.BindingInfromationActivity
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityEditProfile2Binding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_profile2.*
import kotlinx.android.synthetic.main.activity_setting.*

class EditProfileActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfile2Binding
    private val READ_STORAGE_PERMISSION_CODE =2
    private var filepath:Uri? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditProfile2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        frame_uploading.visibility =View.GONE
        back_editprofile.setOnClickListener {
            onBackPressed()
        }
        val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
        val db = FirebaseFirestore.getInstance()
        BindingInfromationActivity().BloodGroupEditSettingbinding(binding)
        BindingInfromationActivity().genderEditSettingsbinding(binding)
        BindingInfromationActivity().fullnameEditSettingbinding(binding)
        BindingInfromationActivity().ageEditSettingsbinding(binding)
        BindingInfromationActivity().phonenoEditSettingbinding(binding)
        BindingInfromationActivity().EmailEditSettingsBinding(binding)
        db.collection("users").document(currentuserid).addSnapshotListener(this,){value,error->
            if (value!!.exists()){
                val image  = value.getString("userimage")
                Glide.with(this).load(image).diskCacheStrategy(DiskCacheStrategy.DATA).into(profile_edit_image2)
                edit_profile_email_edittext2.setText(value.getString("email"))
                edit_profile_phonene_edittext2.setText(value.getString("phoneno"))
                edit_profile_age_edittext2.setText(value.getString("age"))
                edit_profile_gender2_autocomplete.setText(value.getString("gender"))
                edit_profile_disease2_autocomplete.setText(value.getString("disease"))
                edit_profile_bloodgroup2_autocomplete.setText(value.getString("bloodgroup"))
                edit_profile_name_edittext2.setText(value.getString("fullname"))
            }
        }

        change_profile_photo2.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                val i = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(i, "Choose Picture"), 111)
            }else{
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )

            }
        }

        submit_button_2.setOnClickListener {
            edit_profile_name2.helperText = BindingInfromationActivity().validfullnameEditSetting(binding)
            edit_profile_phonene2.helperText = BindingInfromationActivity().validEditSettingsphoneno(binding)
            edit_profile_email2.helperText = BindingInfromationActivity().validEmailEditSettings(binding)
            edit_profile_age2.helperText = BindingInfromationActivity().validageEditSetting(binding)
            edit_profile_bloodgroup2.helperText = BindingInfromationActivity().validBloodGroupSettingsphoneno(binding)
            edit_profile_gender2.helperText = BindingInfromationActivity().validgenderEditSetting(binding)
           if (edit_profile_phonene2.helperText==null && edit_profile_email2.helperText ==null && edit_profile_age2.helperText==null && edit_profile_bloodgroup2.helperText==null && edit_profile_gender2.helperText==null
             ){
               val currentuserid = FirebaseAuth.getInstance().currentUser!!.uid
               val db = FirebaseFirestore.getInstance()
               if (filepath !=null){
                   frame_uploading.visibility =View.VISIBLE
                   lottie_uploading.playAnimation()
                   val imageref = FirebaseStorage.getInstance().reference.child("product/" +System.currentTimeMillis() + "productpic.jpg" )
                   imageref.putFile(filepath!!)
                       .addOnSuccessListener {
                           imageref.downloadUrl.addOnSuccessListener { task ->
                               val url = task.toString()
                               val map = mapOf(
                                   "fullname" to  edit_profile_name_edittext2.text.toString(),
                                   "phoneno" to edit_profile_phonene_edittext2.text.toString(),
                                   "bloodgroup" to edit_profile_bloodgroup2_autocomplete.text.toString(),
                                   "gender" to edit_profile_gender2_autocomplete.text.toString(),
                                   "age" to edit_profile_age_edittext2.text.toString(),
                                   "email" to edit_profile_email_edittext2.text.toString(),
                                   "disease" to edit_profile_disease2_autocomplete.text.toString(),
                                   "userimage" to url
                               )
                               db.collection("users").document(currentuserid).update(map)
                                   .addOnSuccessListener {
                                       frame_uploading.visibility =View.GONE
                                       lottie_uploading.cancelAnimation()
                                       Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show()
                                        finish()

                                   }
                                   .addOnFailureListener {
                                       frame_uploading.visibility =View.GONE
                                       lottie_uploading.cancelAnimation()
                                       Toast.makeText(this,"Could not update plz try again later",Toast.LENGTH_LONG).show()
                                   }
                           }
                       }
                       .addOnFailureListener{
                           frame_uploading.visibility =View.GONE
                           lottie_uploading.cancelAnimation()
                           Toast.makeText(this,"Could not update plz try again later",Toast.LENGTH_LONG).show()
                       }
               }else{
                   frame_uploading.visibility =View.VISIBLE
                   lottie_uploading.playAnimation()
                   val map = mapOf(
                       "fullname" to  edit_profile_name_edittext2.text.toString(),
                       "phoneno" to edit_profile_phonene_edittext2.text.toString(),
                       "bloodgroup" to edit_profile_bloodgroup2_autocomplete.text.toString(),
                       "gender" to edit_profile_gender2_autocomplete.text.toString(),
                       "age" to edit_profile_age_edittext2.text.toString(),
                       "email" to edit_profile_email_edittext2.text.toString(),
                       "disease" to edit_profile_disease2_autocomplete.text.toString()
                   )

                   db.collection("users").document(currentuserid).update(map)
                       .addOnSuccessListener {
                           frame_uploading.visibility =View.GONE
                           lottie_uploading.cancelAnimation()
                           Toast.makeText(this,"Updated",Toast.LENGTH_LONG).show()
                           finish()
                       }
                       .addOnFailureListener {
                           frame_uploading.visibility =View.GONE
                           lottie_uploading.cancelAnimation()
                           Toast.makeText(this,"Could not update plz try again later",Toast.LENGTH_LONG).show()
                       }

               }

           }else{
               val snackbar = Snackbar.make(it, "Plz see all the field", Snackbar.LENGTH_LONG)
               val sbView: View = snackbar.view
               sbView.setBackgroundColor(resources.getColor(R.color.design_default_color_error))
               snackbar.show()
           }
        }

    }

    override fun onResume() {
        super.onResume()
        val skindis= resources.getStringArray(R.array.SkinDisease)
        val skindisadapter = ArrayAdapter(this,R.layout.dropdown_skindis,skindis)
        edit_profile_disease2_autocomplete.setAdapter(skindisadapter)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == READ_STORAGE_PERMISSION_CODE){
            if (grantResults.isNotEmpty()&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val i = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(i, "Choose Picture"), 111)
            }else{
                Toast.makeText(applicationContext, "Storage Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode ==111 && resultCode == RESULT_OK && data != null){
            filepath =data.data!!
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
            Glide.with(this).load(bitmap).into(profile_edit_image2)
        }
    }
}