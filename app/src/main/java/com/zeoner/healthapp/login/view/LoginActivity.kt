package com.zeoner.healthapp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import coil.load
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import com.zeoner.healthapp.R
import com.zeoner.healthapp.databinding.ActivityLoginBinding
import com.zeoner.healthapp.forgotpassword.view.ForgotPasswordActivity
import com.zeoner.healthapp.home.view.HomeActivity
import com.zeoner.healthapp.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var loginbinding: ActivityLoginBinding
    lateinit var loginviewmodel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginbinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        doInitContent()
    }

    private fun doInitContent() {
        loginbinding.imgLogin.load(R.drawable.passimg)
        loginbinding.backBtn.setOnClickListener(this)
        loginbinding.submitBtn.setOnClickListener(this)
        loginbinding.forotpassword.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v){
           loginbinding.backBtn->{
               onBackPressed()
           }

           loginbinding.forotpassword->{
               val intent = Intent(applicationContext, ForgotPasswordActivity::class.java)
               startActivity(intent)
               Animatoo.animateSlideLeft(this)
           }
           loginbinding.submitBtn->{
               doCheckValidation()
           }
       }
    }

    private fun doCheckValidation() {
        val password = loginbinding.etxPassword.text.toString()

        if (password.isEmpty()) {
            loginbinding.txtPassword.setHelperText("Please enter a password")
        } else if (!isPasswordValid(password)) {
            loginbinding.txtPassword.setHelperText(" password contains 8 digits with numbers,smallcase,uppercase and one special character")
        } else {
            // Password is valid
            loginbinding.txtPassword.setHelperText("")
            val intent = Intent(applicationContext, HomeActivity::class.java)

            startActivity(intent)
            Animatoo.animateSlideLeft(this)
//          doCallApi()
        }
    }

    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!*()-_@#$%^&+=])(?=\\S+\$).{8,}\$")
        return password.matches(passwordRegex)
    }
}