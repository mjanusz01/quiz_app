package com.example.quiz_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class NewAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_account)
    }

    fun backToLogin(view: View) {
        finishActivity()
    }

    fun register(view: View) {

        val db = MyDatabase.getInstance(applicationContext)

        val email = findViewById<EditText>(R.id.activity_new_account_email).text.toString()
        val login = findViewById<EditText>(R.id.activity_new_account_login).text.toString()
        val password = findViewById<EditText>(R.id.activity_new_account_password).text.toString()
        val passwordAgain = findViewById<EditText>(R.id.activity_new_account_passwordAgain).text.toString()

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
}