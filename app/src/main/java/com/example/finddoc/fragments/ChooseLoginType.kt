package com.example.finddoc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finddoc.R
import com.example.finddoc.utils.AppUtil
import kotlinx.android.synthetic.main.fragment_choose_login_type.*

class ChooseLoginType(val listener: OnChooseLoginTypeInteraction): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_login_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonDoctor.setOnClickListener {
            AppUtil.pushInAnimation(it,context!!)
            listener.onLoginAs()
        }

        buttonPatient.setOnClickListener {
            AppUtil.pushInAnimation(it,context!!)
            listener.onLoginAs()
        }

        buttonRegister.setOnClickListener {
            AppUtil.pushInAnimation(it,context!!)
        }
    }

    interface OnChooseLoginTypeInteraction{
        fun onLoginAs()
    }
}