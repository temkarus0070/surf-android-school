package com.example.surfandroidschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.forEach
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.surfandroidschool.ui.activities.MemeCreateFragment
import com.example.surfandroidschool.ui.activities.SplashActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter
    lateinit var ft:FragmentTransaction
    lateinit var bottomNavView:BottomNavigationView
    private var memeFragment = MemesFragment()
    private var fragmentManager:FragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView = findViewById(R.id.bottomNavView)
        ft = fragmentManager.beginTransaction()
        ft.add(R.id.appFragment,memeFragment,"memesFragment")
        ft.commit()
        bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.showMemes->{
                    ft = fragmentManager.beginTransaction()
                    if(fragmentManager.findFragmentByTag("memesFragment") == null)
                    ft.add(R.id.appFragment,memeFragment,"memesFragment")
                    else
                        ft.replace(R.id.appFragment,memeFragment,"memesFragment")
                    ft.commit()
                    true
                }
                R.id.addMemes->{
                    ft = fragmentManager.beginTransaction()
                    ft.replace(R.id.appFragment,MemeCreateFragment(),"createFragment")
                    ft.commit()
                    true
                }
                R.id.profile->{

                    true
                }
                else->{

                    true
                }
            }
        }
    }




}
