package com.example.surfandroidschool.mvp.presenters

import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.surfandroidschool.mvp.views.ProfileView
import com.example.surfandroidschool.vm.MemesViewModel
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {

    lateinit var memesViewModel: MemesViewModel

    override fun attachView(view: ProfileView?) {
        super.attachView(view)
    }

    fun memeLoading(fragment: Fragment) {

        memesViewModel = ViewModelProviders.of(fragment)
            .get(MemesViewModel::class.java)
        memesViewModel.allMemes.observe(fragment, Observer {
            viewState.memeLoad(it)
        })

    }


}