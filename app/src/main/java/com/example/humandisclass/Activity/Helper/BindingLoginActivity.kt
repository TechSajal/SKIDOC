package com.example.humandisclass.Activity.Helper

import android.util.Patterns
import com.example.humandisclass.R
import com.example.humandisclass.databinding.ActivityInfromationBinding
import com.example.humandisclass.databinding.ActivityLoginBinding
import com.example.humandisclass.databinding.ActivitySigninBinding
import com.google.android.material.textfield.TextInputLayout

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
            return "plz enter correct name"
        }
        if(!fullname.matches(".*[a-z].*".toRegex())){
            return "plz enter correct name"
        }


        return null
    }
}

