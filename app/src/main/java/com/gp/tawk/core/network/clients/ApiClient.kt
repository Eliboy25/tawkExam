package com.gp.tawk.core.network.clients

import com.gp.tawk.core.network.entities.*
import com.gp.tawk.core.room.entities.GitUserEntity
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {


    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int
    ): MutableList<GitUserEntity>


    @GET("users/{username}")
    suspend fun getUserProfile(
        @Path("username") username: String
    ):GitUserEntity


}