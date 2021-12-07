package com.example.orderselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SelectFirst : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_first)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}