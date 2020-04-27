package com.example.surfandroidschool.ui.activities

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.alpha
import androidx.core.view.MenuItemCompat.setActionView
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.views.CreateMemeView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatFragment

class MemeCreateFragment:MvpAppCompatFragment(),CreateMemeView {
    lateinit var fragmentView:View
    lateinit var editCaption:EditText
    lateinit var editText:EditText
    lateinit var topToolbar:Toolbar
    var captionLength:Int=0
    var textLength:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView= inflater.inflate(R.layout.fragment_create,container,false)
        setMenu()
        createListeners()
        checkInput()
        return fragmentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    fun createListeners(){
        editCaption= fragmentView.findViewById(R.id.captionMeme)!!
        editText= fragmentView.findViewById(R.id.textMeme)!!
        editCaption.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    captionLength=s.length

                }
                checkInput()
            }

        }
        )

        editText.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               if(s!=null) {
                   textLength = s.length
               }
                checkInput()
            }

        })
    }

    fun checkInput(){
        val item=topToolbar?.menu.getItem(1)
        if(captionLength !=0 && textLength!=0){
            topToolbar?.menu.getItem(1).setEnabled(true)
            val str:SpannableString = SpannableString("СОЗДАТЬ")

            str.setSpan(ForegroundColorSpan(resources.getColor(R.color.greenButton)),0,str.length,0)
            item.setTitle(str)
            item.setEnabled(true)
        }
        else
        {
            val str:SpannableString = SpannableString("СОЗДАТЬ")
            str.setSpan(ForegroundColorSpan(resources.getColor(R.color.disabledSaveColor)),0,str.length,0)
            item.setTitle(str)
            item.setEnabled(false)

        }
    }



    private fun setMenu(){
        topToolbar = activity?.findViewById<Toolbar>(R.id.topToolbar)!!
        if(topToolbar?.menu?.size()!=0)
            topToolbar?.menu?.clear()
        topToolbar?.setContentInsetsAbsolute(200,200)
        topToolbar?.inflateMenu(R.menu.meme_create_menu)
        topToolbar?.title = null
       // var params= Toolbar.LayoutParams(14,14)
        var params= androidx.appcompat.widget.ActionMenuView.LayoutParams(14,14)
        params.rightMargin=16
        var item =topToolbar?.menu?.getItem(0)
        var view =item?.actionView
   //     view?.layoutParams = params
      /*  var botNavMenuView:BottomNavigationItemView= topToolbar?.getChildAt(0) as BottomNavigationItemView
        for(item in botNavMenuView.children)
        {
            item.layoutParams=params
        }*/
   //     botNavMenuView?.layoutParams=params

     //   params =Toolbar.LayoutParams(69,48)
    //    params.rightMargin=16
    //    var vieha=topToolbar?.menu?.getItem(0) as BottomNavigationMenuView
     //   vieha.layoutParams=params

            //   botNavMenuView = topToolbar?.getChildAt(1)
        //botNavMenuView?.layoutParams=params
        topToolbar?.menu?.forEach {

            item: MenuItem ->
           run {
           //    item.actionView = view


            }
        }

    }


}