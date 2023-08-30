package com.zeoner.healthapp.speciality.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.card.MaterialCardView
import com.zeoner.healthapp.R
import com.zeoner.healthapp.home.model.FacilityModel

class SpecialityAdapter(private  var facilitylist:ArrayList<FacilityModel>):RecyclerView.Adapter<SpecialityAdapter.MyViewholder>() {


    class MyViewholder(view: View):RecyclerView.ViewHolder(view) {
        var cardView: MaterialCardView = view.findViewById(R.id.cardView)
        var speName: TextView = view.findViewById(R.id.speName)
        var icon: ImageView = view.findViewById(R.id.icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.speciality_item, parent, false)
        return MyViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.speName.text = facilitylist[position].name
        holder.icon.load(facilitylist[position].icon)
        holder.cardView.setOnClickListener {  }

    }

    override fun getItemCount(): Int {
      return facilitylist.size
    }
}