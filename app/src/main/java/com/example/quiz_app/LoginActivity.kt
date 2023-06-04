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
import androidx.room.Room
import org.w3c.dom.Text
import java.io.File

val TOKEN = "TOKEN"
lateinit var loginText: String
lateinit var password: String

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun newAccountClick(view: View) {
        val newAccountIntent = Intent(this,NewAccountActivity::class.java)

        newAccountResultLauncher.launch(newAccountIntent)
    }

    private var newAccountResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> val data = result.data

        resetFields()
    }

    private var mainMenuResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result -> val data = result.data

        resetFields()
    }

    fun login(view: View) {
        val db = MyDatabase.getInstance(applicationContext)
        loginText = findViewById<EditText>(R.id.activity_login_login).text.toString()
        password = findViewById<EditText>(R.id.activity_login_password).text.toString()

        val user = db.userDao().getUserByLoginAndPassword(loginText,password)

        if(user == null){
            resetFields()
            findViewById<TextView>(R.id.activity_login_error).text = "Błędne dane logowania."
        }
        else{
            val newAccountIntent = Intent(this,MainMenuActivity::class.java)
            newAccountIntent.putExtra("login",loginText)
            mainMenuResultLauncher.launch(newAccountIntent)
        }
    }

    private fun resetFields(){
        findViewById<EditText>(R.id.activity_login_login).setText("", TextView.BufferType.EDITABLE)
        findViewById<EditText>(R.id.activity_login_password).setText("", TextView.BufferType.EDITABLE)
        findViewById<TextView>(R.id.activity_login_error).text = ""
    }
    override fun onSaveInstanceState(outState: Bundle) {

        loginText = findViewById<EditText>(R.id.activity_login_login).text.toString()
        password = findViewById<EditText>(R.id.activity_login_password).text.toString()

        super.onSaveInstanceState(outState)

        outState.run{
            putString("login", loginText)
            putString("password",password)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        loginText = savedInstanceState.get("login") as String
        password = savedInstanceState.get("password") as String

    }
}