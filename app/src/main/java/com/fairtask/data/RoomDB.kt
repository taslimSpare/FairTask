package com.fairtask.data

import androidx.annotation.WorkerThread
import com.fairtask.models.User
import com.fairtask.room.dao.UserDao
import kotlinx.coroutines.flow.Flow

/*
This class stores all the local database (Room) operations.
As represented here, this class focuses on creating, fetching, updating and deleting a user's profile
 */
class RoomDB(
    private val userDao: UserDao) {



    /* This returns a LiveData of the current account(s) stored in the DB.
        I chose to use a LiveData because of the advantage of always getting real-time data
     */
    val getUsers: Flow<List<User>> = userDao.getProfiles()



    // this function saves an a user's account to local storage (Room)
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.saveProfile(user)
    }



    // this function deletes a user's profile from the local storage
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteProfile(id: String) {
        userDao.deleteUser(id)
    }


}