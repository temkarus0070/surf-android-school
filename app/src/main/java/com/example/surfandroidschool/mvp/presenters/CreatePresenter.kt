package com.example.surfandroidschool.mvp.presenters

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.surfandroidschool.MainActivity
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.mvp.views.CreateMemeView
import com.example.surfandroidschool.repository.MemesRepository
import com.example.surfandroidschool.vm.MemesViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

import moxy.InjectViewState
import moxy.MvpPresenter
@InjectViewState
class CreatePresenter:MvpPresenter<CreateMemeView>() {
    private var lastId:Long = 0

    lateinit var memeViewModel: MemesViewModel

    fun createMem(meme:MemeData,context:Context?, fragment:Fragment){
        memeViewModel = ViewModelProviders.of(fragment)
            .get(MemesViewModel::class.java)
        var list = memeViewModel.allMemes
        list.observe(fragment, Observer {
                memes->
            memes?.let {
                if(it.isNotEmpty()) {
                    lastId = it.last().id.toLong()
                    lastId = lastId + 1
                }
            }
            meme.id = lastId.toString()

        }).run {
            memeViewModel.insert(meme)
            viewState.goOut()
        }


    }
}