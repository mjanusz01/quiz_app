package com.example.quiz_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*
class NormalGameActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    var range = 10
    var maxTime = 1000000
    var actualTime = maxTime
    var goodAnswers = 0
    var wrongAnswers = 0
    lateinit var questionVar : Question
    lateinit var login: String

    override fun onCreate(savedInstanceState: Bundle?) {
        resetData()
        login = intent.getStringExtra("login").toString()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_game)
        findViewById<TextView>(R.id.normal_game_pointCounter).text = "0/0"
        questionVar = nextQuestion()
        timer = fixTimer()
        Log.v("login - ", login)
    }

    private fun resetData(){
        range = 10
        maxTime = 100000000
        actualTime = maxTime
        goodAnswers = 0
        wrongAnswers = 0
    }

    private fun startSummary(){
        val intent = Intent(this,SummaryActivity::class.java)
        intent.putExtra("good_answers", goodAnswers)
        intent.putExtra("wrong_answers", wrongAnswers)
        intent.putExtra("time", (actualTime))
        intent.putExtra("login", login)
        intent.putExtra("gametype","normal_game")

        val db = MyDatabase.getInstance(applicationContext)
        val stats = Stats(login,wrongAnswers,goodAnswers,"normal_game",actualTime,(goodAnswers*100000)/actualTime)
        db.statsDao().insert(stats)

        startActivity(intent)
        finish()
    }

    private fun fixTimer(): CountDownTimer {
        val timer = object: CountDownTimer(maxTime.toLong(), 10) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                findViewById<TextView>(R.id.timeCounter).text = ((maxTime-millisUntilFinished)/1000).toString() + "s"
                actualTime = maxTime-millisUntilFinished.toInt()
            }

            override fun onFinish() {
                cancel()
            }
        }
        timer.start()
        return timer
    }

    @SuppressLint("SetTextI18n")
    private fun nextQuestion() : Question{
        val newQuestion = getQuestion()
        val sum = goodAnswers + wrongAnswers
        findViewById<TextView>(R.id.normal_game_pointCounter).text = "$goodAnswers/$sum"
        findViewById<TextView>(R.id.normal_game_question).text = newQuestion.question
        findViewById<TextView>(R.id.normal_game_answer1).text = newQuestion.answerOne
        findViewById<TextView>(R.id.normal_game_answer2).text = newQuestion.answerTwo
        findViewById<TextView>(R.id.normal_game_answer3).text = newQuestion.answerThree
        findViewById<TextView>(R.id.normal_game_answer4).text = newQuestion.answerFour
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
        if(questionVar.goodAnswerIndex == index){
            goodAnswers++
        }
        else{
            wrongAnswers++
        }

        if(goodAnswers+ wrongAnswers == range){
            timer.cancel()
            startSummary()
        }
        else {
            questionVar = nextQuestion()
        }
    }
    fun backToMenu(view: View) {
        val myIntent = Intent(this,MainMenuActivity::class.java)
        myIntent.putExtra("login",login)
        startActivity(myIntent)
    }
}