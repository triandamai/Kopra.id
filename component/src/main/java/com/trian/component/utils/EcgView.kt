package com.trian.component.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class ECGView internal constructor(context: Context?) : View(context) {
    var original = ArrayList<Pt>() // original data
    var points = ArrayList<Pt>() // points to be drawn
    var radius = 10f // radius of circle
    var FACTOR = 3.0f // factor to divide radius, smaller FACTOR has smooth path drawing
    var STEP = 2 // step to decrease argb, larger STEP will let the path glow longer
    var ALPHA = 150 // minimum alpha value of path
    var WHITE_DEC = 20 // decrement of WHITE
    var ALPHA_DEC = 5 // decrement of ALPHA
    var GREEN_DEC = 2 // decrement of GREEN
    var counter = 0 // counter
    var argb // current argb
            : ARGB? = null
    var paint: Paint = Paint() // paint
    var path = arrayOf<Pt>( // path to draw
        Pt(100f, 300f),
        Pt(150f, 300f),
        Pt(200f, 100f),
        Pt(250f, 500f),
        Pt(300f, 300f),
        Pt(400f, 300f),
        Pt(450f, 100f),
        Pt(500f, 500f),
        Pt(550f, 300f),
        Pt(650f, 300f)
    )

    // ARGB data holder class
    inner class ARGB(// alpha
        var a: Int, // red
        var r: Int, // green
        var g: Int, // blue
        var b: Int
    ) {
        init {
            b = b
        }
    }

    // Point data holder class
    // We need float data for the point, otherwise, int data would create gap in the graph.
    inner class Pt(x: Float, y: Float) {
        var x: Float
        var y: Float

        init {
            this.x = x
            this.y = y
        }
    }

    // generate data that will be drew on canvas
    fun generateData() {
        for (i in 1 until path.size) {
            val start = path[i - 1] // start point
            val end = path[i] // end point
            val distanceX = end.x - start.x // x distance between start and end
            val distanceY = end.y - start.y // y distance between start and end
            var step: Int // step required from start to end
            var incrementX: Float // step increment of x
            var incrementY: Float // step increment of y
            val inc = radius / FACTOR // minimum increment of each step

            // decide step by larger distance
            step = if (Math.abs(distanceX) > Math.abs(distanceY)) {
                Math.abs((distanceX / inc).toInt())
            } else {
                Math.abs((distanceY / inc).toInt())
            }
            incrementX = distanceX / step
            incrementY = distanceY / step
            var positionX = start.x
            var positionY = start.y
            var l = 0
            while (l < step) {
                original.add(Pt(positionX, positionY))
                positionX += incrementX
                positionY += incrementY
                l++
            }
        }
    }// adjust value of the green part// adjust alpha of the green part// adjust value of the white part// Adjust argb whenever counter reach the STEP

    // control color and brightness value.
    val rGB: ARGB?
        get() {
            // Adjust argb whenever counter reach the STEP
            if (++counter % STEP == 0) {
                if (argb!!.r > 0) {
                    // adjust value of the white part
                    argb!!.r -= WHITE_DEC
                    argb!!.b -= WHITE_DEC
                    if (argb!!.r < 0) {
                        argb!!.r = 0
                        argb!!.b = 0
                    }
                } else if (argb!!.a > ALPHA) {
                    // adjust alpha of the green part
                    argb!!.a -= ALPHA_DEC
                } else if (argb!!.g > 0) {
                    // adjust value of the green part
                    argb!!.g -= GREEN_DEC
                    if (argb!!.g < 0) argb!!.g = 0
                }
            }
            return argb
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //Debug.startMethodTracing("calc");
        canvas.drawColor(Color.BLACK)

        // head part of the line must be brightest.
        argb = ARGB(255, 255, 255, 255)
        // sync the points list before get the size.
        var size = 0
        synchronized(points) { size = points.size }
        // draw points in array.
        for (i in --size downTo 0) {
            val pt = points[i]
            // draw the point only when the color is not black yet.
            val check = rGB
            if (check!!.g == 0) break
            drawCircle(canvas, pt.x, pt.y, check)
        }
        //Debug.stopMethodTracing();
    }

    // draw circle by given argb value.
    fun drawCircle(canvas: Canvas, x0: Float, y0: Float, rgb: ARGB?) {
        paint.setARGB(rgb!!.a, rgb.r, rgb.g, rgb.b)
        canvas.drawCircle(x0, y0, radius, paint)
    }

    // thread shifts data in the point list.
    internal inner class myThread : Thread() {
        var i = 0
        override fun run() {
            try {
                while (true) {
                    // when the point list is full, remove the first element.
                    if (i >= original.size) {
                        synchronized(points) { points.removeAt(0) }
                    }
                    // Add new element from original list to tail of point list.
                    synchronized(points) { points.add(original[i++ % original.size]) }
                    // sleep a little time and update.
                    sleep(10)
                    postInvalidate()
                }
            } catch (e: InterruptedException) {
            }
        }
    }

    init {
        // Generate original data points
        generateData()
        // Start a thread to shift data in the list
        val thread: myThread = myThread()
        thread.start()
    }
}