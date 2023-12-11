package com.higor.search.app.data.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object ClientNetwork {

    fun provideOkHttpClient() = OkHttpClient
        .Builder()
        .build()


    inline fun <reified T> createRetrofitService(okHttpClient: OkHttpClient, baseURL: String): T =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseURL)
            .build()
            .create(T::class.java)

}
