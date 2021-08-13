package com.sample.jetpack

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {
    private var retrofit: Retrofit = Retrofit.Builder().baseUrl(API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private const val API_URL = "https://api.douban.com/v2/movie/in_theaters/"
        private const val API_KEY = "054022eaeae0b00e0fc068c0c0a2102a"

        private var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }

        @Synchronized
        fun get(): RetrofitClient{
            return instance!!
        }
    }

    fun getApi(): Api {
        return retrofit.create(Api::class.java)
    }

    fun getClient(): OkHttpClient {
        var httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(object : Interceptor{
            override fun intercept(chain: Interceptor.Chain): Response {
                var original = chain.request()
                var originalHttpUrl = original.url()
                var url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .build()
                var requestBuilder = original.newBuilder().url(url)
                var request = requestBuilder.build()
                return chain.proceed(request)
            }
        })

        return httpClient.build()
    }
}
