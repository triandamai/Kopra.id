package com.trian.common.utils.utils

fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::titlecase) }.joinToString(" ")

fun Float.metersToKm():Float= this / 1000