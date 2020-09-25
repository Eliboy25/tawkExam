package com.gp.tawk.core.network.helpers

import com.google.gson.Gson
import com.gp.tawk.core.network.entities.ErrorResponse
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHelper @Inject constructor() {

    fun getMessage(throwable: Throwable) : String {
        return (throwable as? HttpException)?.response()?.errorBody()?.string()?.let {
            val errorResponse = Gson().fromJson(it, ErrorResponse::class.java)
            errorResponse.message
        } ?: throwable.message!!
    }
}