package com.example.finddoc.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finddoc.R
import com.example.finddoc.models.Doctor
import com.example.finddoc.utils.AppUtil
import com.example.finddoc.views.CustomTextview

class DoctorsListAdapter(
    val context: Context,
    private val doctors: ArrayList<Doctor>,
    val listener: OnDoctorListInteraction
) : RecyclerView.Adapter<DoctorsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_doctors_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val doctor = doctors[position]
        if (position == 0) {
            val params =
                holder.cardViewOuterDoctorsList.layoutParams as ConstraintLayout.LayoutParams
            params.topMargin = AppUtil.dp2px(120f)
            holder.cardViewOuterDoctorsList.layoutParams = params
        }

        holder.textViewDoctorName.text = doctor.name
        holder.textViewRatingValue.text = doctor.ratings ?: ""
        holder.textViewDistanceValue.text = doctor.distance ?: ""

        holder.cardViewOuterDoctorsList.setOnClickListener {
            AppUtil.pushInAnimation(it, context)
            listener.onItemClicked(doctor)
        }
        if (!doctor.imageURL.isNullOrEmpty())
            Glide.with(context).load(doctor.imageURL).centerCrop()
                .placeholder(R.drawable.ic_baseline_person_24).into(holder.imageViewDoctor)
    }

    override fun getItemCount(): Int = doctors.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardViewOuterDoctorsList =
            itemView.findViewById<CardView>(R.id.cardViewOuterDoctorsList)!!
        val imageViewDoctor = itemView.findViewById<ImageView>(R.id.imageViewDoctor)!!
        val textViewDoctorName = itemView.findViewById<CustomTextview>(R.id.textViewDoctorName)!!
        val textViewRatingValue = itemView.findViewById<CustomTextview>(R.id.textViewRatingValue)!!
        val textViewDistanceValue =
            itemView.findViewById<CustomTextview>(R.id.textViewDistanceValue)!!
    }

    interface OnDoctorListInteraction {
        fun onItemClicked(doctor: Doctor)
    }
}