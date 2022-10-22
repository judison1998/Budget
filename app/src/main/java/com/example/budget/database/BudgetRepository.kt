package com.example.budget.database

import androidx.lifecycle.LiveData

interface BudgetRepository {

    fun getAllBudgetItems(): LiveData<List<BudgetItem>>

    fun insert(budgetItem: BudgetItem)

//    fun getProducts(): String?
}