package com.fairtask.viewmodels



import androidx.lifecycle.*
import com.fairtask.data.ApiService
import com.fairtask.data.RoomDB
import com.fairtask.models.User
import kotlinx.coroutines.launch
import java.lang.Exception

class DummyDataViewModel(
    private val api: ApiService,
    roomDB: RoomDB
) : ViewModel() {

//    private val convertLiveData = MutableLiveData<Resource<ConvertResponse>>()
//    private val symbolsLiveData = MutableLiveData<Resource<SymbolsResponse>>()


    val getProfileFromRoom: LiveData<List<User>> = roomDB.getUsers.asLiveData()



    fun fetchAllSymbols() {

//        viewModelScope.launch {
//            symbolsLiveData.postValue(Resource.loading())
//            try {
//                val result = api.symbols()
//                when(result.success) {
//                    true -> symbolsLiveData.postValue(Resource.success(result))
//                    false -> symbolsLiveData.postValue(Resource.error())
//                }
//            }
//            catch (e: Exception) {
//                symbolsLiveData.postValue(Resource.error())
//            }
//        }
    }


//    fun getConvertLiveData() = convertLiveData
//    fun getSymbolsLiveData() = symbolsLiveData




}
