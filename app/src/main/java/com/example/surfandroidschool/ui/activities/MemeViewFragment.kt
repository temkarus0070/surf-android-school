package com.example.surfandroidschool.ui.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.surfandroidschool.MemesFragment
import com.example.surfandroidschool.R
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.mvp.views.MemeView
import com.example.surfandroidschool.vm.MemesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatFragment
import java.text.SimpleDateFormat
import java.util.*

class MemeViewFragment:MvpAppCompatFragment(),MemeView {
    lateinit var memeData: MemeData
    lateinit var fragmentView:View
    lateinit var memeCaption:TextView
    lateinit var memeImage:ImageView
    lateinit var memeText:TextView
    lateinit var memeDate:TextView
    lateinit var memeLikeBtn:Button



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       fragmentView = inflater.inflate(R.layout.fragment_meme_view,container,false)

        return fragmentView
    }

    override fun onStart() {
        super.onStart()
        initialize()
        writeMeme()

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val bundle=arguments
        if(bundle!=null){
            memeData = bundle.getSerializable("meme") as MemeData
        }

        super.onCreate(savedInstanceState)
    }

    fun initialize(){

        var topToolbar: Toolbar? = activity?.findViewById(R.id.topToolbar)
        topToolbar?.menu?.clear()
        layoutInflater.inflate(R.layout.meme_view_toolbar_items,topToolbar)
        activity?.findViewById<Button>(R.id.exitBtn)?.setOnClickListener {
           kotlin.run {
                var transaction =fragmentManager?.beginTransaction()
               transaction?.replace(R.id.appFragment,MemesFragment())
               transaction?.commit()
            }
        }
        memeCaption = fragmentView?.findViewById(R.id.memeCaption)!!
        memeText = fragmentView?.findViewById(R.id.memeText)
        memeDate = fragmentView?.findViewById(R.id.memeDate)
        memeImage = fragmentView?.findViewById(R.id.memeImage)
        memeLikeBtn = fragmentView?.findViewById(R.id.memeLikeBtn)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)?.visibility=View.GONE
    }

    fun writeMeme(){
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        val date =  Date(memeData.createdDate.toLong()* 1000L)
        var todayDate = Date()
        val dif = todayDate.time - date.time
        val days = dif/86400000
        memeCaption?.text = memeData.title
        memeText.text =memeData.description
        Glide.with(
            fragmentView
        ).load(memeData.photoUrl)
            .into(memeImage)

        memeDate.text = "$days дня/дней назад"
        setOnclick()
    }

    fun setOnclick(){
        memeLikeBtn.setOnClickListener(
            {
                var memeViewModel: MemesViewModel = ViewModelProviders.of(activity as AppCompatActivity)
                    .get(MemesViewModel::class.java)
                memeData.isFavorite = true
                memeViewModel.insert(memeData)
            }
        )
    }
}