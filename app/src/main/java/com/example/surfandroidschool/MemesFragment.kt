package com.example.surfandroidschool

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.memes.memesAdapter
import com.example.surfandroidschool.mvp.presenters.MemesPresenter
import com.example.surfandroidschool.mvp.views.MemesView
import com.example.surfandroidschool.ui.activities.MemeViewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

public class MemesFragment : MvpAppCompatFragment(), MemesView {
    @InjectPresenter
    lateinit var memesPresenter: MemesPresenter
    lateinit var refresher: SwipeRefreshLayout
    lateinit var adapter: memesAdapter
    lateinit var listMemes: RecyclerView
    lateinit var errorTop: TextView
    lateinit var errorBottom: TextView
    lateinit var progressBar: ProgressBar
    lateinit var thisView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thisView = inflater.inflate(R.layout.fragment_memes, container, false)
        return thisView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize()
    }

    override fun showMemes(memesList: List<MemeData>) {
        adapter.setData(memesList)
        refresher.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        errorTop?.visibility = View.GONE
        errorBottom?.visibility = View.GONE
    }


    override fun setToolbar() {

        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.topToolbar)
        val memeView = activity?.findViewById<RelativeLayout>(R.id.memeViewMenu)
        val createView = activity?.findViewById<RelativeLayout>(R.id.memeCreateMenu)

        toolbar?.setBackgroundColor(resources.getColor(R.color.memeBackColor))
        if (view != null)
            toolbar?.removeView(view)
        if (createView != null)
            toolbar?.removeView(createView)
        if (toolbar?.menu?.size() != 0)
            toolbar?.menu?.clear()
        toolbar?.title = "Популярные мемы"
        toolbar?.inflateMenu(R.menu.show_memes_menu)
    }

    override fun initialize() {
        errorTop = thisView.findViewById<TextView>(R.id.errorTop)!!
        errorBottom = thisView.findViewById<TextView>(R.id.errorBottom)!!
        progressBar = thisView.findViewById<ProgressBar>(R.id.progressMemesLoad)!!

        refresher = activity?.findViewById<SwipeRefreshLayout>(R.id.refresherMemes)!!
        refresher?.setOnRefreshListener {
            run {
                memesPresenter.loadMemes()

            }
        }
        listMemes = view?.findViewById(R.id.listMemes)!!
        listMemes.adapter = adapter
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)?.visibility = View.VISIBLE
        setToolbar()
    }

    override fun hideRefreshBar() {
        refresher?.isRefreshing = false
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            var mActivity = context as Activity
            adapter = memesAdapter(mActivity, ::itemClicker)
        }

    }

    fun itemClicker(data: MemeData, position: Int): Unit {
        val ft = fragmentManager?.beginTransaction()
        val fragment = MemeViewFragment()
        val bundle = Bundle()
        bundle.putSerializable("meme", data)
        fragment.arguments = bundle
        ft?.replace(R.id.appFragment, fragment, "memeView")
        ft?.commit()

    }

    override fun showError() {
        progressBar.visibility = View.GONE
        errorTop?.visibility = View.VISIBLE
        errorBottom?.visibility = View.VISIBLE
    }


}