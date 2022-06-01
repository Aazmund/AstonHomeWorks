package com.example.homework3

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.icu.util.Calendar
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.annotation.RequiresApi
import kotlin.math.min

class ClockView(context: Context?) : View(context) {

    fun newColor(color1: Int, color2: Int, color3: Int){
        hourColor = color1
        minuteColor = color2
        secondColor = color3
    }

    fun newSize(size1: Float, size2: Float, size3: Float){
        hourSize = size1
        minuteSize = size2
        secondSize = size3
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        val calendar: Calendar = Calendar.getInstance()
        val second = calendar.get(Calendar.SECOND)
        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR)

        val paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 20F
        paint.style = Paint.Style.STROKE


        val xScale:Float = (width / 2).toFloat()
        val yScale:Float = (height / 1.75).toFloat()
        val radius:Float = min(xScale / 1.5, yScale / 1.5).toFloat()

        canvas.drawCircle(xScale, yScale, radius, paint)

        for (i in 0..12){
            canvas.save()
            canvas.rotate(30F * i, xScale, yScale)
            canvas.drawLine(xScale, (yScale + 40 - radius), xScale, (yScale - radius), paint)
            canvas.restore()
        }

        canvas.save()
        paint.color = hourColor
        paint.strokeWidth = hourSize
        canvas.rotate(30 * hour.toFloat() + minute.toFloat() * 0.5F, xScale, yScale)
        canvas.drawLine(xScale, yScale + 60, xScale, (yScale - radius * 0.5).toFloat(), paint)
        canvas.restore()

        canvas.save()
        canvas.rotate(6 * minute.toFloat(), xScale, yScale)
        paint.color = minuteColor
        paint.strokeWidth = minuteSize
        canvas.drawLine(xScale, yScale + 90, xScale, yScale - radius + 70, paint)
        canvas.restore()

        canvas.save()
        canvas.rotate(6 * second.toFloat() + second.toFloat() * 0.1F, xScale, yScale)
        paint.color = secondColor
        paint.strokeWidth = secondSize
        canvas.drawLine(xScale, yScale + 100, xScale, yScale - radius + 45, paint)
        canvas.restore()

        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                invalidate()
            }, 1000)
        }

    }

    private companion object {
        private var hourColor = Color.BLUE
        private var minuteColor = Color.GREEN
        private var secondColor = Color.RED

        private var hourSize: Float = 20F
        private var minuteSize: Float = 10F
        private var secondSize: Float = 5F
    }

}