package com.example.quiz_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val email = findViewById<EditText>(R.id.activity_new_account_email).text
        val login = findViewById<EditText>(R.id.activity_new_account_login).text
        val password = findViewById<EditText>(R.id.activity_new_account_password).text
        val passwordAgain = findViewById<EditText>(R.id.activity_new_account_passwordAgain).text

        //TODO: obsługa danych (jeśli ok to zaloguj, jeśli nie to wywal błąd)
        val error = false

        if(error){
            findViewById<TextView>(R.id.activity_new_account_error).text = "BŁĘDNE DANE LOGOWANIA"
            resetFields()
        }
        else{
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