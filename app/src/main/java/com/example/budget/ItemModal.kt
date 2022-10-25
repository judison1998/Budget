package com.example.budget
import com.squareup.moshi.Json
//import com.beust.klaxon.Json
data class ItemModal (
    @Json(name = "product_id")
    val productID: String,

    @Json(name = "product_name")
    val productName: String,

    @Json(name = "product_price")
    val productPrice: String,
    @Json(name = "product_image")
    val productImage: String
)



