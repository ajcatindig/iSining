package com.xanthenite.isining.composeapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.xanthenite.isining.R

// Set of Material typography styles to start with
val Inter = FontFamily(
        Font(R.font.inter_regular , FontWeight.Normal) ,
        Font(R.font.inter_bold , FontWeight.Bold))

val Poppins = FontFamily(
        Font(R.font.poppins_regular, FontWeight.Normal) ,
        Font(R.font.poppins_bold, FontWeight.Bold))

val raleway = FontFamily(
        Font(R.font.raleway_regular),
        Font(R.font.raleway_semi_bold, FontWeight.W600),
        Font(R.font.raleway_bold, FontWeight.Bold)
                               )

val universalStd = FontFamily(
        Font(R.font.universal_std)
                                     )

val typography = Typography(
        h4 = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W600,
                fontSize = 30.sp
                      ),
        h5 = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W600,
                fontSize = 24.sp
                      ),
        h6 = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W600,
                fontSize = 20.sp
                      ),
        subtitle1 = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp
                             ),
        subtitle2 = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
                             ),
        body1 = TextStyle(
                fontFamily = universalStd,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
                         ),
        body2 = TextStyle(
                fontFamily = universalStd,
                fontSize = 14.sp
                         ),
        button = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp
                          ),
        caption = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
                           ),
        overline = TextStyle(
                fontFamily = raleway,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp
                            )
                           )