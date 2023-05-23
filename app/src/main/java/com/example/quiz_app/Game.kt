package com.example.quiz_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)