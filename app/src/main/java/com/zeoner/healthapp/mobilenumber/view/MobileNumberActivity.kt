package com.zeoner.healthapp.mobilenumber.view

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import com.zeoner.healthapp.R
import com.zeoner.healthapp.createaccount.view.CreateAccountActivity
import com.zeoner.healthapp.databinding.ActivityMobilenumberBinding
import com.zeoner.healthapp.login.view.LoginActivity
import com.zeoner.healthapp.mobilenumber.adapter.CountryListAdapter
import com.zeoner.healthapp.mobilenumber.viewmodel.MobileNumberViewModel
import com.zeoner.healthapp.otp.view.OTPActivity
import com.zeoner.healthapp.utils.Country
import com.zeoner.healthapp.utils.PhoneValidateResponse
import java.util.*


class MobileNumberActivity : AppCompatActivity(), View.OnClickListener,
    CountryListAdapter.itemSelect {
    lateinit var binding: ActivityMobilenumberBinding
    lateinit var vmobilenumberviewmodel: MobileNumberViewModel
    lateinit var adapter: CountryListAdapter
    private lateinit var dialog: BottomSheetDialog
    private val countriesList: ArrayList<Country> = ArrayList()
    private val selectedCountriesList: ArrayList<Country> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobilenumber)
        binding.error.visibility=View.GONE
        doInitContent()
    }

    private fun doInitContent() {
        binding.otpBtn.setOnClickListener(this)
        binding.countrylayout.setOnClickListener(this)
        binding.createaccount.setOnClickListener(this)

    }

    fun doInitBottomSheet() {
        val view: View
        dialog = BottomSheetDialog(this)
        view = layoutInflater.inflate(R.layout.bottomsheet_country, null)
        dialog.setContentView(view)
        val country_code_picker_search = view.findViewById<AppCompatEditText>(R.id.search)
        val country_code_picker_recyclerview =
            view.findViewById(R.id.tlbottom_recycler) as RecyclerView
        val btn_close = view.findViewById<AppCompatImageButton>(R.id.close)
        if (country_code_picker_search.text!!.isEmpty()) {
            doLoadCountryList()
        }

        adapter = CountryListAdapter(
            countriesList, this, dialog
        )


        country_code_picker_recyclerview.layoutManager = LinearLayoutManager(this)
        country_code_picker_recyclerview.adapter = adapter


        country_code_picker_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                search(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {

            }
        })

        btn_close.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
        dialog.setCanceledOnTouchOutside(true)
    }

    private fun doLoadCountryList() {
        countriesList.clear()
        selectedCountriesList.clear()
        countriesList.addAll(Country.COUNTRIES)
        selectedCountriesList.addAll(countriesList)
    }

    override fun itemselect(position: Int, dialog: BottomSheetDialog) {
        val country = selectedCountriesList[position]
        binding.CountryCode.text = country.dialCode
        binding.countryFlag.setImageResource(country.flag)
        dialog.dismiss()

    }

    fun search(text: String) {
        selectedCountriesList.clear()
        for (country in countriesList) {

            if (country.name!!.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                selectedCountriesList.add(country)
                Log.e("selectedCountriesList", selectedCountriesList.toString())
            }

        }
        adapter.search(selectedCountriesList)

    }


    override fun onClick(v: View?) {

        when (v) {
            binding.otpBtn -> {
                val countrycode = binding.CountryCode.text.toString()
                val mobilenum = binding.mobnumEd.text.toString()
                val code_mob = countrycode + "-" + mobilenum
                Log.e("countrycode", countrycode)

                val validationResult = isPhoneNumberValidate(mobilenum, countrycode)

                if (mobilenum.isEmpty()) {
                    binding.error.text="Enter phone number"
                    binding.error.visibility=View.VISIBLE
                } else if (validationResult != null) {
                    if (validationResult.isValid) {
                        binding.error.visibility=View.GONE
                        val intent = Intent(applicationContext, OTPActivity::class.java)
                        intent.putExtra("mobnum", code_mob)
                        startActivity(intent)
                        Animatoo.animateSlideLeft(this)
                    } else {
                        binding.error.text="Invalid phone number"
                        binding.error.visibility=View.VISIBLE
                    }
                }

            }
            binding.countrylayout -> {
                dialog = BottomSheetDialog(this)
                if (!dialog.isShowing) {
                    doInitBottomSheet()
            }
        }
            binding.createaccount ->{
                val intent = Intent(applicationContext, CreateAccountActivity::class.java)
                startActivity(intent)
                Animatoo.animateSlideLeft(this)
            }
        }
    }

    private fun isPhoneNumberValidate(mobNumber: String?, countryCode: String): PhoneValidateResponse? {
        val phoneNumberValidate = PhoneValidateResponse()
        val phoneNumberUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
        var phoneNumber: Phonenumber.PhoneNumber? = null
        var finalNumber = false
        var isMobile: PhoneNumberUtil.PhoneNumberType? = null
        var isValid = false
        try {
            val isoCode: String = phoneNumberUtil.getRegionCodeForCountryCode(countryCode.toInt())
            phoneNumber = phoneNumberUtil.parse(mobNumber, isoCode)
            isValid = phoneNumberUtil.isValidNumber(phoneNumber)
            isMobile = phoneNumberUtil.getNumberType(phoneNumber)
            phoneNumberValidate.code =phoneNumber.countryCode.toString()
            phoneNumberValidate.phone =phoneNumber.nationalNumber.toString()
            phoneNumberValidate.isValid = false
        } catch (e: NumberParseException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        if (isValid && (PhoneNumberUtil.PhoneNumberType.MOBILE === isMobile || PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE === isMobile)) {
            finalNumber = true
            phoneNumberValidate.isValid = true
        }
        return phoneNumberValidate
    }
}


