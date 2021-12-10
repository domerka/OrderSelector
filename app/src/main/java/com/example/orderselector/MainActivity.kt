package com.example.orderselector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var orderPlayersButton : Button
    lateinit var selectFirstPlayerButton : Button
    lateinit var flipCoinButton : Button
    lateinit var rollDiceButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        orderPlayersButton = findViewById(R.id.order_players_button)
        selectFirstPlayerButton = findViewById(R.id.select_first_player_button)
        flipCoinButton = findViewById(R.id.flip_coin_button)
        rollDiceButton = findViewById(R.id.roll_dice_button)
        orderPlayersButton.setOnClickListener {
            openActivityOrderPlayers()
        }
        selectFirstPlayerButton.setOnClickListener {
            openActivitySelectFirstPlayer()
        }
        flipCoinButton.setOnClickListener{
            openActivityFlipCoin()
        }
        rollDiceButton.setOnClickListener {
            openActivityRollDice()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.select_first){
            val intent = Intent(this,SelectFirst::class.java)
            this.startActivity(intent)
            return true
        }

        if(item.itemId == R.id.order_many){
            val intent = Intent(this,Order::class.java)
            this.startActivity(intent)
            return true
        }

        if(item.itemId == R.id.flip_coin){
            val intent = Intent(this,FlipCoin::class.java)
            this.startActivity(intent)
            return true
        }

        if(item.itemId == R.id.roll_dice){
            val intent = Intent(this,RollDice::class.java)
            this.startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun openActivityOrderPlayers(){
        val intent = Intent(this,Order::class.java)
        this.startActivity(intent)
    }

    private fun openActivitySelectFirstPlayer(){
        val intent = Intent(this,SelectFirst::class.java)
        this.startActivity(intent)
    }

    private fun openActivityFlipCoin(){
        val intent = Intent(this,FlipCoin::class.java)
        this.startActivity(intent)
    }

    private fun openActivityRollDice(){
        val intent = Intent(this,RollDice::class.java)
        this.startActivity(intent)
    }
}