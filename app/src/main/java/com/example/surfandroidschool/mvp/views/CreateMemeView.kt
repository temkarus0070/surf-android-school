package com.example.surfandroidschool.mvp.views

import android.view.View
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CreateMemeView:MvpView {
    fun goOut()
}