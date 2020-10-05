package com.example.finddoc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.finddoc.R
import com.example.finddoc.utils.AppUtil
import kotlinx.android.synthetic.main.fragment_login_page.*

class LoginPageFragment(val listener: OnLoginPageInteraction): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_page,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonLogin.setOnClickListener {
            AppUtil.pushInAnimation(it,context!!)
            listener.onLogin()
        }
    }

    interface OnLoginPageInteraction{
        fun onLogin()
    }
}