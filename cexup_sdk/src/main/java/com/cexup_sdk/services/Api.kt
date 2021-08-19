package com.cexup_sdk.services

import android.util.Log
import com.cexup_sdk.storage.persistence.Persistence
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
class Api(private val persistence: Persistence){
    private val client = HttpClient(Android){
        install(JsonFeature){
            serializer = GsonSerializer(){
                setPrettyPrinting()
                disableHtmlEscaping()
                setLenient()
            }
            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
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

    suspend fun loginAsNurse(username:String,password:String,result:(ApiResult<Nurse>)->Unit){
        val job = client.post<JSONObject>(Endpoint.BASE_URL.loginNurse()){
            body="""
                {
                    "username":${username},
                    "password":${password}
                }
            """.trimIndent()
        }
        job.let {
            val users = gson().fromJson(it.getString(""),Nurse::class.java)
            val success = it.getBoolean("success")
            val message = it.getString("message")
            result( ApiResult(success= success,data = users,message=message))
        }

    }
    suspend fun getListUsers(token:String?,result: (ApiResult<List<Patient>>) -> Unit){
        val job = client.get<JSONObject>(Endpoint.BASE_URL.getAllUsers()){
            header("Authorization",token)
        }
        val users = mutableListOf<Patient>()
        val success = job.getBoolean("success")
        val message = job.getString("message")
        result(ApiResult(success = success,data = users,message = message))

    }
    suspend fun sendMeasurement(type:String,measurement: Measurement,result:(ApiResult<String>)->Unit){
        val send = client.post<JSONObject>(Endpoint.BASE_URL_MEASUREMENT.postMeasurement(type)){
            val  patient = persistence.currentPatient()

            body= when(type){
                "corporate"->{
                    val nurse = persistence.currentNurse()
                    populateMeasureOwnerNurseAndPatient(patient!!,nurse!!,measurement)
                }
                else -> populateMeasureOwnerPatient(patient!!,measurement)

            }
        }
        val success = send.getBoolean("success")
        val message = send.getString("message")
        result(ApiResult(success = success,data = "",message = message))
    }
    suspend fun sendMeasurement(type:String,measurements: List<Measurement>,result:(ApiResult<String>)->Unit){
        val send = client.post<JSONObject>(Endpoint.BASE_URL_MEASUREMENT.postMeasurement(type)){
            val  patient = persistence.currentPatient()

            body= when(type){
                "corporate"->{
                    val nurse = persistence.currentNurse()
                    populateMeasureOwnerNurseAndPatient(patient!!,nurse!!,measurements)
                }
                else -> populateMeasureOwnerPatient(patient!!,measurements)

            }
        }
        val success = send.getBoolean("success")
        val message = send.getString("message")
        result(ApiResult(success = success,data = "",message = message))
    }
}

data class ApiResult<T>(val success: Boolean,val data:T,val message:String)