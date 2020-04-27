package com.example.surfandroidschool.mvp.presenters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.surfandroidschool.R
import com.example.surfandroidschool.memes.MemeApiProvider
import com.example.surfandroidschool.mvp.views.MemesView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MemesPresenter:MvpPresenter<MemesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadMemes()

    }

    public fun loadMemes(){
        val api=MemeApiProvider.getMemeApi()
        val memes = api.getMemes()
        memes.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(             {
                viewState.showMemes(it)
                viewState?.hideRefreshBar()
            } ,
                {
                    it.printStackTrace()
                    viewState.showError()
                })


    }

}