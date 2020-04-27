package com.example.surfandroidschool

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.memes.memesAdapter
import com.example.surfandroidschool.mvp.presenters.MemesPresenter
import com.example.surfandroidschool.mvp.views.MemesView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

public class MemesFragment:MvpAppCompatFragment(),MemesView{
    @InjectPresenter
    lateinit var memesPresenter:MemesPresenter
    lateinit var refresher:SwipeRefreshLayout
    lateinit var adapter:memesAdapter
    lateinit var listMemes:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    override fun showMemes(memesList:List<MemeData>) {
        adapter.setData(memesList)
    }


    override fun setToolbar() {
        val toolbar= activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.topToolbar)
        if(toolbar?.menu?.size()!=0)
            toolbar?.menu?.clear()
        toolbar?.title = "Популярные мемы"
        toolbar?.inflateMenu(R.menu.show_memes_menu)
    }

    override fun initialize(){
        refresher = activity?.findViewById<SwipeRefreshLayout>(R.id.refresherMemes)!!
        refresher?.setOnRefreshListener {
            run {
                    memesPresenter.loadMemes()

            }
        }
       listMemes = view?.findViewById(R.id.listMemes)!!
        listMemes.adapter=adapter

    }

   override fun hideRefreshBar(){
        refresher?.isRefreshing = false
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is Activity){
            var mActivity =context as Activity
            setToolbar()
            adapter = memesAdapter(mActivity)
        }

    }

    override fun showError() {
        println("ошибочка вышла")
    }





}