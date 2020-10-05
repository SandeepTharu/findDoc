package com.example.finddoc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.finddoc.FindDocBaseActivity
import com.example.finddoc.R
import com.example.finddoc.fragments.ChooseLoginType
import com.example.finddoc.fragments.LoginPageFragment
import com.example.finddoc.utils.AppUtil

class OnboardingActivity : FindDocBaseActivity(), ChooseLoginType.OnChooseLoginTypeInteraction, LoginPageFragment.OnLoginPageInteraction {
    private val onboardingBackStack = "ONBOARDING"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        startLoginAsScreen()
    }

    private fun startLoginAsScreen(){
        startFragment(ChooseLoginType(this))
    }

    override fun onLoginAs() {
        startFragment(LoginPageFragment(this))
    }

    override fun onLogin() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun startFragment(fragment: Fragment){
        AppUtil.startFragment(fragment, supportFragmentManager, R.id.onboardingMainContainer, true,
            replace = false,
            addToBackStack = onboardingBackStack
        )
    }
}