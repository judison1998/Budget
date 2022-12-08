package com.example.budget.database
import android.app.Application
import androidx.lifecycle.*
import com.example.budget.network.ProductApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
class BudgetViewModel(val database: BudgetDao, application: Application) :
    AndroidViewModel(application) {
    private val _navigateToSelectedProperty = MutableLiveData<BudgetItem>()
    private val _products = MutableLiveData<List<BudgetItem>>()
    val products: LiveData<List<BudgetItem>>
        get() = _products

    val navigateToSelectedProperty: LiveData<BudgetItem>
        get() = _navigateToSelectedProperty

    fun displayPropertyDetails(budgetItem: BudgetItem) {
        _navigateToSelectedProperty.value = budgetItem
    }

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAllProducts()
    }
    private fun getAllProducts() {
        coroutineScope.launch {
            var getProductsDeferred = ProductApi.retrofitService.getProducts()
            try {
                var listResult = getProductsDeferred.await()
                if (listResult.isNotEmpty()) {
                    _products.value = listResult
                    for (i in 0 until listResult.size) {
                        val Pid = listResult.get(i).productID
                        val Pname = listResult.get(i).productName
                        val Pprice = listResult.get(i).productPrice
                        val Pimage = listResult.get(i).productImage
                        val productModal = BudgetItem(Pid, Pname, Pprice, Pimage)
                        database.insert(productModal)
                    }
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}