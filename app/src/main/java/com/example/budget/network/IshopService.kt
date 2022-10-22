package com.example.budget.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit.http.GET
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
        @GET("products")
       suspend fun getProducts(): List<Products>
    }

    object IshopApi {
        val retrofitService : IshopApiService by lazy {
            retrofit.create(IshopApiService::class.java)
        }
    }



