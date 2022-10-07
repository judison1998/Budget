package com.example.budget


import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.database.CartItem

class DetailsActivity : AppCompatActivity() {
    lateinit var details: TextView
    lateinit var price2: TextView
    lateinit var image2: ImageView
    lateinit var button_add: Button

    lateinit var cartModal : CartItem




    lateinit var cartItem : ArrayList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         cartItem = ArrayList()

        val itemId = intent.extras!!.get("modelled_item")

        var database = BudgetDatabase.getInstance(this)

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

                cartModal.id = it.id
                cartModal.image = it.image
                cartModal.price = it.price
                cartModal.itemName = it.itemName

                database.budgetDao().insert(cartModal)

//                println("cart modal added ${}")







            })


            Toast.makeText(this, "Product successfully added to cart",Toast.LENGTH_SHORT).show()
        }



    }
}