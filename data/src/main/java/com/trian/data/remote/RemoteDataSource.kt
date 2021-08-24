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


class RemoteDataSource() {

    private  val TIME_OUT = 60_000
    private val ktorHttpClient = HttpClient(Android) {
        install(JsonFeature) {
           serializer = GsonSerializer(){
               setLenient()
               setPrettyPrinting()
               disableHtmlEscaping()
           }
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("Logger Ktor =>", message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP status:", "${response.status.value}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }
     suspend fun getAllUsers(): NetworkStatus<List<User>> {

         return safeApiCall {  ktorHttpClient.get("http://app.cexup.com/api/get/patient") }


    }
}