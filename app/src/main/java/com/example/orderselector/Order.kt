package com.example.orderselector

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Order : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
