package com.zeoner.healthapp.otp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import coil.load
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import com.zeoner.healthapp.R
import com.zeoner.healthapp.databinding.ActivityOtpactivityBinding
import com.zeoner.healthapp.home.view.HomeActivity
import com.zeoner.healthapp.home.view.HomeFragment
import com.zeoner.healthapp.mobilenumber.view.MobileNumberActivity
import com.zeoner.healthapp.otp.viewmodel.OTPViewmodel

class OTPActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var otpbinding: ActivityOtpactivityBinding
    lateinit var otpviewmodel: OTPViewmodel
    var mobilenumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        otpbinding = DataBindingUtil.setContentView(this, R.layout.activity_otpactivity)
        otpbinding.submitBtn.setOnClickListener(this)
        otpbinding.editicon.setOnClickListener(this)
        otpbinding.resendotp.setOnClickListener(this)
        otpbinding.otpimage.load(R.drawable.otpimg)
        doGetIntent()
        otpbinding.mobnum.text = mobilenumber
    }

    private fun doGetIntent() {
        mobilenumber = intent.getStringExtra("mobnum").toString()

    }

    private fun startTimer() {
        otpbinding.resendotp.isClickable = false
        otpbinding.resendotp.setTextColor(
            ContextCompat.getColor(
                this,
                R.color.md_red_900
            )
        )
        object : CountDownTimer(60000, 1000) {
            var secondsLeft = 0
            override fun onTick(ms: Long) {
                if (Math.round(ms.toFloat() / 1000.0f) != secondsLeft) {
                    secondsLeft = Math.round(ms.toFloat() / 1000.0f)
                    otpbinding.resendotp.text =
                        resources.getString(R.string.resent_otp) + "( $secondsLeft )"
                }
            }

            override fun onFinish() {
                otpbinding.resendotp.isClickable = true
                otpbinding.resendotp.text = resources.getString(R.string.resent_otp)
                otpbinding.resendotp.setTextColor(
                    ContextCompat.getColor(
                        this@OTPActivity,
                        R.color.dark_blue
                    )
                )
            }
        }.start()
    }

    override fun onClick(v: View?) {
        when (v) {
            otpbinding.submitBtn -> {
                val Intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(Intent)
            }
            otpbinding.editicon -> {
                onBackPressed()
            }
            otpbinding.resendotp -> {
                startTimer()
            }
        }
    }
}