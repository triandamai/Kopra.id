package com.trian.component.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.compose.ui.graphics.Color

class EcgGraphSmarthealth:View{

    private val MULTIPLE = 0.5f
    val state = 10
    var ctx:Context?=null
    private var isHome = false
     var mGridColor = 0
     var mGridWidth = 0f
     var mHeight = 0
     var mInitColor = 0
     var mLineColor = 0
     var mPaint: Paint? = null
     var mPath: Path? = null
     var mSGridColor = 0
     var mSGridWidth = 0f
    private var mSecLineColor = 0
     var mWidth = 0
    private var plist= mutableListOf<Int>()

    constructor(context: Context?) : super(context) {

    }

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        isHome = false
        mInitColor = android.graphics.Color.parseColor("#000000")
        mLineColor = android.graphics.Color.parseColor("#FB3159")
        mGridColor = android.graphics.Color.parseColor("#D9D9D9")
        mSGridColor = android.graphics.Color.parseColor("#F0F0F0")
        mSecLineColor = android.graphics.Color.parseColor("#8C8C8C")
        val baseCtx = (context as ContextWrapper).baseContext
        ctx= baseCtx
        val paint = Paint()
        mPaint = paint
        paint.isAntiAlias = true
        mPath = Path()
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        isHome = false
        mInitColor = android.graphics.Color.parseColor("#000000")
        mLineColor = android.graphics.Color.parseColor("#FB3159")
        mGridColor = android.graphics.Color.parseColor("#D9D9D9")
        mSGridColor = android.graphics.Color.parseColor("#F0F0F0")
        mSecLineColor = android.graphics.Color.parseColor("#8C8C8C")
        val baseCtx = (context as ContextWrapper).baseContext
        ctx= baseCtx
        val paint = Paint()
        mPaint = paint
        paint.isAntiAlias = true
        mPath = Path()
    }

    fun setData(list: List<Int>){
        this.plist.addAll(list)
    }

    fun make(i:Int){
        mWidth =i
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mWidth =w
        mHeight = h
        super.onSizeChanged(w, h, oldw, oldh)
    }

    fun getMultiple(context: Context): Float {
        return context.resources.displayMetrics.density*0.5f
    }

    fun initBackground(canvas: Canvas){

        plist.forEachIndexed{
            index: Int, i: Int ->
            if(index > 0){
                val pos = index -1
                val multiple = (pos *3)*getMultiple(ctx!!)
                val value =
                    (((mHeight /2)/mGridWidth) *mGridWidth)-(((plist.get(pos)*10)*mSGridWidth)/1000.0f)
                val multiple3 = ((pos *3)*getMultiple(ctx!!))
                val valueStop = (((mHeight/2)/mGridWidth)*mGridWidth)-(((plist.get(pos)*10))*mSGridWidth/1000.0f)
                canvas.drawLine(
                    multiple,
                    value,
                    multiple3,
                    valueStop,
                    mPaint!!
                )

            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
       initBackground(canvas = canvas!!)
    }
}