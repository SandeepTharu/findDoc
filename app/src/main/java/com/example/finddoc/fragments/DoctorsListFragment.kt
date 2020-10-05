package com.example.finddoc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finddoc.R
import com.example.finddoc.adapters.DoctorsListAdapter
import com.example.finddoc.models.Doctor
import kotlinx.android.synthetic.main.fragment_doctors_list.*

class DoctorsListFragment(val listener: OnDoctorsListFragmentInteraction) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctors_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewDoctorsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewDoctorsList.adapter = DoctorsListAdapter(
            requireContext(),
            getDoctorsList(),
            object : DoctorsListAdapter.OnDoctorListInteraction {
                override fun onItemClicked(doctor: Doctor) {
                    listener.onDoctorListItemClicked(doctor)
                }
            })
    }


    private fun getDoctorsList(): ArrayList<Doctor> {
        val doctors = ArrayList<Doctor>()
        doctors.add(
            Doctor(
                1,
                "https://pbs.twimg.com/profile_images/1123469231637581825/ID-dwDKx_400x400.jpg",
                "Dr. Binish Manandhar",
                "5.0",
                "3.2km",
                "binish.manandhar23@gmail.com",
                "9812345678",
                "Kathmandu"
            )
        )
        doctors.add(
            Doctor(
                2,
                "https://media-exp1.licdn.com/dms/image/C5603AQGOgwfCbFcmuQ/profile-displayphoto-shrink_200_200/0?e=1602115200&v=beta&t=aqUA6LiNUpawY2V4_XXvaWBOHJ-jycdemG2dVjnoGWc",
                "Dr. Sandeep Tharu",
                "5.0",
                "23.8km",
                "sandeep.tharu@gmail.com",
                "9812345678",
                "Kathmandu"
            )
        )
        doctors.add(
            Doctor(
                3,
                "https://pbs.twimg.com/profile_images/1074553182607605760/RHJML5IC.jpg",
                "Dr. Ronit Prajapati",
                "4.0",
                "6.3km",
                "ronitprajapati86@gmail.com",
                "9812345678",
                "Kathmandu"
            )
        )
        doctors.add(
            Doctor(
                4,
                "https://media-exp1.licdn.com/dms/image/C4E03AQEUsXrRKJVzJw/profile-displayphoto-shrink_200_200/0?e=1604534400&v=beta&t=cy6Xu8Wf1bmLhU709f72kp-EG8KtJDuf8SMlR9R9vbE",
                "Dr. Sangeeta Waiba",
                "4.5",
                "13.1km",
                "sangeeta.waiba@gmail.com",
                "9812345678",
                "Sydney"
            )
        )
        doctors.add(
            Doctor(
                5,
                "https://images.theconversation.com/files/304957/original/file-20191203-66986-im7o5.jpg",
                "Dr. Saru Magar",
                "3.0",
                "10.7km",
                "saru.magar@gmail.com",
                "9812345678",
                "Butwal"
            )
        )
        doctors.add(
            Doctor(
                6,
                "https://cdn.sanity.io/images/0vv8moc6/hcplive/0ebb6a8f0c2850697532805d09d4ff10e838a74b-200x200.jpg",
                "Dr. Ben Lepchen",
                "3.5",
                "15.9km"
            )
        )
        return doctors
    }

    interface OnDoctorsListFragmentInteraction {
        fun onDoctorListItemClicked(doctor: Doctor)
    }
}