package com.example.budget.database

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val datasource: BudgetDao,private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BudgetViewModel::class.java)){
                return BudgetViewModel(datasource,application) as T
            }
            throw IllegalArgumentException("UnKnown ViewModel class")

        }
    }