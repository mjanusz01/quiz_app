package com.example.quiz_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Database(entities = [Question::class, Stats::class, User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun statsDao(): StatsDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "GameAppDatabase.db"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                    //createFromAsset("databases/asset.db")//

                INSTANCE = instance
                instance
            }
        }
    }
}