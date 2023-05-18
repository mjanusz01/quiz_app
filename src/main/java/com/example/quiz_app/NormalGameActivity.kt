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
import kotlin.math.max

var range = 10
var maxTime = 30000
var actualTime = maxTime
var goodAnswers = 0
var finished = false
var wrongAnswers = 0
lateinit var question : Question

class NormalGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("NEW","CREATE")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_game)
        question = nextQuestion()
        fixTimer()
        resetData()
    }

    fun resetData(){
        range = 10
        maxTime = 30000
        actualTime = maxTime
        goodAnswers = 0
        wrongAnswers = 0
        finished = false
    }

    fun startSummary(){
        val intent = Intent(this,SummaryActivity::class.java)
        intent.putExtra("good_answers", goodAnswers)
        intent.putExtra("wrong_answers", wrongAnswers)
        intent.putExtra("time", (maxTime - actualTime))
        startActivity(intent)
        finish()
    }

    private fun fixTimer(){
        val timer = object: CountDownTimer(30000, 10) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                findViewById<TextView>(R.id.timeCounter).text = (millisUntilFinished/1000).toString() + "s"
                actualTime = millisUntilFinished.toInt()
            }

            override fun onFinish() {
                if(!finished) {
                    Log.v("FINISHED", "FINISHED")
                    startSummary()
                }
            }
        }
        timer.start()
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
        val questions = testQuestions()
        val randomIndex = random.nextInt(questions.size)
        return questions[randomIndex]
    }

    private fun testQuestions() : ArrayList<Question>{
        var questions = ArrayList<Question>()

        questions.add(Question("Ile to jest 2 + 2", "1", "2", "3", "4",1))
        questions.add(Question("Podaj liczbę 7", "1", "2", "7", "4",2))
        questions.add(Question("W Polsce mówi się po:", "polsku", "angielsku", "szwedzku", "bułgarsku",0))
        questions.add(Question("10 - 2 = ?", "cztery", "siedem", "dwa", "osiem",3))
        questions.add(Question(
            "Bardzo długie pytanie. Dlaczego tak jest a nie inaczej, że w Polsce taka bieda i wgl? Dlaczego nie stać mnie na rzeczy które bym chciał kupić?" ,
            "Nie wiadomo dlaczego, bieda jest panie",
            "Wiadomo, rząd jest zły",
            "Odpowiedź długa. To nie jest tak, że jest dobrze lub niedobrze.",
        "Odpowiedź dla niezdecydowanych",
            3
        ))
        return questions
    }

    fun answer1(view: View) {
        checkAnswerForIndex(0)
    }
    fun answer2(view: View) {
        checkAnswerForIndex(1)
    }
    fun answer3(view: View) {
        checkAnswerForIndex(2)
    }
    fun answer4(view: View) {
        checkAnswerForIndex(3)
    }

    fun checkAnswerForIndex(index: Int){
        if(question.goodAnswerIndex == index){
            goodAnswers++
        }
        else{
            wrongAnswers++
        }

        if(goodAnswers+ wrongAnswers == range){
            finished = true
            startSummary()
        }
        else {
            question = nextQuestion()
        }
    }
}