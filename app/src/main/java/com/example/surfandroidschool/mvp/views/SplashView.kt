package com.example.surfandroidschool.mvp.views

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface SplashView : MvpView {

    fun openNext()
}