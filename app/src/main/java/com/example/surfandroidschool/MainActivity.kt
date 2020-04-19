package com.example.surfandroidschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.surfandroidschool.ui.activities.SplashActivity
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        System.out.println("я создалась")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

}
