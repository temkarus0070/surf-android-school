package com.example.surfandroidschool.ui.activities

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.alpha
import androidx.core.view.MenuItemCompat.setActionView
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.example.surfandroidschool.MainActivity
import com.example.surfandroidschool.MemesFragment
import com.example.surfandroidschool.R
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.mvp.presenters.CreatePresenter
import com.example.surfandroidschool.mvp.views.CreateMemeView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import java.util.*

class MemeCreateFragment : MvpAppCompatFragment(), CreateMemeView {
    @InjectPresenter
    lateinit var presenter: CreatePresenter
    private val url =
        "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTfPiMMl5kXYin0ZwAQ8BSzXEFCdLaWTWDiu-KQSCNeYbWKS1rM&usqp=CAU"
    public lateinit var fragmentView: View
    lateinit var editCaption: EditText
    lateinit var editText: EditText
    lateinit var topToolbar: Toolbar
    var captionLength: Int = 0
    var textLength: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_create, container, false)
        setMenu()
        createListeners()
        checkInput()
        var imageView = fragmentView?.findViewById<ImageView>(R.id.memeImage)
        Glide.with(fragmentView)
            .load(url)
            .into(imageView)
        return fragmentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


    }


    fun createListeners() {
        editCaption = fragmentView.findViewById(R.id.captionMeme)!!
        editText = fragmentView.findViewById(R.id.textMeme)!!
        editCaption.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    captionLength = s.length

                }
                checkInput()
            }

        }

        )

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    textLength = s.length
                }
                checkInput()
            }

        })


        activity?.findViewById<Button>(R.id.exitCreateBtn)?.setOnClickListener {
            kotlin.run {
                activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)?.selectedItemId =
                    R.id.showMemes
                var transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.appFragment, MemesFragment())
                transaction?.commit()
            }
        }
        activity?.findViewById<Button>(R.id.addMeme)?.setOnClickListener {
            kotlin.run {
                val caption = editCaption.text.toString()
                val text = editText.text.toString()
                var meme = MemeData(caption, url, false, text, Date().time.toString(), "1")

                presenter.createMem(meme, context, this)
            }
        }

    }

    fun checkInput() {
        var item = topToolbar?.getChildAt(1).findViewById<Button>(R.id.addMeme)
        if (item != null) {
            item = item as Button
            if (captionLength != 0 && textLength != 0) {
                item.setEnabled(true)
                item.setTextColor(resources.getColor(R.color.greenButton))
            } else {
                item.setEnabled(false)
                item.setTextColor(resources.getColor(R.color.disabledSaveColor))

            }
        }
    }


    private fun setMenu() {
        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNav?.visibility = View.GONE
        topToolbar = activity?.findViewById<Toolbar>(R.id.topToolbar)!!
        topToolbar.menu.clear()
        layoutInflater.inflate(R.layout.menu_layout_meme_create, topToolbar)
        topToolbar?.title = null
        topToolbar?.setBackgroundColor(resources.getColor(R.color.memeBackColor))


    }

    override fun goOut() {

    }


}