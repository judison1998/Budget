package com.example.budget.fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.BudgetAdapter
import com.example.budget.DetailsActivity
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.network.IshopNetwork
import org.json.JSONArray
import org.json.JSONTokener
import java.util.concurrent.Callable
import java.util.concurrent.Executors
class AllFragment : Fragment(), BudgetAdapter.ClickInterface {
    lateinit var budgetAdapter: BudgetAdapter
    lateinit var listRV: RecyclerView
    private var isGridLayoutManager = true
    lateinit var itemList: ArrayList<BudgetItem>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        itemList = ArrayList()
        var database = getActivity()?.let { BudgetDatabase.getInstance(it) }


//        var call = Callable {
//            IshopNetwork.getHttpResponse("http://192.168.0.127/ishop/getProducts.php")
//        }
//        //  var call = Callable{IshopNetwork.getHttpResponse("https://en.wikipedia.org/wiki/Artificial_intelligence")}
//        var results = Executors.newSingleThreadExecutor().submit(call)
//
//        val budgetItemJsonArray = JSONTokener(results.get()).nextValue() as JSONArray
//        for (i in 0 until budgetItemJsonArray.length()) {
//            val id = budgetItemJsonArray.getJSONObject(i).getInt("product_id")
//            val productName = budgetItemJsonArray.getJSONObject(i).getString("product_name")
//            val productImage = budgetItemJsonArray.getJSONObject(i).getString("product_image")
//            val productPrice = budgetItemJsonArray.getJSONObject(i).getDouble("product_price")
//            Log.i("ListActivity", "Id: $id Name: $productName product Price: $productPrice ")
//
//            var productModal = BudgetItem(id, productImage, productName, productPrice)
//            database!!.budgetDao().insert(productModal)
//
//        }

//        Log.d("Kelly", "Data received: $results")



        database!!.budgetDao().getAllBudgetItems().observe(viewLifecycleOwner, Observer {
            itemList.addAll(it)
            listRV = requireView().findViewById(com.example.budget.R.id.list_recyclerview)
            chooseLayout()
            budgetAdapter = BudgetAdapter(itemList, this)
            listRV.adapter = budgetAdapter

            println("Items from db ${it.size}")

            budgetAdapter.notifyDataSetChanged()
        }
        )
        return inflater.inflate(com.example.budget.R.layout.fragment_all, container, false)
    }

    private fun chooseLayout() {
        if (isGridLayoutManager) {
            listRV.layoutManager = GridLayoutManager(context, 2)
        } else {
            listRV.layoutManager = LinearLayoutManager(context)
            listRV.setHasFixedSize(true)
        }
//        listRV.adapter = BudgetAdapter(itemList, this)
    }

        override fun onItemClick(budgetItem: BudgetItem) {
        val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("modelled_item", budgetItem.productID)
        startActivity(intent)

        }

}