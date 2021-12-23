package com.example.orderselector

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class RollDice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roll_dice)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onDiceTap()
    }

    private fun onDiceTap(){
        val ivDice = findViewById<ImageView>(R.id.iv_dice)
        ivDice.setOnClickListener{

            when((1..6).random()){
                1 -> {
                    rollTheDice(R.drawable.ic_diceone)
                }
                2 -> {
                    rollTheDice(R.drawable.ic_dicetwo)
                }
                3 -> {
                    rollTheDice(R.drawable.ic_dicethree)
                }
                4 -> {
                    rollTheDice(R.drawable.ic_dicefour)
                }
                5 -> {
                    rollTheDice(R.drawable.ic_dicefive)
                }
                6 -> {
                    rollTheDice(R.drawable.ic_dicesix)
                }
            }
        }
    }



    private fun rollTheDice(imageId: Int){
        val diceList = listOf(R.drawable.ic_diceone,R.drawable.ic_dicetwo,R.drawable.ic_dicethree,R.drawable.ic_dicefour,R.drawable.ic_dicefive,R.drawable.ic_dicesix)
        val anim : AnimationSet = AnimationUtils.loadAnimation(this,R.anim.rotate) as AnimationSet
        val ivDice = findViewById<ImageView>(R.id.iv_dice)

        (anim.animations).forEach{
            it.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(animation: Animation?) {
                    ivDice.isClickable = false
                }

                override fun onAnimationEnd(animation: Animation?) {
                    ivDice.isClickable = true
                    ivDice.setImageResource(imageId)
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    ivDice.setImageResource(diceList[(0..5).random()])
                }

            })
        }
        ivDice.startAnimation(anim)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.infos,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.show_info){
            val settingsDialog = Dialog(this)
            settingsDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)

            settingsDialog.setContentView( layoutInflater.inflate(R.layout.popupimage_layout,null))

            settingsDialog.findViewById<TextView>(R.id.popup_text).text = getString(R.string.roll_dice_info)

            settingsDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }
}