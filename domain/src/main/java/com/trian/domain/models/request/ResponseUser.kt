package com.trian.domain.models.request

import com.trian.domain.entities.User

data class ResponseUser(
    val id: Long,
    val user_id: String,
    val user_code:String,
    val type: String?="kosong",
    val no_type: String?="kosong",
    val name: String,
    val username: String,
    val gender: String?="kosong",
    val email: String,
    val phone_number: String="kosong",
    val address: String="kosong",
    val thumb: String?
)

fun ResponseUser.toUser():User = User(
    id_user=null,
    user_id=user_id,
    type = type!!,
    user_code=user_code,
    no_type = no_type!!,
    name = name,
    username=username,
    gender = gender!!,
    email = email,
    phone_number = phone_number,
    address = address,
    thumb = thumb?:"kosong"
)
