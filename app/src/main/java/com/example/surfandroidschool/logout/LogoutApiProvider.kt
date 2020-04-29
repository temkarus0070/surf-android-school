package com.example.surfandroidschool.logout

import com.example.surfandroidschool.auth.RetrofitProvider

object LogoutApiProvider {
    fun getLogoutApi(): LogoutApi {
        return RetrofitProvider.retrofit().create(LogoutApi::class.java)
    }
}