package com.cexup_sdk.services

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

private const val TIME_OUT = 60_000
private const val TAG_SERVICE = "Cexup Service"

private val ktorHttpClient = HttpClient(Android){
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