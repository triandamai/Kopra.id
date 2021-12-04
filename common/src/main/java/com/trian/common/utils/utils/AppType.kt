package com.trian.common.utils.utils

import android.content.Context

fun Context.getType(): LevelUser {
    if(packageName == "app.trian.kopra.farmer") return LevelUser.FARMER
    if(packageName == "app.trian.kopra.collector") return LevelUser.COLLECTOR
    if(packageName == "app.trian.kopra.tenant") return LevelUser.TENANT
    return LevelUser.UNKNOWN
}


enum class LevelUser{
    TENANT,
    COLLECTOR,
    FARMER,
    UNKNOWN
}