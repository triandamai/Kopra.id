package com.trian.component.utils

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class EcgGraphView : View {

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(set: AttributeSet?) {

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }
}