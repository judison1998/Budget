package com.example.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

     lateinit var details : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val bundle : Bundle? = intent.extras
        val image = bundle?.get("image")
        val itemName = bundle?.get("ItemName")
        val price = bundle?.get("Price")

        details = findViewById(R.id.item_details)
        details.text = itemName.toString()

        startActivity(intent)
    }
}