package com.example.surfandroidschool.ui.activities


import android.content.Intent
import android.os.Bundle
import com.example.surfandroidschool.MainActivity
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.presenters.SplashPresenter
import com.example.surfandroidschool.mvp.views.SplashView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

public class SplashActivity:MvpAppCompatActivity(),SplashView {
    @InjectPresenter
    internal lateinit var presenter: SplashPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.setTimer()

    }

    override fun openNext() {

        startActivity(Intent(this,AuthActivity::class.java))
    }




}