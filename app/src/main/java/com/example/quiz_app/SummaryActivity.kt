package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class SummaryActivity : AppCompatActivity() {

    lateinit var login: String
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        updateFields()
        Log.v("login - ", login)
    }

    private fun updateFields(){
        val goodAnswers = intent.getIntExtra("good_answers", 0)
        val wrongAnswers = intent.getIntExtra("wrong_answers", 0)
        val time = intent.getIntExtra("time", 0)
        val gametype = intent.getStringExtra("gametype").toString()
        val points = (goodAnswers*100000)/time
        login = intent.getStringExtra("login").toString()

        findViewById<TextView>(R.id.summary_good_answers).text = goodAnswers.toString()
        findViewById<TextView>(R.id.summary_number_of_questions).text = (goodAnswers + wrongAnswers).toString()
        findViewById<TextView>(R.id.summary_time).text = (time/1000).toString() + " s"
        findViewById<TextView>(R.id.summary_points).text = points.toString()

        val db = MyDatabase.getInstance(applicationContext)
        val stats = Stats(login,wrongAnswers,goodAnswers,gametype,time,points)
        Log.v("C",db.statsDao().getCount().toString())
        db.statsDao().insert(stats)
        Log.v("C",db.statsDao().getCount().toString())
        Toast.makeText(applicationContext,db.statsDao().getCount().toString(),Toast.LENGTH_SHORT).show()
    }

    fun backToLogin(view: View) {
        val intent = Intent(this,MainMenuActivity::class.java)
        intent.putExtra("login",login)
        startActivity(intent)
        finish()
    }
}