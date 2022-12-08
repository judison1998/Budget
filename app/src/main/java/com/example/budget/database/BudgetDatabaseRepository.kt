package com.example.budget.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.budget.network.IshopNetwork
import com.example.budget.network.ProductApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BudgetDatabaseRepository( var budgetDao: BudgetDao) : BudgetRepository {
    private val _products = MutableLiveData<List<BudgetItem>>()

    val uri = "192.168.1.101/ishop/getProducts.php"

    override fun getAllBudgetItems(): LiveData<List<BudgetItem>> {
        return budgetDao.getAllBudgetItems()
    }

    override fun insert(budgetItem: BudgetItem) {
        budgetDao.insert(budgetItem)
    }
    val products : LiveData<List<BudgetItem>>
        get() = _products


}
