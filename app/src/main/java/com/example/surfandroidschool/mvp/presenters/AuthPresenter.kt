package com.example.surfandroidschool.mvp.presenters

import com.example.surfandroidschool.mvp.views.AuthView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AuthPresenter:MvpPresenter<AuthView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

}