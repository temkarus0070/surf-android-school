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
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.alpha
import androidx.core.view.MenuItemCompat.setActionView
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import com.example.surfandroidschool.MemesFragment
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.views.CreateMemeView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
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


        activity?.findViewById<Button>(R.id.exitCreateBtn)?.setOnClickListener {
            kotlin.run{
                var transaction =fragmentManager?.beginTransaction()
                transaction?.replace(R.id.appFragment, MemesFragment())
                transaction?.commit()
            }
        }
        activity?.findViewById<Button>(R.id.addMeme)?.setOnClickListener {
            {
            }
        }

    }

    fun checkInput(){
        var item=topToolbar?.getChildAt(1).findViewById<Button>(R.id.addMeme)
        item = item as Button
        if(captionLength !=0 && textLength!=0){
            item.setEnabled(true)
            item.setTextColor(resources.getColor(R.color.greenButton))
        }
        else
        {
            item.setEnabled(false)
            item.setTextColor(resources.getColor(R.color.disabledSaveColor))

        }
    }



    private fun setMenu(){
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNav?.visibility = View.GONE
        topToolbar = activity?.findViewById<Toolbar>(R.id.topToolbar)!!
        topToolbar.menu.clear()
        layoutInflater.inflate(R.layout.menu_layout_meme_create,topToolbar)
        topToolbar?.title = null


    }


}