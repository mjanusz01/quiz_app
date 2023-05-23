package com.example.quiz_app

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Upsert
    suspend fun upsertContact(contact: Question)

    @Delete
    suspend fun deleteContact(contact: Question)

    @Query("SELECT * FROM question ORDER BY firstName ASC")
    fun getContactsOrderedByFirstName(): Flow<List<Question>>

    @Query("SELECT * FROM question ORDER BY lastName ASC")
    fun getContactsOrderedByLastName(): Flow<List<Question>>

    @Query("SELECT * FROM question ORDER BY phoneNumber ASC")
    fun getContactsOrderedByPhoneNumber(): Flow<List<Question>>
}