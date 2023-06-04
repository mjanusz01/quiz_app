package com.example.quiz_app

import androidx.room.Entity
import androidx.room.PrimaryKey

class QuestionDB {

    @Entity
    data class Question(
        val question : String,
        val answerOne: String,
        val answerTwo: String,
        val answerThree: String,
        val answerFour: String,
        val goodAnswerIndex : Int,
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0
    )

}