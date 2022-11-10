package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.CartItem

class BudgetListActivity : AppCompatActivity() {

    lateinit var cartAdapter: SelectedItemsAdapter
    lateinit var cartItems:ArrayList<CartItem>
    lateinit var cart_recylerview: RecyclerView
    lateinit var checkout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_list)

        val actionbar = supportActionBar
        actionbar!!.title = "Cart"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        toggleVisibility()

        cartItems = ArrayList()
        checkout=findViewById(R.id.check_out)


        cart_recylerview = findViewById(R.id.budget_recylerview)
        var database = BudgetDatabase.getInstance(application)
        val layoutManager = LinearLayoutManager(this,)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cart_recylerview.layoutManager = layoutManager

        database.budgetDao().getAllCartItem().observe(this, Observer {
        cartItems.addAll(it)
            cartAdapter = SelectedItemsAdapter(cartItems, this)
            cart_recylerview.adapter = cartAdapter
            cartAdapter.notifyDataSetChanged()
        }
        )


            checkout.setOnClickListener {
                    intent = Intent(this, CheckOutActivity::class.java)
                    startActivity(intent)
            }
    }
    fun toggleVisibility() {
        var db = BudgetDatabase.getInstance(application)
        db.budgetDao().getAllCartItem().observe(this, Observer {
            if (it.size >= 1) {
                checkout.visibility = View.VISIBLE
            } else {
                checkout.visibility = View.GONE
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}