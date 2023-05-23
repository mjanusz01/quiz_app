package com.example.quiz_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Stats(
    val badAnwserNum: Int,
    val goodAnswerNum : Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0
)