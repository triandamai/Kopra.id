package com.trian.domain.models.request

import com.trian.domain.entities.User

data class WebBaseResponse<T>(
    val success: Boolean,
    val data: T?,
    val user:T?,
    val access_token: String?
)

