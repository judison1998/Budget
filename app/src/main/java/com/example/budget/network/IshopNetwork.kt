package com.example.budget.network

import android.util.Log
import com.example.budget.database.BudgetItem
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


private const val BASE_URL = "http://192.168.0.199/ishop/"
//private const val BASE_URL = "http://192.168.0.127/ishop/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface IshopNetwork {
    @GET("getProducts.php")
    fun getProducts():
            Deferred<List<BudgetItem>>
}
object ProductApi {
    val retrofitService : IshopNetwork by lazy {
        retrofit.create(IshopNetwork::class.java)
    }
}






//class IshopNetwork {
//
//    companion object {
//        private val LOG_TAG: String = ("http://127.0.0.1/ishop/getProducts.php")
//
//        private val BASE_URL = "\"http://192.168.0.127//ishop/getProducts.php\""
//
//
//        fun getUrl(uri: String?): URL? {
//            return try {
//                URL(uri)
//            } catch (e: MalformedURLException) {
//                e.printStackTrace()
//                null
//            }
//        }
//
//        fun getHttpResponse(uri: String?): String? {
//            val url: URL = getUrl(uri)!!
//            var connection: HttpURLConnection? = null
//            return try {
//                connection = url.openConnection() as HttpURLConnection
//                connection!!.requestMethod = "GET"
//                val stream = connection.inputStream
//                val scanner = Scanner(stream)
//                scanner.useDelimiter("\\A")
//                val hasNext = scanner.hasNext()
//                if (hasNext) {
//                    val results = scanner.next()
//
//                    Log.i(
//                        "Jude",
//                        "The data from server is: $results"
//                    )
//                    results
//                } else {
//                    Log.d("Kelly", "The response is null")
//                    null
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Log.d("Keth", "Oops, error: " + e.message)
//                null
//            } finally {
//                connection?.disconnect()
//            }
//        }
//    }
//}




