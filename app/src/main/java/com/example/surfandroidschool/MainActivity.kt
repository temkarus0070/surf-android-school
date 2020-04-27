package com.example.surfandroidschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.view.forEach
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.surfandroidschool.ui.activities.SplashActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter
    lateinit var bottomNavView:BottomNavigationView
    private var memeFragment = MemesFragment()
    private var fragmentManager:FragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView = findViewById(R.id.bottomNavView)
        var ft = fragmentManager.beginTransaction()
        ft.add(R.id.appFragment,memeFragment)
        ft.commit()
    }


}
