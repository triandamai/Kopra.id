package com.trian.component.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.trian.component.R

val fonts =
    FontFamily(
        Font(R.font.bold,FontWeight.Bold),
        Font(R.font.black,FontWeight.Black),
        Font(R.font.extrabold, FontWeight.ExtraBold),
        Font(R.font.extralight, FontWeight.ExtraLight),
        Font(R.font.light,FontWeight.Light),
        Font(R.font.medium,FontWeight.Medium),
        Font(R.font.regular,FontWeight.Normal),
        Font(R.font.semibold,FontWeight.SemiBold),
        Font(R.font.thin, FontWeight.Thin),
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp
)
)