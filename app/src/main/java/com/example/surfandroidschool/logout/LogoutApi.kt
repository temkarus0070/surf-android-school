package com.example.surfandroidschool.logout

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.POST
import java.util.*

interface LogoutApi {
    @POST("auth/logout")
    fun logout():Observable<LogoutInfo>
}