package com.example.budget.network

import com.squareup.moshi.Json

data class Products (
    @Json(name = "product_id")
    val productID: String,

    @Json(name = "product_name")
    val productName: String,

    @Json(name = "product_price")
    val productPrice: String,

    @Json(name = "product_image")
    val productImage: String

         )
