package com.example.finddoc.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.finddoc.FindDocBaseActivity
import com.example.finddoc.R

class SplashScreenActivity : FindDocBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            startOnboarding()
        },2000)
    }

    private fun startOnboarding(){
        val intent = Intent(this,OnboardingActivity::class.java)
        startActivity(intent)
    }
}