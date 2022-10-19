package com.example.budget

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.account.LoginActivity
import com.example.budget.account.ProfileActivity
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.google.android.material.navigation.NavigationView


class ListActivity : AppCompatActivity(), BudgetAdapter.ClickInterface {

    lateinit var drawerLayout: DrawerLayout


    lateinit var actionBarToggle: ActionBarDrawerToggle

    lateinit var navView: NavigationView

    lateinit var listRV: RecyclerView

    lateinit var budgetAdapter: BudgetAdapter

    lateinit var itemList: ArrayList<BudgetItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        drawerLayout = findViewById(R.id.drawerLayout)

        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()
        navView = findViewById(R.id.navView)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bags -> {
                    Toast.makeText(this, "Bags", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, DetailsActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.causalShoes -> {
                    Toast.makeText(this, "Causal Shoes", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.homeAlliance -> {
                    Toast.makeText(this, "Home Alliance", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.menWear -> {
                    Toast.makeText(this, "Men's Wear", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.sandles -> {
                    Toast.makeText(this, "Sandles", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.sneakers -> {
                    Toast.makeText(this, "Sneakers", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.womenWear -> {
                    Toast.makeText(this, "Women's Wear", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
        listRV = findViewById(R.id.list_recyclerview)
        var database = BudgetDatabase.getInstance(this)
        val layoutManager = GridLayoutManager(this, 2)
        listRV.layoutManager = layoutManager


        itemList = ArrayList()

        itemList.add(BudgetItem(1,R.drawable.chairs,"Chair",50000))
        itemList.add(BudgetItem(2,R.drawable.cups,"Cup",60000))
        itemList.add(BudgetItem(3,R.drawable.packos,"Kettle",20000))
        itemList.add(BudgetItem(4,R.drawable.plates,"Plate",4000))
        itemList.add(BudgetItem(5,R.drawable.spoons,"Spoon",90000))
        itemList.add(BudgetItem(6,R.drawable.bag,"Bag",50400))
        itemList.add(BudgetItem(7,R.drawable.bag2,"Other bag",300000))
        itemList.add(BudgetItem(8,R.drawable.shirt_news,"Shirt News",35000))
        itemList.add(BudgetItem(9,R.drawable.white_shirt,"White Shirt",40000))
        itemList.add(BudgetItem(10,R.drawable.air_force_1,"Air Force 1",50000))
        itemList.add(BudgetItem(11,R.drawable.air_force_1_multiple,"Air Force 1 Multiple",60000))
        itemList.add(BudgetItem(12,R.drawable.air_force_brown,"Air Force Brown",20000))
        itemList.add(BudgetItem(13,R.drawable.air_force_dark_grey,"Air Force Dark Grey",4000))
        itemList.add(BudgetItem(14,R.drawable.air_force_jordan,"Air Force Jordan",90000))
        itemList.add(BudgetItem(15,R.drawable.crocs,"Crocs",50400))
        itemList.add(BudgetItem(16,R.drawable.dior_t_shirt,"Dior T Shirt",300000))
        itemList.add(BudgetItem(16,R.drawable.jordan4,"Jordan 4",35000))
        itemList.add(BudgetItem(17,R.drawable.samsung_galaxy_z_flip,"Samsung Galaxy ",40000))
        database.budgetDao().insert(itemList)

//        println("items added to db ${itemList.size}")

        database.budgetDao().getAllBudgetItems().observe(this, Observer {
          // itemList.addAll(it)
            budgetAdapter = BudgetAdapter(itemList, this)
            listRV.adapter = budgetAdapter

            println("Items from db ${it.size}")

            budgetAdapter.notifyDataSetChanged()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onItemClick(budgetItem: BudgetItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("modelled_item", budgetItem.id)
        startActivity(intent)

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
        } else if (id == R.id.account){
            intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        }
        else super.onOptionsItemSelected(item)
        return true
    }
}