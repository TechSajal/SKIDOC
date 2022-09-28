package com.example.humandisclass.Activity.Helper

import android.util.Patterns
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
        binding.textInputPasswordSignin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.containerPasswordLogin.helperText = validPassword(binding)
            }
        }

    }

    fun validPassword(binding: ActivityLoginBinding):String?{
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

    fun phonenologinbinding(binding: ActivitySigninBinding){
        binding.textInputPhoneSignin.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.containerPhonenoSignin.helperText = validphoneno(binding)
            }
        }
    }

    private fun validphoneno(binding: ActivitySigninBinding): String? {
    val phoneno = binding.textInputPhoneSignin.text.toString()
        if(!Patterns.PHONE.matcher(phoneno).matches()){
            return "Plz Enter Valid PhoneNo."
        }
        if(!phoneno.matches(".*[0-9].*".toRegex())){
            return "Must be all Digits"
        }
        if(phoneno.length!=10){
            return "Must be 10 Digits"
        }

    return null

    }
}