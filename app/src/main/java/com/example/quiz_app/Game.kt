package com.example.quiz_app

import androidx.room.Entity
import androidx.room.PrimaryKey

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


@Entity(tableName = "Stats")
data class Stats(
    val badAnwserNum: Int,
    val goodAnswerNum : Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)

@Entity(tableName = "User")
data class User(
    var login: String,
    var email: String,
    var passwordHash: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)