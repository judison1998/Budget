package com.example.budget


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.CartItem

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
            details.text = it.itemName

            price2 = findViewById(R.id.price)
            price2.text = it.price.toString()

            image2 = findViewById(R.id.image2)
            image2.setImageResource(it.image)

        })

        button_add = findViewById(R.id.btn_add)
        button_add.setOnClickListener {
            database.budgetDao().getBudgetItem(id = itemId as Int).observe(this, Observer {

                var cartModel = CartItem(it.id,it.image,it.itemName,it.price)
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
}