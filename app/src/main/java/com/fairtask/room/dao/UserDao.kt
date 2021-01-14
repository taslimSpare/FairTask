package com.fairtask.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fairtask.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM User ORDER BY firstName ASC")
    fun getProfiles(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(user: User)

    @Query("DELETE FROM User WHERE id = :id")
    suspend fun deleteUser(id: String)

}
