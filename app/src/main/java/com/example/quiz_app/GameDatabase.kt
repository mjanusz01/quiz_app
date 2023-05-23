package com.example.quiz_app

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Game::class],
    version = 1
)

abstract class GameDatabase: RoomDatabase() {
    abstract val dao: GameDao
}