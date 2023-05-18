package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO: połączenie z bazą danych
    }

    fun newAccountClick(view: View) {
        val newAccountIntent = Intent(this,NewAccountActivity::class.java)

        newAccountResultLauncher.launch(newAccountIntent)
    }

    private var newAccountResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> val data = result.data

        resetFields()

        // handle data
    }

    private var mainMenuResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> val data = result.data

        resetFields()

        // handle data
    }

    fun login(view: View) {

        val login = findViewById<EditText>(R.id.activity_login_login).text
        val password = findViewById<EditText>(R.id.activity_login_password).text

        //TODO: obsługa danych (jeśli ok to zaloguj, jeśli nie to wywal błąd)

        val error = false

        if(error){
            findViewById<TextView>(R.id.activity_login_error).text = "BŁĘDNE DANE LOGOWANIA"
        }
        else{
            val newAccountIntent = Intent(this,MainMenuActivity::class.java)

            mainMenuResultLauncher.launch(newAccountIntent)
        }
    }

    private fun resetFields(){

        findViewById<EditText>(R.id.activity_login_login).setText("", TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_login_password).setText("", TextView.BufferType.EDITABLE)
        findViewById<TextView>(R.id.activity_login_error).text = ""
    }
}