package com.example.budget.database

import androidx.lifecycle.LiveData
import com.example.budget.network.IshopNetwork

class BudgetDatabaseRepository(private var budgetDao: BugetDao) : BudgetRepository {

    val uri = "192.168.1.101/ishop/getProducts.php"

    override fun getAllBudgetItems(): LiveData<List<BudgetItem>> {
        return budgetDao.getAllBudgetItems()
    }

    override fun insert(budgetItem: BudgetItem) {
        budgetDao.insert(budgetItem)
    }

//    override fun getProducts(): String? {
//        var  ishopNetwork = IshopNetwork()
//        return ishopNetwork.getHttpResponse(uri)
//
//   }


}