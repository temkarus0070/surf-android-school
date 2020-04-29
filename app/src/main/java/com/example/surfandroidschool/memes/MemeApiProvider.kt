package com.example.surfandroidschool.memes

import com.example.surfandroidschool.auth.RetrofitProvider

object MemeApiProvider {
    fun getMemeApi(): MemeApi {
        return RetrofitProvider.retrofit().create(MemeApi::class.java)
    }
}