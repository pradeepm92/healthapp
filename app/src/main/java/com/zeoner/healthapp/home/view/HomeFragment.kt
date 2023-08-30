package com.zeoner.healthapp.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.zeoner.healthapp.R
import com.zeoner.healthapp.databinding.FragmentHomeBinding
import com.zeoner.healthapp.home.adapter.AppoinmentTodayAdapter
import com.zeoner.healthapp.home.adapter.FacilityAdapter
import com.zeoner.healthapp.home.adapter.TopDocterAdapter
import com.zeoner.healthapp.home.model.AppointmentListModel
import com.zeoner.healthapp.home.model.FacilityModel
import com.zeoner.healthapp.home.model.DocterlistModel
import com.zeoner.healthapp.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    lateinit var appoinmentTodayAdapter: AppoinmentTodayAdapter
    lateinit var facilityAdapter: FacilityAdapter
    lateinit var topDocterAdapter: TopDocterAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setFacility()
        setAppoinmentData()
        setTopDocter()
        binding.profileImg.load(R.drawable.profile)

        binding.specialityViewAll.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.specialityFragment)
        }
        return binding.root


    }

    private fun setFacility() {
        var facilitylist = ArrayList<FacilityModel>()
        var FacilityModel = FacilityModel("covid",R.drawable.coronavirus,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("hospital",R.drawable.hospital,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("pills",R.drawable.pill,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("cardio",R.drawable.heart,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("skincare specialist",R.drawable.wave,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("Blood Test",R.drawable.microscope,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("Dental",R.drawable.mirror,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("skincare specialist",R.drawable.wave,)
        facilitylist.add(FacilityModel)
        FacilityModel = FacilityModel("hospital",R.drawable.hospital,)
        facilitylist.add(FacilityModel)
        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerHorizontal.layoutManager = linearLayoutManager
        binding.recyclerHorizontal.setHasFixedSize(false);

        facilityAdapter = FacilityAdapter(facilitylist )
        binding.recyclerHorizontal.adapter = facilityAdapter
    }


    private fun setAppoinmentData() {
       var appointmentlist = ArrayList<AppointmentListModel>()
        var AppointmentListModel = AppointmentListModel(R.drawable.doc1,"sounder Rajan","cardialist","Monday, july 22","11:00am-12:00am")
        appointmentlist.add(AppointmentListModel)
        AppointmentListModel = AppointmentListModel(R.drawable.doc2,"kareem muhammed","Dental specialist","Monday, july 22","11:00am-12:00am")
        appointmentlist.add(AppointmentListModel)
         AppointmentListModel = AppointmentListModel(R.drawable.doc3,"Ishwarya ramanujam","Eye specialist","Monday, july 22","11:00am-12:00am")
        appointmentlist.add(AppointmentListModel)


        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
       binding.recyclerTdyAppointment.setLayoutManager(linearLayoutManager)
        binding.recyclerTdyAppointment.setHasFixedSize(false);

        appoinmentTodayAdapter = AppoinmentTodayAdapter(appointmentlist )
        binding.recyclerTdyAppointment.setAdapter(appoinmentTodayAdapter)
    }

    fun setTopDocter() {
        var docterlist = ArrayList<DocterlistModel>()
        var DocterlistModel = DocterlistModel(R.drawable.doc1,"Johnson william","cardialist","Madurai","60 review","2.0")
        docterlist.add(DocterlistModel)
        DocterlistModel = DocterlistModel(R.drawable.doc2,"Wills Smith","Skin care","Trichy","30 review","3.0")
        docterlist.add(DocterlistModel)
        DocterlistModel = DocterlistModel(R.drawable.doc3,"Mubeena Mustabha","Neuro specialist","chennai","20 review","5.0")
        docterlist.add(DocterlistModel)
        DocterlistModel = DocterlistModel(R.drawable.doc1,"ramanujam rangan","general medicine","coimbatore ","60 review","3.0")
        docterlist.add(DocterlistModel)

        val linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.recyclerTopdocter.layoutManager = linearLayoutManager
        binding.recyclerTopdocter.setHasFixedSize(false)

        topDocterAdapter = TopDocterAdapter(docterlist )
        binding.recyclerTopdocter.adapter = topDocterAdapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}