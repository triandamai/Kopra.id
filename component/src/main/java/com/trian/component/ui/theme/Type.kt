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
        Font(R.font.bold),
        Font(R.font.black),
        Font(R.font.blackitalic),
        Font(R.font.bolditalic),
        Font(R.font.extrabold),
        Font(R.font.extrabolditalic),
        Font(R.font.extralight),
        Font(R.font.extralightitalic),
        Font(R.font.italic),
        Font(R.font.light),
        Font(R.font.lightitalic),
        Font(R.font.medium),
        Font(R.font.mediumitalic),
        Font(R.font.regular),
        Font(R.font.semibold),
        Font(R.font.semibolditalic),
        Font(R.font.thin),
        Font(R.font.thinitalic),
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