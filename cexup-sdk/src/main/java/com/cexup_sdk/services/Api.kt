package com.cexup_sdk.services

import android.util.Log
import com.cexup_sdk.storage.room.entity.Measurement
import com.cexup_sdk.storage.room.entity.Nurse
import com.cexup_sdk.storage.room.entity.Patient
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

private const val TIME_OUT = 60_000
private const val TAG_SERVICE = "Cexup Service"
class Api(httpClientEngine: HttpClientEngine){
    private val client = HttpClient(httpClientEngine){
        install(JsonFeature){
            serializer = GsonSerializer(){
                setPrettyPrinting()
                disableHtmlEscaping()
                setLenient()
            }
//            engine {
//                connectTimeout = TIME_OUT
//                socketTimeout = TIME_OUT
//            }
        }
        install(Logging){
            logger = object :Logger{
                override fun log(message: String) {
                    Log.v(TAG_SERVICE,message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver){
            onResponse { httpResponse: HttpResponse -> Log.d(TAG_SERVICE,"Http status: ${httpResponse.status.value}") }
        }
        install(DefaultRequest){
            header(HttpHeaders.ContentType,ContentType.Application.Json)
        }
    }

    suspend fun loginAsNurse(username:String,password:String):ApiResult<Nurse>{
        val job = client.post<JSONObject>(Endpoint.BASE_URL.loginNurse()){
            body="""
                {
                    "username":${username},
                    "password":${password}
                }
            """.trimIndent()
        }
        val users = gson().fromJson(job.getString(""),Nurse::class.java)
        val success = job.getBoolean("success")
        val message = job.getString("message")
        return ApiResult(success= success,data = users,message=message)
    }
    suspend fun getListUsers(token:String?):ApiResult<List<Patient>>{
        val job = client.get<JSONObject>(Endpoint.BASE_URL.getAllUsers())
        val users = mutableListOf<Patient>()
        val success = job.getBoolean("success")
        val message = job.getString("message")
        return ApiResult(success = success,data = users,message = message)
    }
    suspend fun sendMeasurement(type:String,measurement: Measurement):ApiResult<String>{
        val send = client.post<JSONObject>(Endpoint.BASE_URL_MEASUREMENT.postMeasurement(type)){
            body= populateSingleMeasurement(measurement)
        }
        val success = send.getBoolean("success")
        val message = send.getString("message")
        return ApiResult(success = success,data = "",message = message)
    }
    suspend fun sendMeasurement(type:String,measurement: List<Measurement>):ApiResult<String>{
        val send = client.post<JSONObject>(Endpoint.BASE_URL_MEASUREMENT.postMeasurement(type)){
            body= populateListMeasurement(measurement)
        }
        val success = send.getBoolean("success")
        val message = send.getString("message")
        return ApiResult(success = success,data = "",message = message)
    }
}

data class ApiResult<T>(val success: Boolean,val data:T,val message:String)