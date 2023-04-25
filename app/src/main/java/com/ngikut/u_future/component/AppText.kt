package com.ngikut.u_future.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.ngikut.u_future.ui.theme.AppColor

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text:String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppColor.grey900,
    style: TextStyle
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = style
    )
}