package com.trian.data.remote.app

import com.trian.domain.BaseResponse
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import okhttp3.Response
import retrofit2.http.POST

interface AppApiServices {

    @POST("/login/nurse")
    suspend fun loginNurse():BaseResponse<List<Nurse>>

    @POST("/login")
    suspend fun loginUser():BaseResponse<List<User>>


}