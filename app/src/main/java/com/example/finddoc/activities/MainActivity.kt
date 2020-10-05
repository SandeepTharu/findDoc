package com.example.finddoc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.finddoc.R
import com.example.finddoc.fragments.AppointmentFragment
import com.example.finddoc.fragments.DoctorsListFragment
import com.example.finddoc.fragments.ProfileFragment
import com.example.finddoc.models.Doctor
import com.example.finddoc.utils.AppUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_info_bar.*
import kotlinx.android.synthetic.main.layout_info_bar.view.*

class MainActivity : AppCompatActivity(), DoctorsListFragment.OnDoctorsListFragmentInteraction,
    AppointmentFragment.OnAppointmentFragmentInteraction,
    ProfileFragment.OnProfileInteraction {
    private val mainActivityBackStack = "MAIN_ACTIVITY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFragment(DoctorsListFragment(this))

        imageViewInfoBarLogo.setOnClickListener {
            AppUtil.pushInAnimation(it, this)
            val fragment = supportFragmentManager.findFragmentById(R.id.mainActivityContainer)
            if(fragment is ProfileFragment) {
                textViewInfoBarTitle.text = "Find Doc"
                imageViewInfoBarLogo.setImageResource(R.drawable.ic_find_logo)
                supportFragmentManager.popBackStack()
            } else if(fragment is AppointmentFragment){
                textViewInfoBarTitle.text = "Profile"
                supportFragmentManager.popBackStack()
            }
        }
    }


    override fun onDoctorListItemClicked(doctor: Doctor) {
        textViewInfoBarTitle.text = "Profile"
        imageViewInfoBarLogo.setImageResource(R.drawable.ic_back_ios)
        startFragment(ProfileFragment(doctor, this))
    }

    override fun onBookAnAppointment(doctor: Doctor) {
        textViewInfoBarTitle.text = "Book an Appointment"
        startFragment(AppointmentFragment(doctor, this))
    }

    override fun onAppointmentBooked(doctor: Doctor) {
        Snackbar.make(
            findViewById(android.R.id.content),
            "Appointment Booked",
            Snackbar.LENGTH_LONG
        ).show()
        Handler(Looper.getMainLooper()).postDelayed({
            textViewInfoBarTitle.text = "Find Doc"
            imageViewInfoBarLogo.setImageResource(R.drawable.ic_find_logo)
            supportFragmentManager.popBackStack()
            supportFragmentManager.popBackStack()
        }, 2000)
    }

    private fun startFragment(fragment: Fragment) {
        AppUtil.startFragment(
            fragment, supportFragmentManager, R.id.mainActivityContainer, true,
            replace = false,
            addToBackStack = mainActivityBackStack
        )
    }
}