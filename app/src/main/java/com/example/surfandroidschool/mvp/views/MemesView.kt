package com.example.surfandroidschool.mvp.views

import com.example.surfandroidschool.memes.MemeData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MemesView : MvpView {
    fun showMemes(memesList: List<MemeData>)
    fun initialize()
    fun showError()
    fun hideRefreshBar()
    fun setToolbar()
}