package com.example.budget


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.CartItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    lateinit var details: TextView
    lateinit var price2: TextView
    lateinit var image2: ImageView
    lateinit var button_add: Button
    lateinit var check_cart:Button

    lateinit var cartItems : ArrayList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionbar = supportActionBar
        actionbar!!.title = "Details"
        actionbar.setDisplayHomeAsUpEnabled(true)



         cartItems = ArrayList()
        check_cart=findViewById(R.id.check_cart)
        check_cart.setOnClickListener{
            intent = Intent(this,BudgetListActivity::class.java)
            startActivity(intent)
        }



        val itemId = intent.extras!!.get("modelled_item")

        var database = BudgetDatabase.getInstance(application)

        database.budgetDao().getBudgetItem(id = itemId as Int).observe(this, Observer {

            details = findViewById(R.id.item_details)
            details.text = it.productName

            price2 = findViewById(R.id.price)
            price2.text = it.productPrice.toString()

            val baseUrl = "http://192.168.0.127/ishop/media/"
            val pImage = baseUrl + it.productImage

            image2 = findViewById(R.id.image2)

            Picasso.get().load(pImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit().centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(image2)

        })

        button_add = findViewById(R.id.btn_add)
        button_add.setOnClickListener {
            database.budgetDao().getBudgetItem(id = itemId as Int).observe(this, Observer {

                var cartModel = CartItem(it.productID,it.productImage,it.productName,it.productPrice)
                database.budgetDao().insert(cartModel)
               println("cart modal item: ${cartModel.itemName}")
            })

            Toast.makeText(this, "Product successfully added to cart",Toast.LENGTH_SHORT).show()
        }

        database.budgetDao().getAllCartItem().observe(this, Observer {
            println("number of items in cart = ${it.size}")
        })
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
        val id: Int = item.getItemId()

        if (id == R.id.cart) {

            intent = Intent(applicationContext, BudgetListActivity::class.java)
            startActivity(intent)
        } else if (id == android.R.id.home)  {
            onBackPressed()
        }
            else super.onOptionsItemSelected(item)
        return true
    }
}