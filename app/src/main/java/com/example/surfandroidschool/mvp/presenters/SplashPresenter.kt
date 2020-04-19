package com.example.surfandroidschool.mvp.presenters


import com.example.surfandroidschool.mvp.views.SplashView
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.*

@InjectViewState
class SplashPresenter : MvpPresenter<SplashView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    fun setTimer() {
        var timer: Timer = Timer()
        var task: TimerTask = MyTimerTask(this)
        timer.schedule(task, 300)
    }
}

class MyTimerTask(private val presenter: MvpPresenter<SplashView>) : TimerTask() {
    override fun run() {
        if (presenter.viewState != null) {
            presenter.viewState.openNext()
        }
        presenter.destroyView(presenter.viewState)

    }
}
