package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class SummaryActivity : AppCompatActivity() {

    lateinit var login: String
    var goodAnswers: Int = 0
    var wrongAnswers: Int = 0
    var time: Int = 0
    lateinit var gametype: String
    var points: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        updateFields()
    }

    private fun updateFields(){
        goodAnswers = intent.getIntExtra("good_answers", 0)
        wrongAnswers = intent.getIntExtra("wrong_answers", 0)
        time = intent.getIntExtra("time", 0)
        gametype = intent.getStringExtra("gametype").toString()
        points = (goodAnswers*100000)/time
        login = intent.getStringExtra("login").toString()

        findViewById<TextView>(R.id.summary_good_answers).text = goodAnswers.toString()
        findViewById<TextView>(R.id.summary_number_of_questions).text = (goodAnswers + wrongAnswers).toString()
        findViewById<TextView>(R.id.summary_time).text = (time/1000).toString() + " s"
        findViewById<TextView>(R.id.summary_points).text = points.toString()
    }

    fun backToLogin(view: View) {
        val intent = Intent(this,MainMenuActivity::class.java)
        intent.putExtra("login",login)
        startActivity(intent)
        finish()
    }
    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
        Log.v("RESTORE","RESTORE")

        outState.run{
            putInt("good_answers", goodAnswers)
            putInt("wrong_answers",wrongAnswers)
            putInt("points",points)
            putInt("time",time)
            putString("gametype",gametype)
            putString("login",login)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        goodAnswers = savedInstanceState.get("good_answers") as Int
        wrongAnswers = savedInstanceState.get("wrong_answers") as Int
        points = savedInstanceState.get("points") as Int
        time = savedInstanceState.get("time") as Int
        gametype = savedInstanceState.get("gametype") as String
        login = savedInstanceState.get("login") as String

        findViewById<TextView>(R.id.summary_good_answers).text = goodAnswers.toString()
        findViewById<TextView>(R.id.summary_number_of_questions).text = (goodAnswers + wrongAnswers).toString()
        findViewById<TextView>(R.id.summary_time).text = (time/1000).toString() + " s"
        findViewById<TextView>(R.id.summary_points).text = points.toString()

    }
}