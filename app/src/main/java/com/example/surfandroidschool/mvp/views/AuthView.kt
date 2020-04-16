package com.example.surfandroidschool.mvp.views

import com.example.surfandroidschool.auth.AuthInfo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface AuthView:MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun endAuth(authInfo: AuthInfo)
}