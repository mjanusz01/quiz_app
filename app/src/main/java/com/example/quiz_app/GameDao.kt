package com.example.quiz_app

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface GameDao {

    @Upsert
    suspend fun upsertContact(contact: Question)

    @Delete
    suspend fun deleteContact(contact: Question)

    @Query("SELECT count() FROM Stats where ID = :user_id")
    fun getStatsForUser(user_id: Int?): LiveData<Stats?>?

    @Transaction
    fun getStatsForUser(user: User): LiveData<Stats?>? {
        return getStatsForUser(user.id)
    }
}