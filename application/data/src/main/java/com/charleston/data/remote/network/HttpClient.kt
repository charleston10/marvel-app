package com.charleston.data.remote.network

import android.annotation.SuppressLint
import android.app.Application
import com.charleston.data.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class HttpClient(private val application: Application) {

    private lateinit var okHttpClient: OkHttpClient

    private companion object {
        private const val CACHE_OF_10_MB: Long = 10 * 1024 * 1024
    }

    fun <T> create(restApiClass: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com")
            .addConverterFactory(createMoshi())
            .client(createOkHttp())
            .build()
            .create(restApiClass)
    }

    private fun createOkHttp(): OkHttpClient {
        val cache = Cache(
            application.cacheDir,
            CACHE_OF_10_MB
        )

        val okHttpBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(AuthInterceptor())

        if (BuildConfig.DEBUG) {
            //enable sniffing http request
            okHttpBuilder.sslSocketFactory(provideSSLSocketFactory(), provideX509TrustManager())
        }

        okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }

    private fun createMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .build()
        )
    }

    private fun provideSSLSocketFactory(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(
            null,
            arrayOf<TrustManager>(provideX509TrustManager()),
            java.security.SecureRandom()
        )
        return sslContext.socketFactory
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun provideX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }
}