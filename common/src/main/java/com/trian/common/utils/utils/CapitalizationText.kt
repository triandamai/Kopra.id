package com.trian.common.utils.utils

fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::titlecase) }.joinToString(" ")