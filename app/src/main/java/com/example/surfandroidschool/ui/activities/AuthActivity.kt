package com.example.surfandroidschool.ui.activities

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.surfandroidschool.R
import com.example.surfandroidschool.mvp.presenters.AuthPresenter
import com.example.surfandroidschool.mvp.views.AuthView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class AuthActivity:MvpAppCompatActivity(),AuthView {
    @InjectPresenter
    internal lateinit var presenter:AuthPresenter
    private lateinit var textFieldPassword:TextFieldBoxes
    private lateinit var textFieldLogin:TextFieldBoxes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        textFieldLogin=findViewById(R.id.textfieldLogin)
        textFieldPassword=findViewById(R.id.textfieldPassword)
        setHideInput()
    }


    private fun setHideInput(){
        textFieldPassword.endIconImageButton.setOnClickListener {
            run {
                var editor: ExtendedEditText = findViewById<ExtendedEditText>(R.id.editPassword)
                if(editor.inputType ==  InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_VARIATION_PASSWORD)
                {
                    editor.setTextColor(
                        Color.RED)
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


    fun authClick(view: View) {
        var loginError:TextView = findViewById(R.id.loginError)
        var passwordError:TextView = findViewById(R.id.passwordError)
        var loginInput:ExtendedEditText = findViewById(R.id.editLogin)
        var passwordInput: ExtendedEditText = findViewById<ExtendedEditText>(R.id.editPassword)
        var empty:Boolean = false
        if(loginInput.text.isEmpty()){
            loginError.visibility=View.VISIBLE
            loginError.text="Поле не может быть пустым"
            empty=true
        }
        if(passwordInput.text.isEmpty()) {
            passwordError.visibility=View.VISIBLE
            passwordError.text="Поле не может быть пустым"
            empty = true
        }
        if(!empty) {

        }
    }


}