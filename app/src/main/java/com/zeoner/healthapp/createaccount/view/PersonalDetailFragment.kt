package com.zeoner.healthapp.createaccount.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import coil.load
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import com.zeoner.healthapp.R
import com.zeoner.healthapp.createaccount.viewmodel.PersonalDetailViewModel
import com.zeoner.healthapp.databinding.FragmentPersonalDetailBinding
import com.zeoner.healthapp.mobilenumber.view.MobileNumberActivity

class PersonalDetailFragment : Fragment(),View.OnClickListener {
    lateinit var personalDetailViewModel: PersonalDetailViewModel
    lateinit var personalDetailBinding: FragmentPersonalDetailBinding
    lateinit var viewPager: ViewPager

    companion object {
        fun newInstance() = PersonalDetailFragment()
    }

    private lateinit var viewModel: PersonalDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        personalDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_detail, container, false)

        doInitContent()


        return personalDetailBinding.root



    }
    private fun doInitContent() {
        personalDetailBinding.submitBtn.setOnClickListener(this)
        personalDetailBinding.backBtn.setOnClickListener(this)
        personalDetailBinding.imgCreate.load(R.drawable.emailimg)
    }

    override fun onClick(v: View?) {
        when (v) {
            personalDetailBinding.submitBtn -> {
                val viewPager = activity?.findViewById<ViewPager>(R.id.viewPager_ca)
                viewPager?.let {
                    it.currentItem = it.currentItem + 1
                }
//                doCheckValidation()
            }
            personalDetailBinding.backBtn->{
                val Intent= Intent(requireContext(), MobileNumberActivity::class.java)
                startActivity(Intent)
                Animatoo.animateSlideLeft(requireContext())
            }
        }
    }


    @SuppressLint("ResourceType")
    private fun doCheckValidation() {
        val Email = personalDetailBinding.etxEmail.text.toString()
        val password = personalDetailBinding.etxPassword.text.toString()

        if (Email.isEmpty()) {
            personalDetailBinding.txtEmail.setHelperText("please enter Email")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            personalDetailBinding.txtEmail.setHelperText(" Invalid Email")
        } else if (password.isEmpty()) {
            personalDetailBinding.txtEmail.setHelperText("")
            personalDetailBinding.txtPassword.setHelperText("Please enter a password")
        } else if (!isPasswordValid(password)) {
            personalDetailBinding.txtPassword.setHelperText(" password contains 8 digits with numbers,smallcase,uppercase and one special character")
        } else {
            personalDetailBinding.txtPassword.setHelperText("")
            personalDetailBinding.txtEmail.setHelperText("")
//          doCallApi()
            val viewPager = activity?.findViewById<ViewPager>(R.id.viewPager_ca)
            viewPager?.let {
                it.currentItem = it.currentItem + 1
            }


        }
    }
    fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()_-])(?=\\S+\$).{8,}\$")
        return password.matches(passwordRegex)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonalDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}