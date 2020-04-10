package com.example.surfandroidschool.ui.activities

import android.os.Bundle
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.presenters.AuthPresenter
import com.example.surfandroidschool.mvp.views.AuthView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class AuthActivity:MvpAppCompatActivity(),AuthView {
    @InjectPresenter
    internal lateinit var presenter:AuthPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }


}