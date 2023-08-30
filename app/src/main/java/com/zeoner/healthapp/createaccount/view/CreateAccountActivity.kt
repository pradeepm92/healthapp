package com.zeoner.healthapp.createaccount.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.zeoner.healthapp.R
import com.zeoner.healthapp.createaccount.adapter.PageAdapter
import com.zeoner.healthapp.createaccount.viewmodel.CreateAccountViewModel
import com.zeoner.healthapp.databinding.ActivityCreateAccountBinding


class CreateAccountActivity : AppCompatActivity() {

    lateinit var accountBinding: ActivityCreateAccountBinding
    lateinit var createAccountViewModel: CreateAccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accountBinding=DataBindingUtil.setContentView(this,R.layout.activity_create_account)

        accountBinding.viewPagerCa.adapter  = PageAdapter(supportFragmentManager)
        accountBinding.viewPagerCa.setPagingEnabled(false)
        accountBinding.txtPersonal.setTextColor(resources.getColor(R.color.md_green_900))
        accountBinding.viewPersonal.setBackgroundTintList(
            ContextCompat.getColorStateList(
                this@CreateAccountActivity,
                R.color.md_green_900
            ))

        accountBinding.viewPagerCa.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageSelected(arg0: Int) {

                var position= (  accountBinding.viewPagerCa.getCurrentItem())
                Log.e("posssssssss", position.toString())
                if(position==0){
                    accountBinding.txtPersonal.setTextColor(resources.getColor(R.color.md_green_900))
                    accountBinding.viewPersonal.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            this@CreateAccountActivity,
                            R.color.md_green_900
                        ))
                }else{
                    accountBinding.txtPersonal.setTextColor(resources.getColor(R.color.light_grey))
                    accountBinding.viewPersonal.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            this@CreateAccountActivity,
                            R.color.md_green_900
                        ))

                }
                if(position==1){

                    accountBinding.txtIdProof.setTextColor(resources.getColor(R.color.md_green_900))
                    accountBinding.viewIdprrof.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            this@CreateAccountActivity,
                            R.color.md_green_900
                        ))

                }else{
                    accountBinding.txtIdProof.setTextColor(resources.getColor(R.color.light_grey))
                    accountBinding.viewIdprrof.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            this@CreateAccountActivity,
                            R.color.light_grey
                        ))
                }

            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {


            }

            override fun onPageScrollStateChanged(num: Int) {

            }
        })


    }


}

