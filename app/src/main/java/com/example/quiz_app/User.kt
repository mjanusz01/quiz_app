package com.example.quiz_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var login: String,
    var email: String,
    var passwordHash: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
