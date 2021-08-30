package com.trian.data.remote.app

import com.trian.domain.repository.BaseResponse
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User


class AppRemoteDataSource(private val apiServices: AppApiServices):IAppRemoteDataSource {
    override suspend fun loginNurse(): BaseResponse<List<Nurse>> =apiServices.loginNurse()

    override suspend fun loginUser(): BaseResponse<List<User>> = apiServices.loginUser()


}