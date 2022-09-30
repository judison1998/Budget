package com.example.budget.database

import androidx.lifecycle.LiveData

class BudgetDatabaseRepository(private var budgetDao: BugetDao) : BudgetRepository {

    override fun getAllBudgetItems(): LiveData<List<BudgetItem>> {
        return budgetDao.getAllBudgetItems()
    }

    override fun insert(budgetItem: BudgetItem) {
        budgetDao.insert(budgetItem)
    }

}