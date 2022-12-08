package com.example.budget.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Parcelize
@Entity
data class BudgetItem(
    @PrimaryKey(autoGenerate = true)
// @Json(name = "product_id")
    @Json(name = "product_id")
    var productID: Int,

// @Json(name = "product_name")
    @Json(name = "product_name")

    var productName: String,

// @Json(name = "product_price")
    @Json(name = "product_price")
    var productPrice: String,


// @Json(name = "product_image")
    @Json(name = "product_image")
    val productImage: String,
) : Parcelable


@Entity(tableName = "Cart")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var image: String,
    var itemName: String,
    var price: String

)



