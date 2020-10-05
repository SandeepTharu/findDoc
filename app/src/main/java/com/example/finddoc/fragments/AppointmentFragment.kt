package com.example.finddoc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.finddoc.R
import com.example.finddoc.models.Doctor
import com.example.finddoc.utils.AppUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_appointment_screen.*
import kotlinx.android.synthetic.main.layout_info_bar.view.*

class AppointmentFragment(val doctor: Doctor, val listener: OnAppointmentFragmentInteraction): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_appointment_screen, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if(!doctor.imageURL.isNullOrEmpty())
            Glide.with(requireContext()).load(doctor.imageURL).centerCrop().into(imageViewAppointmentDoctor)

        textViewAppointmentDoctorName.text = doctor.name
        textViewAppointmentRatingValue.text = doctor.ratings?: "Not available"

        buttonAppointmentBook.setOnClickListener {
            AppUtil.pushInAnimation(it,requireContext())
            listener.onAppointmentBooked(doctor)
        }
    }

    interface OnAppointmentFragmentInteraction{
        fun onAppointmentBooked(doctor: Doctor)
    }
}