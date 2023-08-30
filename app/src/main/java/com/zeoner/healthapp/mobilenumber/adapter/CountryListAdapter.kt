package com.zeoner.healthapp.mobilenumber.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.zeoner.healthapp.R
import com.zeoner.healthapp.utils.Country


class CountryListAdapter(private var countries: List<Country>,
                         private  val itemselect: itemSelect, private val dialog: BottomSheetDialog
)
    : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {


    fun search(selectedCountriesList: List<Country>) {
        countries = selectedCountriesList
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val countryname: TextView = itemView.findViewById(R.id.countryname)
        val countrycode: TextView = itemView.findViewById(R.id.countrycode)
        val country_flag: ImageView = itemView.findViewById(R.id.country_flag)



    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.countrybottom_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val country = countries[position]

        holder.countrycode.text = country.getCode()
        holder.countryname.text = country.name
        holder.country_flag.setImageResource(country.flag)

        holder.itemView.setOnClickListener {
            itemselect.itemselect(position,dialog)
        }

    }


    override fun getItemCount(): Int {
        return countries.size
    }


    interface itemSelect {
        fun itemselect(position:Int,dialog:BottomSheetDialog)
    }


}
