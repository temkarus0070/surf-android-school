package com.example.surfandroidschool.ui.activities

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.get
import androidx.core.view.marginLeft
import androidx.core.view.size
import com.example.surfandroidschool.MainActivity
import com.example.surfandroidschool.R
import com.example.surfandroidschool.auth.AuthInfo
import com.example.surfandroidschool.mvp.presenters.AuthPresenter
import com.example.surfandroidschool.mvp.views.AuthView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.view.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes
import java.text.Format
import studio.carbonylgroup.textfieldboxes.SimpleTextChangedWatcher as SimpleTextChangedWatcher1

class AuthActivity : MvpAppCompatActivity(), AuthView {
    @InjectPresenter
    internal lateinit var presenter: AuthPresenter
    private lateinit var textFieldPassword: TextFieldBoxes
    private lateinit var textFieldLogin: TextFieldBoxes
    private lateinit var loginInput: ExtendedEditText
    private lateinit var passwordInput: ExtendedEditText
    private lateinit var loginError: TextView
    private lateinit var passwordError: TextView
    private lateinit var authButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        checkAuth()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        textFieldLogin = findViewById(R.id.textfieldLogin)
        textFieldPassword = findViewById(R.id.textfieldPassword)
        setHideInput()
        setInputs()
        setChangeEvents()
    }


    private fun setInputs() {
        loginInput = findViewById(R.id.editLogin)
        passwordInput = findViewById(R.id.editPassword)
        loginError = findViewById(R.id.loginError)
        passwordError = findViewById(R.id.passwordError)
        authButton = findViewById(R.id.authBtn)
        progressBar = findViewById(R.id.progress_circular)
        buttonFrameLayout = findViewById(R.id.lowFrame)
    }

    private fun setHideInput() {
        textFieldPassword.endIconImageButton.setOnClickListener {
            run {
                var editor: ExtendedEditText = findViewById(R.id.editPassword)
                if (editor.inputType == InputType.TYPE_CLASS_NUMBER or
                    InputType.TYPE_NUMBER_VARIATION_PASSWORD
                ) {
                    editor.inputType = InputType.TYPE_CLASS_NUMBER or
                            InputType.TYPE_NUMBER_VARIATION_NORMAL
                    textFieldPassword.setEndIcon(R.drawable.picture_password)
                    editor.transformationMethod = null
                } else {
                    editor.inputType = InputType.TYPE_CLASS_NUMBER or
                            InputType.TYPE_NUMBER_VARIATION_PASSWORD
                    textFieldPassword.setEndIcon(R.drawable.ic_hideassword)
                    editor.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }

        }
    }

    private fun setChangeEvents() {
        textFieldPassword.setSimpleTextChangeWatcher { theNewText, isError ->
            run {
                val len = applicationContext.resources.getInteger(R.integer.passwordLength)
                if (passwordInput.text.length < len) {
                    passwordError.visibility = View.VISIBLE
                    val errorMessage = "Пароль должен содержать ${len} символа"
                    passwordError.setTextColor(Color.WHITE)
                    passwordError.text = errorMessage
                } else {
                    passwordError.visibility = View.GONE
                }
            }
        }


    }


    fun authClick(view: View) {
        var empty = false
        if (loginInput.text.isEmpty()) {
            loginError.visibility = View.VISIBLE
            loginError.text = "Поле не может быть пустым"
            loginError.setTextColor(applicationContext.resources.getColor(R.color.errorColor))
            empty = true
        }
        if (passwordInput.text.isEmpty()) {
            passwordError.visibility = View.VISIBLE
            passwordError.text = "Поле не может быть пустым"
            passwordError.setTextColor(applicationContext.resources.getColor(R.color.errorColor))
            empty = true
        }
        rippleButton()
        if (!empty) {
            authButton.text = ""
            progressBar.visibility = View.VISIBLE
            presenter.auth(
                loginInput.text.toString(), passwordInput.text.toString(), getSharedPreferences(
                    "auth", Context.MODE_PRIVATE
                )
            )
        }

    }

    override fun rippleButton() {
        val density = applicationContext.resources.displayMetrics.density
        val width = (328 * density).toInt()
        val height = (48 * density).toInt()
        val leftMargin = (21 * density).toInt()
        val rightMargin = (11 * density).toInt()
        val bottomMargin = (24 * density).toInt()
        var params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(width, height)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.leftMargin = leftMargin
        params.rightMargin = rightMargin
        params.bottomMargin = bottomMargin
        buttonFrameLayout.layoutParams = params
    }

    //незнаю надо ,или ненадо убирать riple, на всяких случай сделал
    override fun endRippleButton() {
        var density = applicationContext.resources.displayMetrics.density
        val width = (328 * density).toInt()
        val height = (48 * density).toInt()
        val bottomMargin = (24 * density).toInt()
        var params = RelativeLayout.LayoutParams(width, height)
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.bottomMargin = bottomMargin
        buttonFrameLayout.layoutParams = params
    }

    override fun endAuth(authInfo: AuthInfo) {
        progressBar.visibility = View.INVISIBLE
        authButton.text = resources.getString(R.string.enter)
    }

    override fun checkAuth() {
       if( getSharedPreferences("auth",Context.MODE_PRIVATE).contains("accessToken"))
           nextScreen()
    }

    override fun nextScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun viewError() {
        Snackbar.make(
            findViewById(R.id.lowFrame), "Вы ввели не верные данные.\n" +
                    "Попробуйте еще раз ", 3000
        ).setBackgroundTint(
            resources.getColor(
                R.color.snackBarBackColor
            )
        ).show()
    }


}