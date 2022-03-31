package ru.unicorecms.unicoreconnect.common.http

import okhttp3.Response
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import java.io.IOException

class HttpHelper(val response: Response) {
    val body: String = response.body!!.string()

    inline fun <reified T>  getOrThrow(): T {
        if (!response.isSuccessful)
            throw IOException(response.toString())

        return UnicoreCommon.gson.fromJson(body, T::class.java)
    }
}