package com.example.humandisclass.Activity.Helper

import android.util.Patterns
import com.example.humandisclass.databinding.ActivityEditProfile2Binding
import com.example.humandisclass.databinding.ActivityInfromationBinding
import com.example.humandisclass.databinding.ActivityLoginBinding
import com.example.humandisclass.databinding.ActivitySigninBinding

class BindingLoginActivity {

    fun EmailLoginBinding(binding: ActivityLoginBinding) {
        binding.textInputEmailLogin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.loginContainerEmail.helperText = validEmail(binding)
            }
        }

    }

    fun validEmail(binding: ActivityLoginBinding):String?{
        val emailtext = binding.textInputEmailLogin.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    fun PasswordLoginBinding(binding: ActivityLoginBinding) {
        binding.textInputPasswordLogin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.containerPasswordLogin.helperText = validPassword(binding)
            }
        }

    }

    fun validPassword(binding: ActivityLoginBinding):String?{
        val passwordtext = binding.textInputPasswordLogin.text.toString()
        if (passwordtext.length<8){
            return  "Minimum 8 Character Password"
        }
        if(!passwordtext.matches(".*[A-Z].*".toRegex())){
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordtext.matches(".*[a-z].*".toRegex())){
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordtext.matches(".*[!@#$%^&*()_+=-].*".toRegex())){
            return "Must Contain 1 Special Character (!@#\$%^&*()_+=-)"
        }
        return null
    }



}
/////cfgfgfgffg/////
class BindingSigninActivity{
    fun EmailLoginBinding(binding: ActivitySigninBinding) {
        binding.textInputEmailSignin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.emailContainerSignin.helperText = validEmail(binding)
            }
        }

    }

    fun validEmail(binding: ActivitySigninBinding):String?{
        val emailtext = binding.textInputEmailSignin.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
            return "Invalid Email Address"
        }
        return null
    }

    fun PasswordLoginBinding(binding: ActivitySigninBinding) {
        binding.textInputPasswordSignin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.containerPasswordSignin.helperText = validPassword(binding)
            }
        }

    }

    fun validPassword(binding: ActivitySigninBinding):String?{
        val passwordtext = binding.textInputPasswordSignin.text.toString()
        if (passwordtext.length<8){
            return  "Minimum 8 Character Password"
        }
        if(!passwordtext.matches(".*[A-Z].*".toRegex())){
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordtext.matches(".*[a-z].*".toRegex())){
            return "Must Contain 1 Lower-case Character"
        }
        if(!passwordtext.matches(".*[!@#$%^&*()_+=-].*".toRegex())){
            return "Must Contain 1 Special Character (!@#\$%^&*()_+=-)"
        }
        return null
    }

    fun phonenosigninbinding(binding: ActivitySigninBinding){
        binding.textInputConfirmpasswordSignin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.containerConfirmpassSignin.helperText = validphoneno(binding)
            }
        }
    }

    private fun validphoneno(binding: ActivitySigninBinding): String? {
    val confirmpasswordtext = binding.textInputConfirmpasswordSignin.text.toString()
        if (confirmpasswordtext.length<8){
            return  "Minimum 8 Character Password"
        }
        if(!confirmpasswordtext.matches(".*[A-Z].*".toRegex())){
            return "Must Contain 1 Upper-case Character"
        }
        if(!confirmpasswordtext.matches(".*[a-z].*".toRegex())){
            return "Must Contain 1 Lower-case Character"
        }
        if(!confirmpasswordtext.matches(".*[!@#$%^&*()_+=-].*".toRegex())){
            return "Must Contain 1 Special Character (!@#\$%^&*()_+=-)"
        }
        return null
    }


}

class BindingInfromationActivity(){
    fun ageInfromationbinding(binding: ActivityInfromationBinding){
        binding.actInfEdittextAge.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.actInfContainerAge.helperText = validage(binding)
            }
            if(!focused){
                binding.actInfContainerAge.helperText = validage(binding)
            }
        }
    }

     fun validage(binding: ActivityInfromationBinding): String? {
        val age = binding.actInfEdittextAge.text.toString()
            if (age.isEmpty()){
                return "fill age"
            }
            if (!age[0].toString().matches(".*[1-9].*".toRegex())){
                return "Age cannot be started from 0"
            }
            if (age[1].toString().matches(".*[0-9].*".toRegex())){
                return null
            }
            if (age[2].toString().matches(".*[0-9].*".toRegex())){
                return null
            }

        return null
    }

    fun phonenoInfromationbinding(binding: ActivityInfromationBinding){
        binding.actInfEdittextPhoneno.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.actInfContainerPhoneno.helperText = validphoneno(binding)
            }
            if(!focused){
                binding.actInfContainerPhoneno.helperText = validphoneno(binding)
            }
        }
    }

     fun validphoneno(binding: ActivityInfromationBinding): String? {
       val phoneno = binding.actInfEdittextPhoneno.text.toString()
        if (phoneno.length<10){
            return "Invalid phone no"
        }
        if (!Patterns.PHONE.matcher(phoneno).matches()){
            return "Invalid phone no"
        }
        return null
    }

    fun fullnameInfromationbinding(binding: ActivityInfromationBinding){
        binding.actInfEdittextFullname.setOnFocusChangeListener { _, focused ->
            if(focused){
                binding.actInfContainerFullname.helperText = validfullname(binding)
            }
            if(!focused){
                binding.actInfContainerFullname.helperText = validfullname(binding)
            }
        }
    }
     fun validfullname(binding: ActivityInfromationBinding): String? {
        val fullname = binding.actInfEdittextFullname.text.toString()
        if(!fullname.matches(".*[A-Z].*".toRegex())){
            return "plz change your first letter to capital"
        }
        if(!fullname.matches(".*[a-z].*".toRegex())){
            return "plz enter correct name"
        }


        return null
    }
     //Edit Settings
    fun fullnameEditSettingbinding(binding: ActivityEditProfile2Binding){
        binding.editProfileNameEdittext2.setOnFocusChangeListener { _, focused ->
            if(focused){
                binding.editProfileName2.helperText = validfullnameEditSetting(binding)
            }
            if(!focused){
                binding.editProfileName2.helperText = validfullnameEditSetting(binding)
            }
        }
    }
    fun validfullnameEditSetting(binding: ActivityEditProfile2Binding): String? {
        val fullname = binding.editProfileNameEdittext2.text.toString()
        if (fullname.isEmpty()){
            return "Plz enter your name"
        }
        if(!fullname.matches(".*[A-Z].*".toRegex())){
            return "plz change your first letter to capital"
        }
        if(!fullname.matches(".*[a-z].*".toRegex())){
            return "plz enter correct name"
        }
        return null
    }


    fun phonenoEditSettingbinding(binding: ActivityEditProfile2Binding){
        binding.editProfilePhoneneEdittext2.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.editProfilePhonene2.helperText = validEditSettingsphoneno(binding)
            }
            if(!focused){
                binding.editProfilePhonene2.helperText = validEditSettingsphoneno(binding)
            }
        }
    }

    fun validEditSettingsphoneno(binding: ActivityEditProfile2Binding): String? {
        val phoneno = binding.editProfilePhoneneEdittext2.text.toString()
        if (phoneno.isEmpty()){
            return "Plz fill phone no."
        }
        if (phoneno.length<10){
            return "Invalid phone no"
        }
        if (!Patterns.PHONE.matcher(phoneno).matches()){
            return "Invalid phone no"
        }
        return null
    }


    fun EmailEditSettingsBinding(binding: ActivityEditProfile2Binding) {
        binding.editProfileEmailEdittext2.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.editProfileEmail2.helperText = validEmailEditSettings(binding)
            }
        }

    }

    fun validEmailEditSettings(binding: ActivityEditProfile2Binding):String?{
        val emailtext = binding.editProfileEmailEdittext2.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()){
            return "Invalid Email Address"
        }
        return null
    }


    fun ageEditSettingsbinding(binding: ActivityEditProfile2Binding){
        binding.editProfileAgeEdittext2.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.editProfileAge2.helperText = validageEditSetting(binding)
            }
            if(!focused){
                binding.editProfileAge2.helperText = validageEditSetting(binding)
            }
        }
    }

    fun validageEditSetting(binding: ActivityEditProfile2Binding): String? {
        val age = binding.editProfileAgeEdittext2.text.toString()
        if (age.isEmpty()){
            return "fill age"
        }
        if( age.length ==3 && age > 110.toString()){
            return "Age should be less than 110"
        }
        if (!age[0].toString().matches(".*[1-9].*".toRegex())){
            return "Age cannot be started from 0"
        }

        return null
    }

    fun BloodGroupEditSettingbinding(binding: ActivityEditProfile2Binding){
        binding.editProfileBloodgroup2Autocomplete.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.editProfileBloodgroup2.helperText = validBloodGroupSettingsphoneno(binding)
            }
            if(!focused){
                binding.editProfileBloodgroup2.helperText = validBloodGroupSettingsphoneno(binding)
            }
        }
    }

    fun validBloodGroupSettingsphoneno(binding: ActivityEditProfile2Binding): String? {
        val bloodgroup = binding.editProfileBloodgroup2Autocomplete.text.toString()
//       if (!bloodgroup.matches("A+".toRegex()) || !bloodgroup.matches("O+".toRegex()) || !bloodgroup.matches("B+".toRegex())){
//           return "please enter A+,B+,AB+"
//       O
        if( bloodgroup != "A+" &&  bloodgroup != "B+" && bloodgroup != "O+" &&
             bloodgroup != "A-"  &&
            bloodgroup != "B-"&&
             bloodgroup != "O-" &&
              bloodgroup !="AB+" &&
               bloodgroup!= "AB-" ){
            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
        }
//        if ( bloodgroup.length==2 && bloodgroup != "B+"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if (bloodgroup.length==2 && bloodgroup != "O+"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if (bloodgroup.length==3 && bloodgroup != "AB+"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if ( bloodgroup.length==3 && bloodgroup != "AB-"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if ( bloodgroup.length==2 && bloodgroup != "A-"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if ( bloodgroup.length==2 && bloodgroup != "B-"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
//        if (bloodgroup.length==2 && bloodgroup != "O-"){
//            return "Plz enter A+,B+,O+,AB+,AB-,O-,B-,A-"
//        }
        return null
    }

    fun genderEditSettingsbinding(binding: ActivityEditProfile2Binding){
        binding.editProfileGender2Autocomplete.setOnFocusChangeListener { _, focused ->
            if (focused){
                binding.editProfileGender2.helperText = validgenderEditSetting(binding)
            }
            if(!focused){
                binding.editProfileGender2.helperText = validgenderEditSetting(binding)
            }
        }
    }

    fun validgenderEditSetting(binding: ActivityEditProfile2Binding): String? {
        val gender = binding.editProfileGender2Autocomplete.text.toString()
        if (gender.isEmpty()){
            return "fill gender"
        }
        if (gender != "Male" && gender != "Female" && gender !="Other"){
            return "Plz enter Male,Female,Other"
        }

        return null
    }
}

