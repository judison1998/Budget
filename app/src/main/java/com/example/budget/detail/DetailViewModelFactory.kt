package com.example.budget.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.budget.database.BudgetItem

class DetailViewModelFactory(
    private val budgetItem: BudgetItem,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(budgetItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}