package com.zeoner.healthapp.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zeoner.healthapp.R
import com.zeoner.healthapp.home.model.DocterlistModel

class TopDocterAdapter(private val docterlist:ArrayList<DocterlistModel>):
    RecyclerView.Adapter<TopDocterAdapter.MyViewholder>() {
    class MyViewholder (view: View): RecyclerView.ViewHolder(view) {
        var doctername: TextView = view.findViewById(R.id.doctername)
        var profileimg: ImageView = view.findViewById(R.id.profile_photo)
        var speciality: TextView = view.findViewById(R.id.speciality)
        var cityname: TextView = view.findViewById(R.id.city)
        var review: TextView = view.findViewById(R.id.review)
        var rating: TextView = view.findViewById(R.id.rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.topdocter_list_row, parent, false)
        return MyViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.doctername.text = docterlist[position].doctername
        holder.speciality.text = docterlist[position].speciality
        holder.cityname.text = docterlist[position].cityname
        holder.review.text = docterlist[position].review
        holder.rating.text = docterlist[position].rating
        holder.profileimg.load(docterlist[position].profileimage)

    }

    override fun getItemCount(): Int {
       return docterlist.size
    }
}