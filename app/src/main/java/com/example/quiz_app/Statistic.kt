package com.example.quiz_app

import androidx.room.PrimaryKey

data class Statistic(
    val login: String,
    val badAnswerNum: Int,
    val goodAnswerNum : Int,
    val gametype: String,
    val time: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0){
}