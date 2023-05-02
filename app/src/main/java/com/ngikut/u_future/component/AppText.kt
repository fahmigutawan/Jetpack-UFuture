package com.ngikut.u_future.component

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.AndroidView
import com.ngikut.u_future.R
import com.ngikut.u_future.ui.theme.AppColor

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
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

@SuppressLint("InflateParams")
@Composable
fun AppTextFromHtml(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppColor.grey900,
    fontId: Int,
    fontSize: TextUnit
) {
    AndroidView(
        factory = {
            LayoutInflater.from(it).inflate(R.layout.html_textview, null)
        },
        update = {
            val textView = it.findViewById<TextView>(R.id.html_textview)
            textView.apply {
                setText(Html.fromHtml(text, 0))
                setTextColor(color.hashCode())
                setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize.value)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    setTypeface(resources.getFont(fontId))
                }
            }
        }
    )
}