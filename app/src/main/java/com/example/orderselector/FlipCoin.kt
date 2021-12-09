package com.example.orderselector

import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast


class FlipCoin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_coin)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onCoinTap()
    }

    private fun onCoinTap(){
        val ivCoin = findViewById<ImageView>(R.id.iv_coin)
        ivCoin.setOnClickListener{
            val randomNumber = (1..2).random()

            if(randomNumber == 1){
                flipTheCoin(R.drawable.ic_heads, "Heads")
            }else{
                flipTheCoin(R.drawable.ic_tails,"Tails")
            }
        }
    }
    private fun flipTheCoin(imageId:Int , coinSide: String){
        val ivCoin = findViewById<ImageView>(R.id.iv_coin)
        ivCoin.animate().apply {
            duration = 1000
            rotationYBy(1800F)
            ivCoin.isClickable = false

        }.withEndAction{
            ivCoin.setImageResource(imageId)
            Toast.makeText(this,coinSide,Toast.LENGTH_SHORT).show()
            ivCoin.isClickable = true
        }.start()
    }
}