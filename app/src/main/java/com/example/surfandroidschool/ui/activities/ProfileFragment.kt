package com.example.surfandroidschool.ui.activities

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.surfandroidschool.MemesFragment
import com.example.surfandroidschool.R
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.memes.memesAdapter
import com.example.surfandroidschool.mvp.presenters.ProfilePresenter
import com.example.surfandroidschool.mvp.views.ProfileView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class ProfileFragment:MvpAppCompatFragment(), ProfileView {
    @InjectPresenter
    lateinit var presenter: ProfilePresenter
    lateinit var adapter:memesAdapter
    lateinit var memesList:RecyclerView
    lateinit var thisView:View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thisView = inflater.inflate(R.layout.fragment_profile,container,false)
        return thisView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadAdapter()
        super.onViewCreated(view, savedInstanceState)
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
        presenter.memeLoading(this)
        val prefs = thisView.context.getSharedPreferences("auth",Context.MODE_PRIVATE)
        val name = prefs.getString("username","")
        val descr = prefs.getString("userDescription","")
        thisView.findViewById<TextView>(R.id.profileName).text = name
        thisView.findViewById<TextView>(R.id.profileDescription).text = descr
        super.onStart()

    }

    fun loadAdapter(){
        memesList = thisView.findViewById(R.id.profileMemesList)!!
        memesList.adapter = adapter
    }

    override fun onAttach(context: Context) {
        if(context is Activity)
        {
            adapter = memesAdapter(context as Activity,::itemClicker)
        }

        super.onAttach(context)
    }



    fun itemClicker(data:MemeData, position:Int):Unit{
        val ft = fragmentManager?.beginTransaction()
        val fragment=MemeViewFragment()
        val bundle=Bundle()
        bundle.putSerializable("meme",data)
        fragment.arguments=bundle
        ft?.replace(R.id.appFragment,fragment,"memeView")
        ft?.commit()

    }


    override fun memeLoad(memes: List<MemeData>) {

        adapter.setData(memes)
        val progress = thisView.findViewById<ProgressBar>(R.id.loadingProgress)
        progress.visibility = View.GONE
        progress.isIndeterminate = false
    }


}