package com.zeoner.healthapp.createaccount.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.thecode.onboardingviewagerexamples.utils.Animatoo
import com.zeoner.healthapp.R
import com.zeoner.healthapp.createaccount.viewmodel.IdProofViewModel
import com.zeoner.healthapp.databinding.FragmentIdProofBinding
import com.zeoner.healthapp.mobilenumber.view.MobileNumberActivity
import java.util.*

class IdProofFragment : Fragment(), View.OnClickListener {
    lateinit var idProofViewModel: IdProofViewModel
    lateinit var idbinding: FragmentIdProofBinding
    private var mDate = 0
    private var mMonth: Int = 0
    private var mYear: Int = 0
    var passportbtn_isselect: Boolean = false
    var nationalidbtn_isselect: Boolean = true
    var male_isselect: Boolean = true
    var female_isselect: Boolean = false
    var mCalendar = Calendar.getInstance()
    var gender = "male"


    companion object {
        fun newInstance() = IdProofFragment()
    }

    private lateinit var viewModel: IdProofViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        idbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_id_proof, container, false)

        doInitContent()


        return idbinding.root


    }

    private fun doCheckValidation() {

        if (nationalidbtn_isselect.equals(true)) {
            if (idbinding.idEdtxt.text!!.isEmpty()) {
                idbinding.idTxt.setHelperText("please enter the id number")
            } else if (idbinding.firstnameEdtxt.text!!.isEmpty()) {
                idbinding.passportTxt.setHelperText("")
                idbinding.idTxt.setHelperText("")
                idbinding.firstnameTxt.setHelperText("please enter the firstname")
            } else if (idbinding.lastnameEdtxt.text!!.isEmpty()) {
                idbinding.firstnameTxt.setHelperText("")
                idbinding.lastnameTxt.setHelperText("please enter the Lastname")
            } else if (idbinding.birthEdtxt.text!!.isEmpty()) {
                idbinding.lastnameTxt.setHelperText("")
                idbinding.birthTxt.setHelperText("please enter the  birth date")
            } else {

//            doCallApi()
                Toast.makeText(requireContext(), "validation over", Toast.LENGTH_SHORT).show()
            }
        }
        if (passportbtn_isselect.equals(true)) {
            if (idbinding.passportEdtxt.text!!.isEmpty()) {
                idbinding.passportTxt.setHelperText("please enter the passport Number")
            } else if (idbinding.firstnameEdtxt.text!!.isEmpty()) {
                idbinding.passportTxt.setHelperText("")
                idbinding.idTxt.setHelperText("")
                idbinding.firstnameTxt.setHelperText("please enter the firstname")
            } else if (idbinding.lastnameEdtxt.text!!.isEmpty()) {
                idbinding.firstnameTxt.setHelperText("")
                idbinding.lastnameTxt.setHelperText("please enter the Lastname")
            } else if (idbinding.birthEdtxt.text!!.isEmpty()) {
                idbinding.lastnameTxt.setHelperText("")
                idbinding.birthTxt.setHelperText("please enter the  birth date")
            } else {

//                idbinding.passportTxt.setHelperText("")
//                idbinding.idTxt.setHelperText("")
//                idbinding.firstnameTxt.setHelperText("")
//                idbinding.lastnameTxt.setHelperText("")
                idbinding.birthTxt.setHelperText("")
//            doCallApi()
                Toast.makeText(requireContext(), "validation over", Toast.LENGTH_SHORT).show()
            }

        }


    }


    private fun doInitContent() {

        idbinding.birthEdtxt.setOnClickListener(this)
        idbinding.nationalBtn.setOnClickListener(this)
        idbinding.passwordBtn.setOnClickListener(this)
        idbinding.signinBtn.setOnClickListener(this)
        idbinding.maleLayout.setOnClickListener(this)
        idbinding.femaleLayout.setOnClickListener(this)
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View?) {
        when (v) {
            idbinding.birthEdtxt -> {
                doOpenDateDialog()
            }
            idbinding.nationalBtn -> {
                nationalidbtn_isselect = true
                passportbtn_isselect = false
                if (nationalidbtn_isselect.equals(true)) {
                    idbinding.passportLayout.visibility = View.GONE
                    idbinding.idLayout.visibility = View.VISIBLE
                    idbinding.nationalBtn.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_deep_purple_600
                        )
                    )
                    idbinding.passwordBtn.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_deep_purple_200
                        )
                    )


                }
            }
            idbinding.passwordBtn -> {
                passportbtn_isselect = true
                nationalidbtn_isselect = false
                idbinding.passportLayout.visibility = View.VISIBLE
                idbinding.idLayout.visibility = View.GONE
                idbinding.nationalBtn.setBackgroundTintList(
                    ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.md_deep_purple_200
                    )
                )
                idbinding.passwordBtn.setBackgroundTintList(
                    ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.md_deep_purple_600
                    )
                )

            }
            idbinding.maleLayout -> {
                male_isselect = true
                female_isselect = false
                if (male_isselect.equals(true)) {

                    idbinding.maleLayout.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_indigo_A700
                        )
                    )
                    idbinding.txtMale.setTextColor(resources.getColor(R.color.md_white_1000))
                    gender = "male"

                    idbinding.femaleLayout.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_blue_50
                        )
                    )
                    idbinding.txtFemale.setTextColor(resources.getColor(R.color.dark_blue))
                }
                Log.e("gender", gender)
            }
            idbinding.femaleLayout -> {
                male_isselect = false
                female_isselect = true
                if (female_isselect.equals(true)) {
                    idbinding.femaleLayout.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_indigo_A700
                        )
                    )
                    idbinding.txtFemale.setTextColor(resources.getColor(R.color.md_white_1000))

                    gender = "female"
                    idbinding.maleLayout.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.md_blue_50
                        )
                    )
                    idbinding.txtMale.setTextColor(resources.getColor(R.color.dark_blue))
                    Log.e("gender", gender)
                }


            }
            idbinding.signinBtn -> {
                doCheckValidation()
            }


        }

    }

    private fun goToLoginScreen() {
        val intent = Intent(requireContext(), MobileNumberActivity::class.java)
        startActivity(intent)
        Animatoo.animateSlideLeft(requireContext())
    }


    private fun doOpenDateDialog() {
        val calendar = Calendar.getInstance()
        mDate = calendar[Calendar.DATE]
        mMonth = calendar[Calendar.MONTH]
        mYear = calendar[Calendar.YEAR]
        val datePickerDialog = DatePickerDialog(
            requireContext(), android.R.style.Theme_DeviceDefault_Dialog,
            { datePicker, year, month, date ->
                var month = month
                month = month + 1
                idbinding.birthEdtxt.setText("$date-$month-$year")
                mCalendar.set(year, month - 1, date)
            }, mYear, mMonth, mDate
        )
        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePickerDialog.show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(IdProofViewModel::class.java)
        // TODO: Use the ViewModel
    }

}