package ru.unicorecms.unicoreconnect.common.http

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import java.io.IOException
import java.util.concurrent.TimeUnit

class UnicoreRequester {
    private var config = UnicoreCommon.config
    private val JSON = "application/json; charset=utf-8".toMediaType()
    val client: OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .callTimeout(10, TimeUnit.SECONDS)
        .addInterceptor {
            val newRequest = it.request().newBuilder()
                .addHeader("Authorization", "Api-Key ${config.apiKey}")
                .build()

            it.proceed(newRequest)
        }
        .build()

    @Throws(IOException::class)
    fun <T>post(url: String, data: T): HttpHelper {
        val body = UnicoreCommon.gson.toJson(data).toRequestBody(JSON)
        val request = Request.Builder().url(url).post(body).build()

        return client.newCall(request).execute().use { response ->
            HttpHelper(response)
        }
    }

    @Throws(IOException::class)
    fun <T>patch(url: String, data: T): HttpHelper {
        val body = UnicoreCommon.gson.toJson(data).toRequestBody(JSON)
        val request = Request.Builder().url(url).patch(body).build()

        return client.newCall(request).execute().use { response ->
            HttpHelper(response)
        }
    }

    @Throws(IOException::class)
    fun get(url: String): HttpHelper {
        val request = Request.Builder().url(url).build()

        return client.newCall(request).execute().use { response ->
            HttpHelper(response)
        }
    }

    @Throws(IOException::class)
    fun delete(url: String): HttpHelper {
        val request = Request.Builder().url(url).delete().build()

        return client.newCall(request).execute().use { response ->
            HttpHelper(response)
        }
    }
}