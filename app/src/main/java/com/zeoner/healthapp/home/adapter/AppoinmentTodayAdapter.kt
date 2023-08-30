package com.zeoner.healthapp.home.adapter

import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.zeoner.healthapp.R
import com.zeoner.healthapp.home.model.AppointmentListModel

class AppoinmentTodayAdapter(private val appointmentlist: ArrayList<AppointmentListModel>) :
    RecyclerView.Adapter<AppoinmentTodayAdapter.MyViewholder>() {
    class MyViewholder(view: View) : RecyclerView.ViewHolder(view) {
        var doctername: TextView = view.findViewById(R.id.doctername)
        var profileimg: ImageView = view.findViewById(R.id.profile_photo)
        var specialistname: TextView = view.findViewById(R.id.specalist_name)
        var Date: TextView = view.findViewById(R.id.calendar)
        var Time: TextView = view.findViewById(R.id.clock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.appoinment_list_row, parent, false)
        return MyViewholder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.doctername.text = appointmentlist[position].doctername
        holder.specialistname.text = appointmentlist[position].specialist
        holder.Date.text = appointmentlist[position].date
        holder.Time.text = appointmentlist[position].Time
        holder.profileimg.load(appointmentlist[position].profileimage)

//        if(!appointmentlist[position]?.profileimage.isNullOrEmpty()){
//            val imageByteArray: ByteArray = Base64.decode(appointmentlist[position]!!.profileimage, Base64.DEFAULT)
//            Glide.with(context).load(imageByteArray).placeholder(R.drawable.no_pictures).into(holder.img_item)
//        }

    }

    override fun getItemCount(): Int {
        return appointmentlist.size
    }
}