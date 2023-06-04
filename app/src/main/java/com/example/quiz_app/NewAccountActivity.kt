package com.example.quiz_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.properties.Delegates

class NewAccountActivity : AppCompatActivity() {

    lateinit var email: String
    lateinit var login: String
    lateinit var password: String
    lateinit var passwordAgain: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_account)
    }

    fun backToLogin(view: View) {
        finishActivity()
    }

    fun register(view: View) {

        val db = MyDatabase.getInstance(applicationContext)

        email = findViewById<EditText>(R.id.activity_new_account_email).text.toString()
        login = findViewById<EditText>(R.id.activity_new_account_login).text.toString()
        password = findViewById<EditText>(R.id.activity_new_account_password).text.toString()
        passwordAgain = findViewById<EditText>(R.id.activity_new_account_passwordAgain).text.toString()

        var error = false
        var errorMessage = ""
        val userByLogin = db.userDao().getCountForLogin(login)
        val userByEmail = db.userDao().getCountForEmail(email)

        if(password != passwordAgain){
            error = true
            errorMessage = "Podane hasła nie są identyczne."
        }

        if(userByLogin != 0 || userByEmail != 0){
            error = true
            errorMessage = "Użytkownik o podanym loginie lub emailu już istnieje."
        }

        if(email == "" || login == "" || password == "" || passwordAgain == ""){
            error = true
            errorMessage = "Uzupełnij wszystkie pola."
        }

        if(error){
            findViewById<TextView>(R.id.activity_new_account_error).text = errorMessage
            resetFields()
        }
        else{
            db.userDao().insert(User(login,email,password))
            finishActivity()
        }
    }

    private fun finishActivity(){
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED, intent)
        finish()
    }

    private fun resetFields(){
        findViewById<EditText>(R.id.activity_new_account_email).setText("", TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_login).setText("", TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_password).setText("", TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_passwordAgain).setText("", TextView.BufferType.EDITABLE)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        email = findViewById<EditText>(R.id.activity_new_account_email).text.toString()
        login = findViewById<EditText>(R.id.activity_new_account_login).text.toString()
        password = findViewById<EditText>(R.id.activity_new_account_password).text.toString()
        passwordAgain = findViewById<EditText>(R.id.activity_new_account_passwordAgain).text.toString()
        super.onSaveInstanceState(outState)

        outState.run{
            putString("email",email)
            putString("login", loginText)
            putString("password",password)
            putString("password_again",passwordAgain)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        email = savedInstanceState.get("email") as String
        login = savedInstanceState.get("login") as String
        password = savedInstanceState.get("password") as String
        passwordAgain = savedInstanceState.get("password_again") as String

        findViewById<EditText>(R.id.activity_new_account_email).setText(email, TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_login).setText(login, TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_password).setText(password, TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_new_account_passwordAgain).setText(passwordAgain, TextView.BufferType.EDITABLE)
    }
}