package com.example.finddoc.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.HapticFeedbackConstants
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.finddoc.R


object AppUtil{

    fun performHapticFeedback(view: View, context: Context) {
        view.isHapticFeedbackEnabled = true
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
    }

    fun pushInAnimation(view: View, context: Context) {
        val pushInAnimation = AnimationUtils.loadAnimation(context, R.anim.scale)
        view.startAnimation(pushInAnimation)
        performHapticFeedback(view, context)
    }

    fun changeStatusBarColor(activity: Activity, iconsForLightBar: Boolean, color: Int) {
        val window = activity.window
        val decor = window?.decorView
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (iconsForLightBar) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                decor?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else
                decor?.systemUiVisibility = 0
        } else
            decor?.systemUiVisibility = 0


        window?.statusBarColor = ContextCompat.getColor(
            activity,
            color
        )
    }

    fun dp2px(dp: Float): Int {
        return (Resources.getSystem().displayMetrics.density * dp).toInt()
    }

    fun px2dp(px: Float): Float {
        return (px - 0.5f) / (Resources.getSystem().displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun getDeviceHeightWidth(activity: Activity): Array<Float>{
        val display: Display = activity.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density: Float = activity.resources.displayMetrics.density
        val dpHeight = outMetrics.heightPixels / density
        val dpWidth = outMetrics.widthPixels / density
        return arrayOf(dpHeight,dpWidth)
    }

    fun startFragment(
        fragment: Fragment,
        supportFragmentManager: FragmentManager,
        containerViewId: Int,
        slide: Boolean,
        replace: Boolean,
        addToBackStack: String?
    ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            if (slide) R.anim.push_up_in else R.anim.enter_from_right,
            if (slide) R.anim.push_up_out else R.anim.exit_to_left
        )

        if (replace)
            fragmentTransaction.replace(
                containerViewId,
                fragment
            )
        else
            fragmentTransaction.add(
                containerViewId,
                fragment
            )

        fragmentTransaction.addToBackStack(addToBackStack)

        fragmentTransaction.commit()
    }
}