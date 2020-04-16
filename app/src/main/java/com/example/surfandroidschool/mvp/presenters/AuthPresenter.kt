package com.example.surfandroidschool.mvp.presenters

import com.example.surfandroidschool.auth.AuthApi
import com.example.surfandroidschool.auth.AuthInfo
import com.example.surfandroidschool.auth.User
import com.example.surfandroidschool.mvp.views.AuthView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AuthPresenter:MvpPresenter<AuthView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun auth(login:String, password:String){
        var api = AuthApi.create()
        var auth:Observable<AuthInfo> = api.auth(User(login,password))

        auth.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
               viewState.endAuth(it)
            },
                {
                    it.printStackTrace()
                }
            )
    }

}