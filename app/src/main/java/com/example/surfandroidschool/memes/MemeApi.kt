package com.example.surfandroidschool.memes

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface MemeApi {
    @GET("memes")
    fun getMemes(): Observable<List<MemeData>>

}