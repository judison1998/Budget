package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        val actionbar = supportActionBar
        actionbar!!.title = "Help"
        actionbar.setDisplayHomeAsUpEnabled(true)



    }
    override fun onBackPressed() {
        val intent = Intent(this,ListActivity::class.java)
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}