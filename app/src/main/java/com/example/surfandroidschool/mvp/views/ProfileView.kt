package com.example.surfandroidschool.mvp.views

import com.example.surfandroidschool.memes.MemeData
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView:MvpView {

    fun memeLoad(memes:List<MemeData>)

}