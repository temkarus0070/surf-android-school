package com.example.surfandroidschool.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.views.ProfileView
import moxy.MvpAppCompatFragment

class ProfileFragment:MvpAppCompatFragment(), ProfileView {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }
}