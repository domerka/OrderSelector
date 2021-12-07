package com.example.orderselector

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class Order : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

class DrawCircleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
                    ) : View(context, attrs, defStyleAttr) {
    private val colors = intArrayOf(
        Color.BLUE, Color.GREEN, Color.MAGENTA,
        Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
        Color.LTGRAY, Color.YELLOW
    )
    private val pointers: MutableList<PointF> = mutableListOf()

    override fun onTouchEvent(event: MotionEvent): Boolean {

        val pointerIndex : Int = event.actionIndex
        val pointerId : Int = event.getPointerId(pointerIndex)
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP ->{
            }
            MotionEvent.ACTION_POINTER_DOWN ->{
                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                pointers.add(pointerId, f)
            }
            MotionEvent.ACTION_MOVE -> {
                val size = event.pointerCount
                var i = 0
                while (i < size) {
                    val point: PointF = pointers[event.getPointerId(i)]
                    point.x = event.getX(i)
                    point.y = event.getY(i)
                    i++
                }
            }
            MotionEvent.ACTION_POINTER_UP ->{
                pointers.removeAt(pointerId)
            }/*
            MotionEvent.ACTION_CANCEL -> {
                pointers.removeAt(pointerId);
            }*/
        }
        return true
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val size: Int = pointers.size
        var i = 0
        while (i < size) {
            val point: PointF = pointers[i]
            if (canvas != null) {
                canvas.drawCircle(point.x, point.y, 10F, Paint())
            }
            i++
        }
    }

}