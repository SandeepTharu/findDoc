package com.example.finddoc.views

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.finddoc.R

class DynamicCurve : ConstraintLayout {
    private var mContext: Context? = null
    var paint: Paint? = null
    var paint2: Paint? = null
    var path: Path? = null
    internal var cx1 = 0f
    internal var cx2 = 0f

    private var reverse: Boolean = false
    private var mirror: Boolean = false
    private var upsideDown: Boolean = false
    private var halfWidth: Boolean = false
    private var decreaseHeightBy: Float = 0.0f
    private var isInPx: Boolean = false
    private var deltaDivisble: Float = 10f

    private var x0: Float? = 0.0f
    private var x1: Float? = 0.0f
    private var x2: Float? = 0.0f
    private var x3: Float? = 0.0f
    private var y0: Float? = 0.0f
    private var y1: Float? = 0.0f
    private var y2: Float? = 0.0f
    private var y3: Float? = 0.0f

    private var x1a: Float? = 0.0f
    private var x2a: Float? = 0.0f
    private var x3a: Float? = 0.0f
    private var y1a: Float? = 0.0f
    private var y2a: Float? = 0.0f
    private var y3a: Float? = 0.0f

    private var upsideDownCalculated: Boolean = false

    var paintColor: Int = resources.getColor(R.color.LightGoldenrodYellow)

    constructor(context: Context) : super(context) {
        this.mContext = context
        init()

    }


    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        this.mContext = context
        val attribute = context.obtainStyledAttributes(attributeSet, R.styleable.Dynamic)
        val curveColor = attribute.getColor(R.styleable.Dynamic_curveColor, 0)
        mirror = attribute.getBoolean(R.styleable.Dynamic_mirror, false)
        reverse = attribute.getBoolean(R.styleable.Dynamic_reverse, false)
        upsideDown = attribute.getBoolean(R.styleable.Dynamic_upsideDown, false)
        decreaseHeightBy = attribute.getFloat(R.styleable.Dynamic_decreaseHeightBy, 0f)
        isInPx = attribute.getBoolean(R.styleable.Dynamic_isInPx, false)
        paintColor = curveColor
        deltaDivisble = attribute.getFloat(R.styleable.Dynamic_deltaDivisible, 10f)

        initializeXY(attribute)

        if (mirror) ifMirrored() //For mirrored
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.mContext = context
        init()
    }

    private fun init() {
        setWillNotDraw(false)

        /*********************From init()*******************/
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint!!.style = Paint.Style.FILL
        paint!!.color = paintColor
        //        paint.setStrokeWidth(4);

        //For Shadows
        /*paint!!.setShadowLayer(8f, 1f, 1f, Color.GRAY);
        setLayerType(LAYER_TYPE_SOFTWARE, paint);*/


        path = Path()

        paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
        paint2!!.style = Paint.Style.STROKE
        paint2!!.strokeWidth = 3f
        paint2!!.color = ContextCompat.getColor(context,R.color.LightGoldenrodYellow)
        /****************************************************/

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val height = height
        val width = width
        val delta = width / deltaDivisble
        val deltaHeight = height / deltaDivisble

        if (upsideDown) ifUpsideDown(height.toFloat(), delta) //For upSideDown

        path?.moveTo(
            if (isInPx) dp2px(x0!!) else x0!! * delta,
            when {
                y0 == null -> height.toFloat() - (decreaseHeightBy * deltaHeight)
                isInPx -> dp2px(
                    y0!! - decreaseHeightBy
                )
                else -> (y0!! - decreaseHeightBy) * delta
            }
        )
        path?.cubicTo(
            if (isInPx) dp2px(x1!!) else x1!! * delta,
            when {
                y1 == null -> height.toFloat() - (decreaseHeightBy * deltaHeight)
                isInPx -> dp2px(y1!! - decreaseHeightBy)
                else -> (y1!! - decreaseHeightBy
                        ) * delta
            },
            if (isInPx) dp2px(x2!!) else x2!! * delta,
            when {
                y2 == null -> height.toFloat() - (decreaseHeightBy * deltaHeight)
                isInPx -> dp2px(
                    y2!! - decreaseHeightBy
                )
                else -> (y2!! - decreaseHeightBy) * delta
            },
            if (x3 == null && !halfWidth) width.toFloat() else if (x3 == null && halfWidth) width.toFloat() / 2 else if (isInPx) dp2px(
                x3!!
            ) else x3!! * delta,
            when {
                y3 == null -> height.toFloat() - (decreaseHeightBy * deltaHeight)
                isInPx -> dp2px(
                    y3!! - decreaseHeightBy
                )
                else -> (y3!! - decreaseHeightBy) * delta
            }
        )

        if (x3 == null && halfWidth) {
            path?.cubicTo(
                if (isInPx) dp2px(x1a!!) else x1a!! * delta,
                if (isInPx) dp2px(y1a!! - decreaseHeightBy) else (y1a!! - decreaseHeightBy) * delta,
                if (isInPx) dp2px(x2a!!) else x2a!! * delta,
                if (isInPx) dp2px(y2a!! - decreaseHeightBy) else (y2a!! - decreaseHeightBy) * delta,
                width.toFloat(),
                if (isInPx) dp2px(y3a!! - decreaseHeightBy) else (y3a!! - decreaseHeightBy) * delta
            )
        }

        if (reverse) {
            path?.lineTo(width.toFloat(), 0f)
            path?.lineTo(0f, 0f)
        } else {
            path?.lineTo(width.toFloat(), height.toFloat())
            path?.lineTo(0f, height.toFloat())
        }

        path?.lineTo(0f, height.toFloat())
        path?.close()

        canvas?.drawPath(path!!, paint!!)
    }

    private fun dp2px(dp: Float): Float {
        return (mContext?.resources?.displayMetrics!!.density * dp + 0.5f)
    }

    private fun ifMirrored() {
        //NOTE: '*' means un-mirrored value
        val dummyX1 = x1
        val dummyX2 = x2
        val dummyY0 = y0
        val dummyY1 = y1
        val dummyY2 = y2
        val dummyY3 = y3
        x1 = if (x2 != null) deltaDivisble - dummyX2!! else x1 //If mirrored -> [width - x2*]
        x2 = if (x1 != null) deltaDivisble - dummyX1!! else x2 //If mirrored -> [width - x1*]
        y0 = if (y3 != null) dummyY3 else if(mirror) dummyY3 else y0 //If mirrored -> y3*
        y1 = if (y2 != null) dummyY2 else if(mirror) dummyY2 else y1 //If mirrored -> y2*
        y2 = if (y1 != null) dummyY1 else if(mirror) dummyY1 else y2 //If mirrored -> y1*
        y3 = if (y0 != null) dummyY0 else if(mirror) dummyY0 else y3//If mirrored -> y0*
    }

    private fun ifUpsideDown(height: Float, delta: Float){
        if(!upsideDownCalculated) {
            val dummyY0 = y0
            val dummyY1 = y1
            val dummyY2 = y2
            val dummyY3 = y3
            y0 = height / delta - (dummyY0 ?: 0f)
            y1 = height / delta - (dummyY1 ?: 0f)
            y2 = height / delta - (dummyY2 ?: 0f)
            y3 = height / delta - (dummyY3 ?: 0f)
            upsideDownCalculated = true
        }
    }

    private fun checkFullHeight(value: String?): Float? {
        return if (value.equals("height", ignoreCase = true))
            null else value?.toFloat()
    }

    private fun initializeXY(attribute: TypedArray){
        x0 = attribute.getString(R.styleable.Dynamic_x0)?.toFloat()
        x1 = attribute.getString(R.styleable.Dynamic_x1)?.toFloat()
        x2 = attribute.getString(R.styleable.Dynamic_x2)?.toFloat()
        x3 = when {
            attribute.getString(R.styleable.Dynamic_x3)
                .equals("width", ignoreCase = true) -> null
            attribute.getString(R.styleable.Dynamic_x3)
                .equals("half", ignoreCase = true) -> {
                halfWidth = true
                null
            }
            else -> attribute.getString(R.styleable.Dynamic_x3)?.toFloat()
        }
        y0 = checkFullHeight(attribute.getString(R.styleable.Dynamic_y0))
        y1 = checkFullHeight(attribute.getString(R.styleable.Dynamic_y1))
        y2 = checkFullHeight(attribute.getString(R.styleable.Dynamic_y2))
        y3 = checkFullHeight(attribute.getString(R.styleable.Dynamic_y3))

        if (x3 == null) {
            x1a = attribute.getString(R.styleable.Dynamic_x1a)?.toFloat()
            x2a = attribute.getString(R.styleable.Dynamic_x2a)?.toFloat()
            x3a = attribute.getString(R.styleable.Dynamic_x3a)?.toFloat()
            y1a = attribute.getString(R.styleable.Dynamic_y1a)?.toFloat()
            y2a = attribute.getString(R.styleable.Dynamic_y2a)?.toFloat()
            y3a = attribute.getString(R.styleable.Dynamic_y3a)?.toFloat()
        }
    }

    fun changeCurveColor(color: Int) {
        paintColor = color
        init()
        invalidate()
    }
}