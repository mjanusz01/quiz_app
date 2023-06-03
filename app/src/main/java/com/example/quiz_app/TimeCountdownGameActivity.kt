package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*

class TimeCountdownGameActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    var rangeTime = 10
    var maxTimeTime = 30000
    var actualTimeTime = maxTimeTime
    var goodAnswersTime = 0
    var wrongAnswersTime = 0
    lateinit var questionTime : Question
    lateinit var login: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        resetData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_game)
        findViewById<TextView>(R.id.countdown_game_pointCounter).text = "0/0"
        login = intent.getStringExtra("login").toString()
        questionTime = nextQuestion()
        timer = fixTimer()
    }

    private fun resetData(){
        rangeTime = 10
        maxTimeTime = 30000
        actualTimeTime = maxTimeTime
        goodAnswersTime = 0
        wrongAnswersTime = 0
    }

    private fun startSummary(){
        val intent = Intent(this, SummaryActivity::class.java)
        intent.putExtra("good_answers", goodAnswersTime)
        intent.putExtra("wrong_answers", wrongAnswersTime)
        intent.putExtra("time", (maxTimeTime - actualTimeTime))
        intent.putExtra("login",login)
        intent.putExtra("gametype","time_game")
        startActivity(intent)
        finish()
    }

    private fun fixTimer(): CountDownTimer {
        val timer = object: CountDownTimer(30000, 10) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                findViewById<TextView>(R.id.countdown_game_timeCounter).text = (millisUntilFinished/1000).toString() + "s"
                actualTimeTime = millisUntilFinished.toInt()
            }
            override fun onFinish() {
                cancel()
                startSummary()
            }
        }
        timer.start()
        return timer
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() : Question {
        val newQuestion = getQuestion()
        val sum = goodAnswersTime + wrongAnswersTime
        findViewById<TextView>(R.id.countdown_game_pointCounter).text = "$goodAnswersTime/$sum"
        findViewById<TextView>(R.id.countdown_game_question).text = newQuestion.question
        findViewById<TextView>(R.id.countdown_game_answer1).text = newQuestion.answerOne
        findViewById<TextView>(R.id.countdown_game_answer2).text = newQuestion.answerTwo
        findViewById<TextView>(R.id.countdown_game_answer3).text = newQuestion.answerThree
        findViewById<TextView>(R.id.countdown_game_answer4).text = newQuestion.answerFour
        return newQuestion
    }

    private fun getQuestion(): Question {
        val random = Random()
        val questions = getQuestionsFromDatabase()
        val randomIndex = random.nextInt(questions.size)
        return questions[randomIndex]
    }

    private fun getQuestionsFromDatabase(): List<Question> {
        val db = QuestionDBDatabase.getInstance(applicationContext)
        return db.questionDao().getAllquestionData()
    }

    fun answer1(view: View) {
        checkAnswerForIndex(1)
    }
    fun answer2(view: View) {
        checkAnswerForIndex(2)
    }
    fun answer3(view: View) {
        checkAnswerForIndex(3)
    }
    fun answer4(view: View) {
        checkAnswerForIndex(4)
    }

    private fun checkAnswerForIndex(index: Int){
        if(questionTime.goodAnswerIndex == index){
            goodAnswersTime++
        }
        else{
            wrongAnswersTime++
        }
        questionTime = nextQuestion()
    }

    fun backToMenu(view: View) {
        val myIntent = Intent(this,MainMenuActivity::class.java)
        myIntent.putExtra("login",login)
        startActivity(myIntent)
    }
}