package com.example.quiz_app

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Upsert
    suspend fun upsertContact(contact: Game)

    @Delete
    suspend fun deleteContact(contact: Game)

    @Query("SELECT * FROM game ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<Game>>

    @Query("SELECT * FROM game ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Game>>

    @Query("SELECT * FROM game ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Game>>
}