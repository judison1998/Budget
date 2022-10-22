package com.example.budget.network

import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class IshopNetwork {

    companion object {
        private val LOG_TAG: String = ("http://127.0.0.1/ishop/getProducts.php")

        private val BASE_URL = "\"http://192.168.0.127//ishop/getProducts.php\""


        fun getUrl(uri: String?): URL? {
            return try {
                URL(uri)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                null
            }
        }

        fun getHttpResponse(uri: String?): String? {
            val url: URL = getUrl(uri)!!
            var connection: HttpURLConnection? = null
            return try {
                connection = url.openConnection() as HttpURLConnection
                connection!!.requestMethod = "GET"
                val stream = connection.inputStream
                val scanner = Scanner(stream)
                scanner.useDelimiter("\\A")
                val hasNext = scanner.hasNext()
                if (hasNext) {
                    val results = scanner.next()
                    Log.i(
                        "Jude",
                        "The data from server is: $results"
                    )
                    results
                } else {
                    Log.d("Kelly", "The response is null")
                    null
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d("keth", "Oops, error: " + e.message)
                null
            } finally {
                connection?.disconnect()
            }
        }
    }
}




