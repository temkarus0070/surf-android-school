package com.example.surfandroidschool.auth

import com.google.gson.Gson
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.*

interface AuthApi {
    @POST("auth/login")
    fun auth(@Body user:User): Observable<AuthInfo>

    companion object Factory{
        private const val BASE_URL:String = "http://demo2407529.mockable.io"

        fun create():AuthApi{
            var gson= Gson()
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
            return retrofit.create(AuthApi::class.java)
        }
    }
}