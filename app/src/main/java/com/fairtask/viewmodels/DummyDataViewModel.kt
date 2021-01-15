package com.fairtask.viewmodels


import androidx.lifecycle.*
import com.fairtask.data.ApiService
import com.fairtask.data.RoomDB
import com.fairtask.models.Resource
import com.fairtask.models.User
import kotlinx.coroutines.launch
import java.lang.Exception

class DummyDataViewModel(
    private val api: ApiService,
    private val roomDB: RoomDB
) : ViewModel() {

    private val remoteUsersLiveData = MutableLiveData<Resource<List<User>>>()


    val getUsersFromRoom: LiveData<List<User>> = roomDB.getUsers.asLiveData()
    var savedUser = User()


    fun deleteUserFromRoom(id: String) {
        viewModelScope.launch {
            roomDB.deleteProfile(id)
        }
    }

    fun addUserToRoom(user: User) {
        viewModelScope.launch {
            roomDB.insert(user)
        }
    }


    fun getUsersFromRemote() {

        viewModelScope.launch {
            remoteUsersLiveData.postValue(Resource.loading())
            try {
                val result = api.fetchUsers(100)
                when(result.data.isNotEmpty()) {
                    true -> remoteUsersLiveData.postValue(Resource.success(result.data))
                    false -> remoteUsersLiveData.postValue(Resource.error("You have no saved users"))
                }
            }
            catch (e: Exception) {
                remoteUsersLiveData.postValue(Resource.error(e.message))
            }
        }
    }


    fun getRemoteUsersLiveData() = remoteUsersLiveData




}
