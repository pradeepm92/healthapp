package com.zeoner.healthapp.spash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zeoner.healthapp.R
import com.zeoner.healthapp.mobilenumber.view.MobileNumberActivity
import com.zeoner.healthapp.onboarding.OnBoardingActivity
import com.zeoner.vistacan.Sharedpref.Constants
import com.zeoner.vistacan.Sharedpref.SharedPref


class SplashActivity : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        handler.postDelayed(Runnable {
             var is_NEW_USER = SharedPref.get(this, Constants.IS_NEW_USER,false);
            Log.e("is_NEW_USER", is_NEW_USER .toString() )
            if (is_NEW_USER==true){
                val intent = Intent(this@SplashActivity, MobileNumberActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 3000)
    }
}