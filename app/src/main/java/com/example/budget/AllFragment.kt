package com.example.budget

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.R
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.databinding.FragmentAllBinding
import com.example.budget.network.IshopNetwork
import org.json.JSONArray
import org.json.JSONTokener
import java.util.concurrent.Callable
import java.util.concurrent.Executors

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AllFragment : Fragment(), BudgetAdapter.ClickInterface {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentAllBinding? = null

    private val binding get() = _binding!!

    lateinit var budgetAdapter: BudgetAdapter

    lateinit var listRV: RecyclerView

    private var isGridLayoutManager = true

    lateinit var itemList: ArrayList<BudgetItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        itemList = ArrayList()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        var database = BudgetDatabase.getInstance(context as Application)


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



        database.budgetDao().getAllBudgetItems().observe(this, Observer {
            itemList.addAll(it)
            budgetAdapter = BudgetAdapter(itemList, this)
            listRV.adapter = budgetAdapter

            println("Items from db ${it.size}")

            budgetAdapter.notifyDataSetChanged()
        }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listRV = binding.listRecyclerview
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun chooseLayout() {
        if (isGridLayoutManager) {
            listRV.layoutManager = GridLayoutManager(context,2)
        } else {
            listRV.layoutManager = LinearLayoutManager(context)
        }
        listRV.adapter = BudgetAdapter(itemList,this)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
        fun newInstance() =
            AllFragment()

    }

    override fun onItemClick(budgetItem: BudgetItem) {
//        val intent = Intent(this, DetailsActivity::class.java)
//        intent.putExtra("modelled_item", budgetItem.productID)
//        startActivity(intent)

    }
}