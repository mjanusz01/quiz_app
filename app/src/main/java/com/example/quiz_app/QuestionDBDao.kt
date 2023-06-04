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

}