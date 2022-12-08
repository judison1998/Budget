package com.example.budget.detail


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.budget.BudgetListActivity
import com.example.budget.ListActivity
import com.example.budget.R
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.database.CartItem
import com.example.budget.databinding.ActivityDetailsBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.util.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var btnAdd: Button
    private lateinit var imgView: ImageView

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val actionbar = supportActionBar
        actionbar!!.title = "Product Details"
        actionbar.setDisplayHomeAsUpEnabled(true)
        val itemId = intent.extras!!.get("modelled_item").toString().toInt()
        Log.i("received", "$itemId")
        val database = BudgetDatabase.getInstance(this)
        database.budgetDao().getBudgetItem(id = itemId)
            .observe(this, androidx.lifecycle.Observer {
                name = findViewById(R.id.item_details)
                price = findViewById(R.id.price)
                name.text = it.productName
                price.text = it.productPrice
                imgView = findViewById(R.id.image2)
                val pImage = it.productImage
                Picasso.get().load(pImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .fit().centerCrop()
                    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                    .into(imgView)

                Log.i("pro", "${it.productImage}")

            })
        btnAdd = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            database.budgetDao().getBudgetItem(id = itemId)
                .observe(this, androidx.lifecycle.Observer {

                    var cartModel =
                        CartItem(it.productID, it.productImage, it.productName, it.productPrice)
                    database.budgetDao().insert(cartModel)
                    println("cart modal item: ${cartModel.itemName}")
                })

            Toast.makeText(this, "Product successfully added to cart", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId

        if (id == R.id.cart) {

            intent = Intent(applicationContext, BudgetListActivity::class.java)
            startActivity(intent)
        } else if (id == android.R.id.home) {
            onBackPressed()
        } else super.onOptionsItemSelected(item)
        return true
    }
}