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
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_info_bar.*
import kotlinx.android.synthetic.main.layout_info_bar.view.*


class ProfileFragment(private val doctor: Doctor, val listener: OnProfileInteraction) : Fragment() {
    private val notAvailable = "Not available"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!doctor.imageURL.isNullOrEmpty())
            Glide.with(requireContext()).load(doctor.imageURL).centerCrop()
                .placeholder(R.drawable.ic_baseline_person_24).into(imageViewProfileDoctor)

        textViewProfileDoctorName.text = doctor.name
        textViewProfileEmailValue.text = doctor.email?: notAvailable
        textViewProfileMobileValue.text = doctor.mobile?: notAvailable
        textViewProfileCityValue.text = doctor.city?: notAvailable
        buttonBookAnAppointment.setOnClickListener {
            AppUtil.pushInAnimation(it,requireContext())
            listener.onBookAnAppointment(doctor)
        }
    }

    interface OnProfileInteraction{
        fun onBookAnAppointment(doctor: Doctor)
    }
}