package com.example.budget.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.budget.ItemModal
import com.example.budget.network.IshopApi
//import com.example.budget.network.IshopApi
import kotlinx.coroutines.launch



class BudgetViewModel(application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status
//
//    private val _products = MutableLiveData<List<ItemModal>>()
//    val properties: LiveData<List<ItemModal>>
//        get() = _products

    private val budgetRepository: BudgetRepository

    init {
        val budgetDao = BudgetDatabase.getInstance(application).budgetDao()
        budgetRepository = BudgetDatabaseRepository(budgetDao)

//        getProducts()
    }

    val allBudgetItems: LiveData<List<BudgetItem>> = budgetRepository.getAllBudgetItems()

//    private fun getProducts() {
//        viewModelScope.launch {
//            try {
//               val listResult = IshopApi.retrofitService.getProducts()
//                _status.value = "Success: ${listResult.size} Products retrieved"
//
//            } catch (e:Exception){
//                _status.value = "Failure: ${e.message}"
//            }
//
//        }
//
//    }

}