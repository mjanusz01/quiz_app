package com.example.quiz_app

data class Question(
    val question : String,
    val answerOne: String,
    val answerTwo: String,
    val answerThree: String,
    val answerFour: String,
    val goodAnswerIndex : Int
){

}