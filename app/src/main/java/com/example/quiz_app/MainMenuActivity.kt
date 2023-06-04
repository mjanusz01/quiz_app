package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainMenuActivity : AppCompatActivity() {
    lateinit var login: String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        login  = intent.getStringExtra("login").toString()
        findViewById<TextView>(R.id.activity_main_menu_text).text = "Witaj, " + login.toString() + "!"
        Log.v("login - ", login)
    }

    fun normalGameButton(view: View) {
        val intent = Intent(this,GameDescriptionActivity::class.java)
        intent.putExtra("type","normal_game")
        intent.putExtra("login", login)
        startActivity(intent)
    }

    fun timeGameButton(view: View) {
        val intent = Intent(this,GameDescriptionActivity::class.java)
        intent.putExtra("type","time_game")
        intent.putExtra("login", login)
        startActivity(intent)
    }

    fun statsButton(view: View) {
        val intent = Intent(this,StatsActivity::class.java)
        startActivity(intent)
    }
    fun backToLogin(view: View) {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}