package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox

class CheckOutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_women)

        val actionbar = supportActionBar
        actionbar!!.title = "CheckOut"
        actionbar.setDisplayHomeAsUpEnabled(true)


        fun onCheckboxClicked(view: View) {
            if (view is CheckBox) {
                val checked: Boolean = view.isChecked

                when (view.id) {
                    R.id.deliver -> {
                        if (checked) {
                            // Show delivery amount
                        } else {
                            // Pickup Station
                        }
                    }
                    R.id.pickup_station -> {
                        if (checked) {
                            // Select pickup station
                        } else {
                            // Deliver
                        }
                    }
                    // TODO: Select A delivery method
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}