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
        Color.BLUE, Color.GREEN, Color.MAGENTA,
        Color.WHITE, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,
        Color.LTGRAY, Color.YELLOW
    )

    private var mActivePointers: Array<Boolean> = Array(10){false}
    private val paint : Paint = Paint()
    private val textPaint : Paint = Paint()
    private var isTimerDone : Boolean = false

    private var positions : Array<PointF> = Array(10){ PointF() }
    private val randoNumbers: MutableList<Int> = mutableListOf()

    private var numberOfPointers = 0

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
                timer = object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                    }
                    override fun onFinish() {
                        isTimerDone = true
                        invalidate()
                        Toast.makeText(context,
                           context.getString(R.string.finish_toast_message),Toast.LENGTH_SHORT).show()
                    }
                }.start()
                numberOfPointers++
            }
            MotionEvent.ACTION_POINTER_DOWN ->{
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
                mActivePointers[pointerId] = false
                numberOfPointers--
            }
            MotionEvent.ACTION_POINTER_UP ->{
                mActivePointers[pointerId] = false
                numberOfPointers--
                //Toast.makeText(context,test.size.toString(),Toast.LENGTH_SHORT).show()
            }
            MotionEvent.ACTION_CANCEL -> {
                mActivePointers[pointerId] = false
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
        var i = 0
        if(isTimerDone){
            while (i < 10) {
                if(mActivePointers[i]){
                    randoNumbers.add(i)
                }
                i++
            }
            randoNumbers.shuffle()
            val id = randoNumbers[0]
            paint.color = colors[id]
            val point: PointF = positions[id]
            canvas?.drawCircle(point.x, point.y, 120F, paint)
            canvas?.drawText("1",point.x+110f,point.y,textPaint)
        }
        else
        {
            while (i < 10) {
                if(mActivePointers[i]){
                    paint.color = colors[i]
                    val point: PointF = positions[i]
                    canvas?.drawCircle(point.x, point.y, 120F, paint)
                }
                i++
            }
        }

    }
}