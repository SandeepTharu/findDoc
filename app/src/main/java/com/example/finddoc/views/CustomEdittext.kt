package com.example.finddoc.views

import android.content.Context
import android.util.AttributeSet
import com.example.finddoc.R

class CustomEdittext : androidx.appcompat.widget.AppCompatEditText {
    constructor(context: Context) : super(context)

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
        contentDescription = text

        attribute.recycle()
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
}