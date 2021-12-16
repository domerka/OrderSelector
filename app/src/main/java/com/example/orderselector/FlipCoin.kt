package com.example.orderselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.LinearLayout





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
                flipTheCoin(R.drawable.ic_coin_heads, getString(R.string.flip_coin_heads_message))
            }else{
                flipTheCoin(R.drawable.ic_coin_icon,getString(R.string.flip_coin_tails_message))
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