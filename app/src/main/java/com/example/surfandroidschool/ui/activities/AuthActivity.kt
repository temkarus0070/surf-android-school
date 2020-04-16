package com.example.surfandroidschool.ui.activities

import android.content.Context
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.example.surfandroidschool.R
import com.example.surfandroidschool.auth.AuthInfo
import com.example.surfandroidschool.mvp.presenters.AuthPresenter
import com.example.surfandroidschool.mvp.views.AuthView
import kotlinx.android.synthetic.main.activity_auth.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes
import java.text.Format
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher as SimpleTextChangedWatcher1

class AuthActivity:MvpAppCompatActivity(),AuthView {
    @InjectPresenter
    internal lateinit var presenter:AuthPresenter
    private lateinit var textFieldPassword:TextFieldBoxes
    private lateinit var textFieldLogin:TextFieldBoxes
    private  lateinit var loginInput:ExtendedEditText
    private  lateinit var passwordInput: ExtendedEditText
    private  lateinit var loginError:TextView
    private  lateinit var passwordError:TextView
    private lateinit var authButton:Button
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        textFieldLogin=findViewById(R.id.textfieldLogin)
        textFieldPassword=findViewById(R.id.textfieldPassword)
        setHideInput()
        setInputs()
        setChangeEvents()
    }


    private fun setInputs(){
        loginInput = findViewById(R.id.editLogin)
        passwordInput = findViewById<ExtendedEditText>(R.id.editPassword)
        loginError =  findViewById(R.id.loginError)
        passwordError = findViewById(R.id.passwordError)
        authButton = findViewById(R.id.authBtn)
        progressBar = findViewById(R.id.progress_circular)
    }

    private fun setHideInput(){
        textFieldPassword.endIconImageButton.setOnClickListener {
            run {
                var editor: ExtendedEditText = findViewById<ExtendedEditText>(R.id.editPassword)
                if(editor.inputType ==  InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                {
                    editor.inputType=InputType.TYPE_CLASS_NUMBER or
                            InputType.TYPE_NUMBER_VARIATION_NORMAL
                    textFieldPassword.setEndIcon(R.drawable.picture_password)
                    editor.transformationMethod=null
                }
                else {
                    editor.inputType =
                        InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
                    textFieldPassword.setEndIcon(R.drawable.ic_hideassword)
                    editor.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }

        }
    }

   private fun setChangeEvents(){
        textFieldLogin.setSimpleTextChangeWatcher { theNewText, isError -> {

        } }
        textFieldPassword.setSimpleTextChangeWatcher { theNewText, isError ->
            run {
                var len = applicationContext.resources.getInteger(R.integer.passwordLength)
                if (passwordInput.text.length < len) {
                    passwordError.visibility = View.VISIBLE
                    var errorMessage:String= "Пароль должен содержать ${len} символа"
                    passwordError.setTextColor(Color.WHITE)
                    passwordError.text = errorMessage


                }
                else {
                    passwordError.visibility = View.GONE
                }
            }
        }


    }


    fun authClick(view: View) {
        var empty:Boolean = false
        if(loginInput.text.isEmpty()){
            loginError.visibility=View.VISIBLE
            loginError.text="Поле не может быть пустым"
            loginError.setTextColor(applicationContext.resources.getColor(R.color.errorColor))
            empty=true
        }
        if(passwordInput.text.isEmpty()) {
            passwordError.visibility=View.VISIBLE
            passwordError.text="Поле не может быть пустым"
            passwordError.setTextColor(applicationContext.resources.getColor(R.color.errorColor))
            empty = true
        }
        if(!empty) {
            authButton.text=""
            progressBar.visibility = View.VISIBLE
            presenter.auth(loginInput.text.toString(), passwordInput.text.toString())
        }
    }

    override fun endAuth(authInfo:AuthInfo) {
        progressBar.visibility = View.INVISIBLE
        authButton.text =  resources.getString(R.string.enter)
        println(authInfo?.userInfo?.userDescription)
    }

    fun progressButton(){

    }


}