package com.example.orderselector

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.Window
import android.widget.TextView

class SelectFirst : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_first)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

            settingsDialog.findViewById<TextView>(R.id.popup_text).text = getString(R.string.first_player_info)

            settingsDialog.show()
        }
        return super.onOptionsItemSelected(item)
    }
}