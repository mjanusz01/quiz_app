package com.example.quiz_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class GameDescriptionActivity : AppCompatActivity() {
    private lateinit var type: String
    lateinit var login: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_description)
        login = intent.getStringExtra("login").toString()
        type = intent.getStringExtra("type").toString()
        if(type == "normal_game"){
            findViewById<TextView>(R.id.game_description_description).text = "Gra polega na odpowiedzeniu na 10 pytań w jak najkrótszym czasie. Im więcej dobrych odpowiedzi tym więcej otrzymasz punktów. Zrób to w jak najkrótszym czasie, wtedy liczba punktów wzrośnie!"
        }
        else if(type == "time_game"){
            findViewById<TextView>(R.id.game_description_description).text = "Gra na czas - w czasie 30 sekund odpowiadasz na pytania. Im więcej odpowiedzi będzie dobrych, tym więcej punktów zdobędziesz. Powodzenia!"
        }
        val db2 = QuestionDBDatabase.getInstance(applicationContext)
    }

    fun game_desctiption_start_button(view: View) {
        if(type == "normal_game"){
            val intent = Intent(this,NormalGameActivity::class.java)
            intent.putExtra("login",login)
            startActivity(intent)
        }
        else if(type == "time_game"){
            val intent = Intent(this,TimeCountdownGameActivity::class.java)
            intent.putExtra("login",login)
            startActivity(intent)
        }
    }
}