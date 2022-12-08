package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.viewpager.widget.ViewPager
import com.example.budget.account.ProfileActivity
import com.example.budget.database.BudgetItem
import com.example.budget.databinding.ActivityListBinding
import com.example.budget.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class ListActivity : AppCompatActivity() {
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout
    private lateinit var bar: Toolbar

    lateinit var bottomNav: BottomNavigationView

    lateinit var budgetAdapter: BudgetAdapter

    lateinit var itemList: ArrayList<BudgetItem>
    lateinit var navController: NavController
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemList = ArrayList()
        pager = findViewById(R.id.viewPager)
        tab = findViewById(R.id.tabs)
        bar = findViewById(R.id.toolbar)

//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                replace(
//                    R.id.frame_layout,
//                    AllFragment.newInstance(),
//                    AllFragment::class.java.simpleName
//                )
//            }
//        }


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AllFragment(), "All")
        adapter.addFragment(MenFragment(), "Men")
        adapter.addFragment(WomenFragment(), "Women")
        adapter.addFragment(KidsFragment(), "Kids")
        pager.adapter = adapter
        tab.setupWithViewPager(pager)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val i = Intent(this, ListActivity::class.java)
                    startActivity(i)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.profile -> {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.help -> {
                    val i = Intent(this, HelpActivity::class.java)
                    startActivity(i)
                    finish()
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                filter(msg)
                return false
            }

        })
        return true

    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<BudgetItem> = ArrayList()

        for (item in itemList) {
            if (item.productName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {

//            budgetAdapter.filterList(filteredlist)
        }
    }

}