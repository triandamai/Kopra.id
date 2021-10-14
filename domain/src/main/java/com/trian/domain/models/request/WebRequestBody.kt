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

data class RequestBookingDoctor(
    val type:String,
    val allow_access_data:Boolean,
    val username: String,
    val username_doctor:String,
    val user_id:String,
    val id:String,
    val time:String,
    val date:String,
    val patient:String,
    val note:String,
    val price:String,
    val kuisioner:List<Kuisioner>,
)

data class Kuisioner(
    val id:Int,
    val question:String,
    val option_value:List<String>,
    val option_key:List<String>,
    val option_type:String,
    val answer:List<String>,
)


