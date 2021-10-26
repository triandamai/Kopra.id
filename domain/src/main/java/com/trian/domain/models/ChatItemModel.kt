package com.trian.domain.models

data class ChatItemModel(
    val date:Long,
    val from:String,
    val lastMessage:String,
    val thumb:String
)