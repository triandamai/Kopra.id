package com.cexup_sdk

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*


class ApiMockEngine {
    fun get() = client.engine

    private val responseHeaders =
        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                if (request.url.encodedPath == "/login") {
                    respond(LoginMockResponse(), HttpStatusCode.OK, responseHeaders)
                }else{
                    error("Unhandled ${request.url.encodedPath}")
                }
            }
        }
    }
}

object LoginMockResponse {
    operator fun invoke(): String =
        """
           {
            "success":true,
            "message":"success"
           }
        """.trimIndent() // This contains the mock JSON response for the specific resource.
}

