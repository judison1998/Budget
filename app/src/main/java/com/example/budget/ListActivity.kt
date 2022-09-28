package com.example.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    lateinit var listRV : RecyclerView
    lateinit var budgetAdapter: BudgetAdapter
    lateinit var listItem : ArrayList<ItemModal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listRV = findViewById(R.id.list_recyclerview)

        listItem = ArrayList()
        val layoutManager = GridLayoutManager(this, 2)
        listRV.layoutManager = layoutManager
        budgetAdapter = BudgetAdapter(listItem,this)
        listRV.adapter = budgetAdapter

        listItem.add(ItemModal("Chairs",200000,R.drawable.chairs))
        listItem.add(ItemModal("Cups",10000,R.drawable.cups))
        listItem.add(ItemModal("Kettle",75000,R.drawable.packos))
        listItem.add(ItemModal("Plates",5000,R.drawable.plates))
        listItem.add(ItemModal("Spoons",1000,R.drawable.spoons))

        budgetAdapter.notifyDataSetChanged()

    }
}