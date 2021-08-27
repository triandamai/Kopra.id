package com.trian.data.remote

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorEngine {
    private  val TIME_OUT = 60_000
    fun ktorHttpClient() = HttpClient(Android) {
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
    fun ktorHttpClientMock() = HttpClient(MockEngine){
        engine {
            addHandler {
                    request -> when(request.url.fullPath){
                "/api/get/patient"->{
                    val responseHeaders = headersOf("Content-Type" to listOf(ContentType.Text.Plain.toString()))
                    respond("Hello, world", headers = responseHeaders)
                }
                else -> error("Unhandled ${request.url.fullPath}")
            }
            }
        }
    }
}