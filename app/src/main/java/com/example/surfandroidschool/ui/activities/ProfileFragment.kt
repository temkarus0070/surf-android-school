package com.example.surfandroidschool.ui.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
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

    fun setToolbar(){
        var toolbar = activity?.findViewById<Toolbar>(R.id.topToolbar)
        toolbar?.setBackgroundColor(resources.getColor(R.color.memeLayoutBackColor))
        toolbar?.menu?.clear()
        toolbar?.title=null
        toolbar?.inflateMenu(R.menu.menu_profile)
    }

    override fun onStart() {
        setToolbar()
        super.onStart()
    }


}