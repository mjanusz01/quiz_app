package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Text

class SummaryActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        updateFields()
    }

    private fun updateFields(){
        val goodAnswers = intent.getIntExtra("good_answers", 0)
        val wrongAnswers = intent.getIntExtra("wrong_answers", 0)
        val time = intent.getIntExtra("time", 0)
        val points = (goodAnswers*100000)/time

        findViewById<TextView>(R.id.summary_good_answers).text = goodAnswers.toString()
        findViewById<TextView>(R.id.summary_number_of_questions).text = (goodAnswers + wrongAnswers).toString()
        findViewById<TextView>(R.id.summary_time).text = (time/1000).toString() + " s"
        findViewById<TextView>(R.id.summary_points).text = points.toString()
    }

    fun backToLogin(view: View) {
        val intent = Intent(this,MainMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}