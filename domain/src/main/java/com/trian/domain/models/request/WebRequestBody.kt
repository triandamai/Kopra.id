package com.trian.domain.models.request

data class RequestLoginGoogle(
    val email:String,
    val name:String
)

data class RequestLogin(
    val username:String,
    val password:String
)

data class RequestRegister (
    val name: String,
    val address: String,
    val username: String,
    val email: String,
    val password: String
)

data class RequestWithSlug(
    val slug:String
)

data class RequestDetailDoctor(
    val slug:String
)
