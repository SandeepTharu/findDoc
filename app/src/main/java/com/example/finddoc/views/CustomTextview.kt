package com.example.finddoc.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.example.finddoc.R


class CustomTextview : TextView {
    
    constructor(context: Context) : super(context) {
        setDefaultTypeface()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyFont(context, attrs)
    }

    private fun applyFont(context: Context, attrs: AttributeSet) {
        val attribute = context.obtainStyledAttributes(attrs, R.styleable.CustomTextStyle)
        val spacingAttribute = context.obtainStyledAttributes(
            attrs, R.styleable
                .CustomLetterSpacingStyle
        )

        // Read the title and set it if any
        val attributeName = attribute.getString(R.styleable.CustomTextStyle_customTextStyle)
        val attributeLetter = spacingAttribute.getString(
            R.styleable
                .CustomLetterSpacingStyle_customLetterSpacingStyle
        )
        if (attributeName != null) {
            // We have a attribute value and set it to proper value as you want
            setTypeface(attributeName)
        } else {
            setDefaultTypeface()
        }


//        setDefaultTypeface() //since there is only one font now, we will reuse the same


        if (attributeLetter != null) {
            setLetterSpacing(attributeLetter)
        } else {
            letterSpacing = 0.0f
        }

        contentDescription = text
        
        attribute.recycle()
    }

    fun setTypeface(typefaceName: String) {
        /*attribute name for textbold*/
        /*app:customTextStyle="bold""*/

        val boldTypeface = FontCache.getOTF(context, "TTCommonBold")
        val mediumTypeface = FontCache.getOTF(context, "TTCommonRegular")

        if (typefaceName.equals("bold")) {
            super.setTypeface(boldTypeface, Typeface.NORMAL)
        } else if (typefaceName.equals("medium")) {
            super.setTypeface(mediumTypeface)
        }
    }

    private fun setLetterSpacing(letterTypeface: String) {
        /*attribute name for letter spacing*/
        /*app:customLetterSpacingStyle="customLetterSpacing"*/
        if (letterTypeface.equals("customLetterSpacing")) {
            letterSpacing = 0.0f
        }
    }

    private fun setDefaultTypeface() {
        val normalTypeface = FontCache.getOTF(context, "TTCommonRegular")
        super.setTypeface(normalTypeface)
    }
}

