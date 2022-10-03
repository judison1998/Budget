package com.example.budget

//import android.R
import android.graphics.BitmapFactory
import android.media.Image
import android.os.Bundle
import android.util.Log.i
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    lateinit var details: TextView
    lateinit var price2: TextView
    lateinit var image2: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemModal = intent.extras!!.get("modelled_item") as ItemModal

        details = findViewById(R.id.item_details)
        details.text = itemModal.itemName
        price2 = findViewById(R.id.price)
        price2.text = itemModal.price.toString()
        image2 = findViewById(R.id.image2)
        image2.setImageResource(itemModal.image)
    }
}