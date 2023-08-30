package com.zeoner.healthapp.forgotpassword.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.zeoner.healthapp.R
import com.zeoner.healthapp.databinding.ActivityForgotPasswordBinding
import com.zeoner.healthapp.forgotpassword.viewmodel.ForgotViewModel

class ForgotPasswordActivity : AppCompatActivity() {
 lateinit var  forgotbinding:ActivityForgotPasswordBinding
 lateinit var forgotViewModel: ForgotViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        forgotbinding.forgotImg.load(R.drawable.forgotimg)
    }
}