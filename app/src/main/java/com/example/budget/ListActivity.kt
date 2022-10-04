package com.example.budget

//import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import java.io.ByteArrayOutputStream
import java.io.Serializable


class ListActivity : AppCompatActivity(), BudgetAdapter.ClickInterface {

    lateinit var listRV: RecyclerView
    lateinit var budgetAdapter: BudgetAdapter
    lateinit var listItem: ArrayList<ItemModal>

    lateinit var itemList:ArrayList<BudgetItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        listRV = findViewById(R.id.list_recyclerview)

        listItem = ArrayList()

         var database = BudgetDatabase.getInstance(this)
        val layoutManager = GridLayoutManager(this, 2)
        listRV.layoutManager = layoutManager
        budgetAdapter = BudgetAdapter(itemList, this)
        listRV.adapter = budgetAdapter

        listItem.add(ItemModal("Chairs", 200000, R.drawable.chairs))
        listItem.add(ItemModal("Cups", 10000, R.drawable.cups))
        listItem.add(ItemModal("Kettle", 75000, R.drawable.packos))
        listItem.add(ItemModal("Plates", 5000, R.drawable.plates))
        listItem.add(ItemModal("Spoons", 1000, R.drawable.spoons))
        listItem.add(ItemModal("bag", 50000, R.drawable.bag))
        listItem.add(ItemModal("bag1", 20000, R.drawable.bag2))

        budgetAdapter.notifyDataSetChanged()

        itemList = ArrayList()
        itemList.add(BudgetItem(1,R.drawable.chairs,"chair",50000))
        itemList.add(BudgetItem(2,R.drawable.cups,"cup",60000))
        itemList.add(BudgetItem(3,R.drawable.packos,"kettle",20000))
        itemList.add(BudgetItem(4,R.drawable.plates,"plate",4000))
        itemList.add(BudgetItem(5,R.drawable.spoons,"spoon",90000))
        itemList.add(BudgetItem(6,R.drawable.bag,"bag",50400))
        itemList.add(BudgetItem(7,R.drawable.bag2,"other bag",3000))
        database.budgetDao().insert(itemList)
        println("items added to db ${itemList.size}")
    }
    override fun onItemClick(budgetItem: BudgetItem) {
        val intent=Intent(this,DetailsActivity::class.java)
        intent.putExtra("modelled_item",budgetItem as Serializable)
        startActivity(intent)

    }
}