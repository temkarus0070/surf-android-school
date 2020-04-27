package com.example.surfandroidschool.mvp.views

import android.content.SharedPreferences
import com.example.surfandroidschool.auth.AuthInfo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AuthView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun endAuth(authInfo: AuthInfo)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun nextScreen()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun viewError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun rippleButton()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun endRippleButton()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun checkAuth()
}