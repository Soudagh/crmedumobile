package com.example.crmedumobile.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.crmedumobile.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val CrmEduMontserrat = FontFamily(
    Font(R.font.montserrat_alternates_bold, FontWeight.Bold),
    Font(R.font.montserrat_alternates_black, FontWeight.Black),
    Font(R.font.montserrat_alternates_regular, FontWeight.Normal),
    Font(R.font.montserrat_alternates_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_alternates_medium, FontWeight.Medium)

)

private val RegularMontserratStyle = TextStyle(
    fontFamily = CrmEduMontserrat,
    fontWeight = FontWeight.Normal,
    lineHeight = 24.sp,
    platformStyle = PlatformTextStyle(includeFontPadding = false)
)

private val BoldMontserratStyle = TextStyle(
    fontFamily = CrmEduMontserrat,
    fontWeight = FontWeight.Bold,
    platformStyle = PlatformTextStyle(includeFontPadding = false)
)

private val SemiBoldMontserratStyle = TextStyle(
    fontFamily = CrmEduMontserrat,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 24.sp,
    platformStyle = PlatformTextStyle(includeFontPadding = false)
)

val RegularMontserrat16 = RegularMontserratStyle.copy(fontSize = 16.sp)
val RegularMontserrat14 = RegularMontserratStyle.copy(fontSize = 14.sp)

val SemiBoldMontserrat18 = SemiBoldMontserratStyle.copy(fontSize = 18.sp)
val SemiBoldMontserrat16 = SemiBoldMontserratStyle.copy(fontSize = 16.sp)
val SemiBoldMontserrat14 = SemiBoldMontserratStyle.copy(fontSize = 14.sp)
val SemiBoldMontserrat12 = SemiBoldMontserratStyle.copy(fontSize = 12.sp)
val SemiBoldMontserrat24 = SemiBoldMontserratStyle.copy(fontSize = 24.sp)

val BoldMontserrat14 = BoldMontserratStyle.copy(fontSize = 14.sp)
val BoldMontserrat16 = BoldMontserratStyle.copy(fontSize = 16.sp)
val BoldMontserrat20 = BoldMontserratStyle.copy(fontSize = 20.sp)
val BoldMontserrat36 = BoldMontserratStyle.copy(fontSize = 36.sp)

