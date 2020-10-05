package com.example.finddoc.views

import android.content.Context
import android.util.AttributeSet
import android.view.HapticFeedbackConstants
import com.example.finddoc.R
import com.example.finddoc.utils.AppUtil


class CustomButton : androidx.appcompat.widget.AppCompatButton {
    constructor(context: Context) : super(context) {
        isHapticFeedbackEnabled = true
        performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyFont(context, attrs)
    }

    private fun applyFont(context: Context, attrs: AttributeSet) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.CustomTextStyle)

        // Read the title and set it if any
        val attributeName = attribute.getString(R.styleable.CustomTextStyle_customTextStyle)
        if (attributeName != null) {
            // We have a attribute value and set it to proper value as you want
            setTypeface(attributeName)
        } else {
            setDefaultTypeface()
        }

        val textAllCaps = attrs.getAttributeValue("android", "textAllCaps")

        if(textAllCaps==null){
            setDefaultCapsFalse()
        }
        contentDescription = text

        isHapticFeedbackEnabled = true
        performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
        attribute.recycle()
    }


   /* override fun onTouchEvent(event: MotionEvent?): Boolean {
        val eventAction = event!!.getAction()

        // put your code in here to handle the event
        when (eventAction) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> {
                performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
            }
            MotionEvent.ACTION_MOVE -> {
            }
        }

        // tell the View to redraw the Canvas
        invalidate()

        // tell the View that we handled the event
        return true
    }*/

    override fun setOnClickListener(l: OnClickListener?) {
        AppUtil.pushInAnimation(this,context)
        super.setOnClickListener(l)
    }

    fun setTypeface(string: String) {
        val boldTypeface = FontCache.getOTF(context, "TTCommonBold")
        val mediumTypeface = FontCache.getOTF(context, "TTCommonRegular")

        if (string == "bold") {
            super.setTypeface(boldTypeface)
        } else if (string == "medium") {
            super.setTypeface(mediumTypeface)
        }
    }

    fun setDefaultTypeface() {
        val normalTypeface = FontCache.getOTF(context, "TTCommonRegular")
        super.setTypeface(normalTypeface)
    }

    fun setDefaultCapsFalse(){
        super.setAllCaps(false)
    }

}