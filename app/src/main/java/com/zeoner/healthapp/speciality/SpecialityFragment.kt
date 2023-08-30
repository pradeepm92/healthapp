package com.zeoner.healthapp.speciality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.zeoner.healthapp.R
import com.zeoner.healthapp.databinding.FragmentSpecialityBinding
import com.zeoner.healthapp.home.model.FacilityModel
import com.zeoner.healthapp.speciality.adapter.SpecialityAdapter


class SpecialityFragment : Fragment(), View.OnClickListener {
    var specialitylist = ArrayList<FacilityModel>()

    lateinit var specialityBinding: FragmentSpecialityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        specialityBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_speciality, container, false)
        specialityBinding.backBtn.setOnClickListener(this)
        specialityBinding.recyclerview.hasFixedSize()
        specialityBinding.recyclerview.layoutManager=GridLayoutManager(requireContext(),2)

        setData()
        specialityBinding.recyclerview.setHasFixedSize(false)
        val specialityAdapter = SpecialityAdapter(specialitylist )
        specialityBinding.recyclerview.adapter = specialityAdapter

        return specialityBinding.root

    }

    fun setData(){
        specialitylist.add(FacilityModel("covid",R.drawable.coronavirus))
        specialitylist.add(FacilityModel("hospital",R.drawable.hospital))
        specialitylist.add(FacilityModel("pills",R.drawable.pill))
        specialitylist.add(FacilityModel("cardio",R.drawable.heart))
        specialitylist.add(FacilityModel("skincare specialist",R.drawable.wave))
        specialitylist.add(FacilityModel("Blood Test",R.drawable.microscope))
        specialitylist.add(FacilityModel("Dental",R.drawable.mirror))
        specialitylist.add(FacilityModel("skincare specialist",R.drawable.wave))
        specialitylist.add(FacilityModel("hospital",R.drawable.hospital))
    }

    override fun onClick(v: View?) {
        when (v){
            specialityBinding.backBtn->{
              Navigation.findNavController(v).popBackStack()
          }
        }
    }

}