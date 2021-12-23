package com.example.orderselector
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class SelectFirstView (context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val colors = intArrayOf(
        Color.rgb(148,0,211),Color.rgb(75,0,130),
        Color.rgb(0,0,255),Color.rgb(0,255,0),
        Color.rgb(255,255,0),Color.rgb(255,127,0),
        Color.rgb(255,0,0),Color.rgb(51,255,255),
        Color.rgb(255,102,178),Color.rgb(51,0,0)
    )

    private var mActivePointers: Array<Boolean> = Array(10){false}
    private val paint : Paint = Paint()
    private val textPaint : Paint = Paint()
    private var isTimerDone : Boolean = false

    private var positions : Array<PointF> = Array(10){ PointF() }
    private val randoNumbers: MutableList<Int> = mutableListOf()

    private var numberOfPointers = 0

    private var progressWidth = 0.0f
    private var screenWidth = 0.0f
    private var firstCall = true

    private var timer : CountDownTimer = object: CountDownTimer(0,0){
        override fun onTick(millisUntilFinished: Long) {
        }
        override fun onFinish() {
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {

        val pointerIndex : Int = event.actionIndex
        val pointerId : Int = event.getPointerId(pointerIndex)

        when(event.actionMasked){
            MotionEvent.ACTION_DOWN -> {
                if(isTimerDone){
                    numberOfPointers = 0
                    positions = Array(10){ PointF() }
                    mActivePointers = Array(10){false}
                    isTimerDone = false
                }

                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                mActivePointers[pointerId] = true
                positions[pointerId] = f
                timer = object : CountDownTimer(2000, 40) {
                    override fun onTick(millisUntilFinished: Long) {
                        progressWidth -= screenWidth/50
                        invalidate()
                    }
                    override fun onFinish() {
                        progressWidth -= screenWidth/50
                        isTimerDone = true
                        invalidate()
                    }
                }.start()
                numberOfPointers++
            }
            MotionEvent.ACTION_POINTER_DOWN ->{
                if(numberOfPointers == 10){
                    Toast.makeText(context,"No more players possible!",Toast.LENGTH_SHORT).show()
                    return false
                }
                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                mActivePointers[pointerId] = true
                positions[pointerId] = f
                numberOfPointers++
            }
            MotionEvent.ACTION_MOVE -> {
                if(!isTimerDone){
                    val size = event.pointerCount
                    var i = 0
                    while (i < size) {
                        val point = positions[event.getPointerId(i)]
                        point.x = event.getX(i)
                        point.y = event.getY(i)
                        i++
                    }
                }
            }
            MotionEvent.ACTION_UP ->{
                progressWidth = screenWidth
                mActivePointers[pointerId] = false
                numberOfPointers--
            }
            MotionEvent.ACTION_POINTER_UP ->{
                mActivePointers[pointerId] = false
                numberOfPointers--
            }
            MotionEvent.ACTION_CANCEL -> {
                mActivePointers[pointerId] = false
                numberOfPointers--
            }
        }
        if(!isTimerDone) invalidate()
        if(numberOfPointers == 0) timer.cancel()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        textPaint.textSize = 100f
        textPaint.color = Color.WHITE
        canvas?.drawColor(Color.BLACK)

        if (canvas != null && firstCall) {
            progressWidth = canvas.width.toFloat()
            screenWidth = canvas.width.toFloat()
            firstCall = false
        }
        //Progress bar drawing
        paint.color = Color.WHITE
        canvas?.drawRect(0f, 0f, progressWidth, 15f, paint)

        var k = 0
        if (isTimerDone) {
            while (k < 10) {
                if (mActivePointers[k]) {
                    randoNumbers.add(k)
                }
                k++
            }
            if(randoNumbers.size > 1){
                randoNumbers.shuffle()
            }
        }
        var i = 0
        while (i < 10) {
            if (mActivePointers[i]) {
                val point: PointF = positions[i]
                if(isTimerDone){
                    if(i == randoNumbers[0]){
                        paint.color = colors[i]
                        canvas?.drawText("1", point.x + 110f, point.y, textPaint)
                    }else{
                        paint.color = Color.DKGRAY
                    }
                }else{
                    paint.color = colors[i]
                }
                canvas?.drawCircle(point.x, point.y, 120F, paint)
            }
            i++
        }
    }
}