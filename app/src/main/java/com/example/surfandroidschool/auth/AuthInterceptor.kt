package com.example.surfandroidschool.auth

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private  val authErrorCallBack:()->Unit):Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        val responseCode = response.code()
        if(responseCode == 400)
        {
            authErrorCallBack()
        }
        return response
    }

}