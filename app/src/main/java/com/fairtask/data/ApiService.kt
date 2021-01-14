package com.fairtask.data

import com.fairtask.models.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


/*
This interface contains the endpoints that are being consuming directly from dummyapi.io
*/
interface ApiService {

    @Headers("app-id: 5fff840fbd5d005daec10ea4")
    @GET("data/api/user")
    suspend fun fetchUsers(
        @Query("limit") limit: Int
    ): UserResponse

}
