package com.example.budget

//import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream
import java.io.Serializable


class ListActivity : AppCompatActivity(), BudgetAdapter.ClickInterface {

    lateinit var listRV: RecyclerView
    lateinit var budgetAdapter: BudgetAdapter
    lateinit var listItem: ArrayList<ItemModal>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        listRV = findViewById(R.id.list_recyclerview)

        listItem = ArrayList()
        val layoutManager = GridLayoutManager(this, 2)
        listRV.layoutManager = layoutManager
        budgetAdapter = BudgetAdapter(listItem, this)
        listRV.adapter = budgetAdapter

        listItem.add(ItemModal("Chairs", 200000, R.drawable.chairs))
        listItem.add(ItemModal("Cups", 10000, R.drawable.cups))
        listItem.add(ItemModal("Kettle", 75000, R.drawable.packos))
        listItem.add(ItemModal("Plates", 5000, R.drawable.plates))
        listItem.add(ItemModal("Spoons", 1000, R.drawable.spoons))
        listItem.add(ItemModal("bag", 50000, R.drawable.bag))
        listItem.add(ItemModal("bag1", 20000, R.drawable.bag2))

        budgetAdapter.notifyDataSetChanged()

    }
    override fun onItemClick(itemModal: ItemModal) {
        val intent=Intent(this,DetailsActivity::class.java)
        intent.putExtra("modelled_item",itemModal as Serializable)
        startActivity(intent)

    }
}