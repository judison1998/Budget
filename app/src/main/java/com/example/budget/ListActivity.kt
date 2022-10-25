package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.account.ProfileActivity
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.network.IshopNetwork
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray
import org.json.JSONTokener
import java.util.concurrent.Callable
import java.util.concurrent.Executors


class ListActivity : AppCompatActivity(), BudgetAdapter.ClickInterface {

    lateinit var bottomNav: BottomNavigationView

    lateinit var listRV: RecyclerView

    lateinit var budgetAdapter: BudgetAdapter

    lateinit var itemList: ArrayList<BudgetItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var database = BudgetDatabase.getInstance(application)


        var call = Callable {
            IshopNetwork.getHttpResponse("http://192.168.0.127/ishop/getProducts.php")
        }
        //  var call = Callable{IshopNetwork.getHttpResponse("https://en.wikipedia.org/wiki/Artificial_intelligence")}
        var results = Executors.newSingleThreadExecutor().submit(call)

        val budgetItemJsonArray = JSONTokener(results.get()).nextValue() as JSONArray
        for (i in 0 until budgetItemJsonArray.length()) {

            val id = budgetItemJsonArray.getJSONObject(i).getInt("product_id")
            val productName = budgetItemJsonArray.getJSONObject(i).getString("product_name")
            val productImage = budgetItemJsonArray.getJSONObject(i).getString("product_image")
            val productPrice = budgetItemJsonArray.getJSONObject(i).getDouble("product_price")
            Log.i("ListActivity", "Id: $id Name: $productName product Price: $productPrice ")

            var productModal = BudgetItem(id, productImage, productName, productPrice)
            database.budgetDao().insert(productModal)

        }

        Log.d("Kelly", "Data received: $results")

        bottomNav = findViewById(R.id.bottomNav)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val i = Intent(this, ListActivity::class.java)
                    startActivity(i)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.profile -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.categories -> {
                    val i = Intent(this, ListActivity::class.java)
                    startActivity(i)
                    return@setOnNavigationItemReselectedListener
                }
                R.id.help -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                    return@setOnNavigationItemReselectedListener

                }
            }

            listRV = findViewById(R.id.list_recyclerview)

            val layoutManager = GridLayoutManager(this, 2)
            listRV.layoutManager = layoutManager


            itemList = ArrayList()



            database.budgetDao().getAllBudgetItems().observe(this, Observer {
                itemList.addAll(it)
                budgetAdapter = BudgetAdapter(itemList, this)
                listRV.adapter = budgetAdapter

                println("Items from db ${it.size}")

                budgetAdapter.notifyDataSetChanged()
            })
        }
    }
    override fun onItemClick(budgetItem: BudgetItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("modelled_item", budgetItem.productID)
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
        } else if (id == R.id.account) {
            intent = Intent(applicationContext, ProfileActivity::class.java)
            startActivity(intent)
        } else super.onOptionsItemSelected(item)
        return true
    }
}