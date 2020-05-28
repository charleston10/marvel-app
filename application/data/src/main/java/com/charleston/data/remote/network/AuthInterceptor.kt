package com.charleston.data.remote.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest

class AuthInterceptor : Interceptor {

    companion object {
        private const val TIMESTAMP_KEY = "ts"
        private const val HASH_KEY = "hash"
        private const val API_KEY = "apikey"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        val publicKey = "0838df2b5cd52a2e8d38d7be86e041fead3b43a2"
        val hash: String = generateHash(timestamp, publicKey, "5ef027befa4ba7dc659518d6d6925d57")

        var request: Request = chain.request()

        val url: HttpUrl = request.url()
            .newBuilder()
            .addQueryParameter(TIMESTAMP_KEY, timestamp)
            .addQueryParameter(API_KEY, publicKey)
            .addQueryParameter(HASH_KEY, hash)
            .build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }


    private fun generateHash(
        timestamp: String,
        publicKey: String,
        privateKey: String
    ): String {
        val value = timestamp + privateKey + publicKey
        return value.toMd5()
    }

    private fun String.toMd5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}