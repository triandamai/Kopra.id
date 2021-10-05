package com.trian.domain.models.request

data class RequestLoginGoogle(
    val email:String,
    val name:String
)

data class RequestLogin(
    val username:String,
    val password:String
)

