package com.example.quiz_app

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


//@Dao
//interface GameDao {
//
//    @Upsert
//    suspend fun upsertContact(contact: Question)
//
//    @Delete
//    suspend fun deleteContact(contact: Question)
//
//    @Query("SELECT count() FROM Stats where ID = :user_id")
//    fun getStatsForUser(user_id: Int?): LiveData<Stats?>?
//
//    @Transaction
//    fun getStatsForUser(user: User): LiveData<Stats?>? {
//        return getStatsForUser(user.id)
//    }
//}
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
}

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
