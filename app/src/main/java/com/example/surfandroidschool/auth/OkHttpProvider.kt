package com.example.surfandroidschool.auth

import android.content.SharedPreferences
import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object OkHttpProvider {

    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}


object RetrofitProvider {
    val BASE_URL: String = "https://demo2407529.mockable.io"
    fun retrofit(): Retrofit {
        var client: OkHttpClient = OkHttpProvider.okHttpClient()
        val gson = Gson()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}

object AuthApiProvider {
    fun getAuthApi(): AuthApi {
        return RetrofitProvider.retrofit().create(AuthApi::class.java)
    }
}