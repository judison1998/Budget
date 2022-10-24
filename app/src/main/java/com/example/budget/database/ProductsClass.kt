package com.example.budget.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beust.klaxon.Json


@Entity
 data class BudgetItem(
 @PrimaryKey(autoGenerate = true)
 @Json(name = "product_id")
 var productID: Int,


 @Json(name = "product_image")
 var productImage: String,

 @Json(name = "product_name")
 var productName: String,

 @Json(name = "product_price")
 var productPrice: Double

 )

@Entity(tableName = "Cart")
data class CartItem (
 @PrimaryKey(autoGenerate = true)
 var id: Int = 0,
 var image : String,
 var itemName : String,
 var price : Double

        )



