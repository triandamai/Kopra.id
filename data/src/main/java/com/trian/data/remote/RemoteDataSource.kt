package com.trian.data.remote

import android.util.Log
import com.trian.common.utils.network.NetworkStatus
import com.trian.common.utils.network.UNKNOWN_NETWORK_EXCEPTION
import com.trian.data.utils.safeApiCall
import com.trian.domain.BaseResponse
import com.trian.domain.entities.User
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.json.JSONObject


class RemoteDataSource(private val httpClient: HttpClient) {


     suspend fun getAllUsers(): NetworkStatus<List<User>> {

         return safeApiCall {  httpClient.get("http://app.cexup.com/api/get/patient") }


    }
}