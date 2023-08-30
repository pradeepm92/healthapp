package com.zeoner.healthapp.onboarding


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.zeoner.healthapp.databinding.ActivityOnboardingFinishBinding
import com.zeoner.healthapp.mobilenumber.view.MobileNumberActivity
import com.zeoner.vistacan.Sharedpref.Constants
import com.zeoner.vistacan.Sharedpref.SharedPref

class OnboardingFinishActivity : AppCompatActivity() {
    private lateinit var btnStart: MaterialButton

    private lateinit var binding: ActivityOnboardingFinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        btnStart = binding.layoutStart
        btnStart.setOnClickListener {
            SharedPref.save(this,Constants.IS_NEW_USER,true);
            val intent = Intent(this@OnboardingFinishActivity, MobileNumberActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
