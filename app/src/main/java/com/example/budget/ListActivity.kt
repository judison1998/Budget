package com.example.budget

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.budget.account.ProfileActivity
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.databinding.ActivityListBinding
import com.example.budget.fragments.*
import com.example.budget.network.IshopNetwork
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONArray
import org.json.JSONTokener
import java.util.concurrent.Callable
import java.util.concurrent.Executors


class ListActivity : AppCompatActivity()  {

    lateinit var bottomNav: BottomNavigationView

    lateinit var budgetAdapter: BudgetAdapter

    lateinit var itemList: ArrayList<BudgetItem>


    private lateinit var pager: ViewPager2
    private lateinit var tab: TabLayout
    private lateinit var toolbar: Toolbar

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityListBinding.inflate(LayoutInflater.from(applicationContext))
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        toolbar = findViewById(R.id.toolbar)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = FragmentAdapter(this)

        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = "Tab ${position}"
        }.attach()

//        setSupportActionBar(bar)

//        val adapter = ViewPagerAdapter(supportFragmentManager)

//        adapter.addFragment(AllFragment(), "All")
//        adapter.addFragment(MenFragment(), "Men")
//        adapter.addFragment(WomenFragment(), "Women")
//        adapter.addFragment(KidsFragment(), "Kids")
//        pager.adapter = adapter
//        tab.setupWithViewPager(pager)
        bottomNav = findViewById(R.id.bottomNav)

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)


        bottomNav?.setOnItemSelectedListener {
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
    class FragmentAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> AllFragment.newInstance()
                1 -> MenFragment.newInstance()
                2 -> WomenFragment.newInstance()
                3 -> KidsFragment.newInstance()
                else -> AllFragment.newInstance()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu!!.findItem(R.id.actionSearch)
        val searchView: SearchView = searchItem.getActionView() as SearchView
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
            if (item.productName.toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            budgetAdapter.filterList(filteredlist)
        }
    }
}