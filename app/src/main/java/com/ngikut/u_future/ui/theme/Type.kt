package com.ngikut.u_future.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ngikut.u_future.R

object AppType{
    //Heading
    val h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 24.sp
    )
    val h2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 22.sp
    )
    val h3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 20.sp
    )
    val h4 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 18.sp
    )
    val h5 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 16.sp
    )

    //Heading semibold
    val h3Semibold = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_semibold)),
        fontSize = 18.sp
    )
    val h4Semibold = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_semibold)),
        fontSize = 16.sp
    )

    //Subheading
    val subheading1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 18.sp
    )
    val subheading2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 16.sp
    )
    val subheading3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_medium)),
        fontSize = 14.sp
    )

    //Body
    val body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_regular)),
        fontSize = 16.sp
    )
    val body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_regular)),
        fontSize = 14.sp
    )
    val body3 = TextStyle(
        fontFamily = FontFamily(Font(R.font.sf_display_regular)),
        fontSize = 12.sp
    )
}