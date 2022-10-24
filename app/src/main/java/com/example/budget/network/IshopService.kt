package com.example.budget.network


import com.example.budget.database.BudgetItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

    private val BASE_URL = "192.168.1.101/ishop/getProducts.php"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    interface IshopApiService {
        @retrofit2.http.GET("products")
       suspend fun getProducts(): List<BudgetItem>
    }

    object IshopApi {
        val retrofitService : IshopApiService by lazy {
            retrofit.create(IshopApiService::class.java)
        }
    }



