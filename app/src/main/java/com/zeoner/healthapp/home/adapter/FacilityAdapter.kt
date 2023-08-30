package com.zeoner.healthapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zeoner.healthapp.R
import com.zeoner.healthapp.home.model.FacilityModel

class FacilityAdapter(private  var facilitylist:ArrayList<FacilityModel>):RecyclerView.Adapter<FacilityAdapter.MyViewholder>() {


    class MyViewholder(view: View):RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var icon: ImageView = view.findViewById(R.id.icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.facility_list_row, parent, false)
        return MyViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.name.text = facilitylist[position].name
        holder.icon.load(facilitylist[position].icon)


    }

    override fun getItemCount(): Int {
      return facilitylist.size
    }
}