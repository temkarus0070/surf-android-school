package com.example.surfandroidschool.mvp.presenters

import android.content.SharedPreferences
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
class AuthPresenter : MvpPresenter<AuthView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun auth(login: String, password: String, prefs: SharedPreferences) {
        val api = AuthApi.create { error() }
        val auth: Observable<AuthInfo> = api.auth(User(login, password))

        auth.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it?.accessToken != null) {
                    val editor = prefs.edit()
                    editor?.putString("id", it.userInfo.id.toString())
                    editor?.putString("username", it.userInfo.username)
                    editor?.putString("firstName", it.userInfo.firstName)
                    editor?.putString("lastName", it.userInfo.lastName)
                    editor?.putString("userDescription", it.userInfo.userDescription)
                    editor?.apply()
                    viewState.nextScreen()
                }
                viewState.endAuth(it)

            },
                {
                    it.printStackTrace()
                }
            )
    }

    private fun error() {
        viewState.viewError()
    }

}