package com.example.quiz_app

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

class QuestionDBDao {

    @Dao
    interface QuestionDao {
        @Insert
        fun insert(question: Question)

        @Update
        fun update(question: Question)

        @Delete
        fun delete(question: Question)

        @Query("SELECT * FROM question")
        fun getAllquestionData(): List<Question>
    }

    @Dao
    interface StatsDao {
        @Insert
        fun insert(stats: Stats)

        @Update
        fun update(stats: Stats)

        @Delete
        fun delete(stats: Stats)

        @Query("SELECT COUNT(*) FROM Stats;")
        fun getCount(): Int

    }

    @Dao
    interface UserDao {
        @Insert
        fun insert(user: User)

        @Update
        fun update(user: User)

        @Delete
        fun delete(user: User)

        @Query("SELECT COUNT(*) FROM User WHERE login = :login;")
        fun getCountForLogin(login: String): Int

        @Query("SELECT COUNT(*) FROM User WHERE email = :email;")
        fun getCountForEmail(email: String): Int

        @Query("SELECT COUNT(*) FROM User")
        fun getAllUsersCount(): Int

        @Query("SELECT * FROM User WHERE login = :login AND passwordHash = :password;")
        fun getUserByLoginAndPassword(login: String, password: String): User

    }
}