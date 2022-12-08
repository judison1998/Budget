package com.example.budget.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.budget.R
import com.example.budget.database.BudgetItem

class DetailViewModel(budgetItem: BudgetItem, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<BudgetItem>()
    val selectedProperty: LiveData<BudgetItem>
        get() = _selectedProperty

    init {
        _selectedProperty.value = budgetItem
    }

    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it !=null) {
            true -> R.string.price
                false ->R.string.cart
            }, it.productPrice)
    }
    val displayPropertyName = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it !=null) {
                true -> R.string.itemName
                false ->R.string.cart
            }, it.productName)
    }

    // The displayPropertyType formatted Transformation Map LiveData, which displays the
    // "For Rent/Sale" String
//    val displayPropertyName = Transformations.map(selectedProperty) {
//        app.applicationContext.getString(R.string.itemName,
//            app.applicationContext.getString(
//                when(it ! =null) {
//                    true -> R.string.type_rent
//                    false -> R.string.type_sale
//                }))
//    }
}