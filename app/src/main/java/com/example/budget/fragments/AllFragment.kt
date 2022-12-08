package com.example.budget.fragments
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.budget.BudgetAdapter
import com.example.budget.database.ViewModelFactory
import com.example.budget.detail.DetailsActivity
import com.example.budget.database.*
import com.example.budget.databinding.FragmentAllBinding

class AllFragment : Fragment(){
    lateinit var itemList: ArrayList<BudgetItem>
    override fun onCreateView(


        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val binding = FragmentAllBinding.inflate(inflater)
        itemList = ArrayList()
        setHasOptionsMenu(true)
        val application = requireNotNull(this.activity).application
        val dataSource = BudgetDatabase.getInstance(application).budgetDao()
        val viewModelFactory = ViewModelFactory(dataSource,application)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(BudgetViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.photosGrid.adapter = BudgetAdapter(BudgetAdapter.OnClickListener{
            Log.i("product: ",it.productName)
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("modelled_item", it.productID)
            startActivity(intent)

        })

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                val intent = Intent(activity, DetailsActivity::class.java)
        intent.putExtra("modelled_item", it.productID)
        startActivity(intent)
            }
        })

        return binding.root
    }


}