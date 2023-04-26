package com.ngikut.u_future.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType

@Composable
fun AppTextInputNormal(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 4.dp,
    showWarningMessage: Boolean = false,
    warningMessage: String = "",
    placeHolder: String,
    textStyle: TextStyle = AppType.subheading3,
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = RoundedCornerShape(4.dp),
    enabled: Boolean = true,
    readOnly: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions? = null,
    keyboardActions: KeyboardActions? = null,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    textColor: Color = AppColor.grey800,
    placeHolderColor: Color = AppColor.grey400,
    disabledTextColor: Color = AppColor.grey200,
    backgroundColor: Color = if (enabled) AppColor.grey50 else AppColor.grey400,
    cursorColor: Color = AppColor.grey800,
    errorCursorColor: Color = AppColor.danger400,
    focusedIndicatorColor: Color = AppColor.primary400,
    unfocusedIndicatorColor: Color = AppColor.grey400,
    disabledIndicatorColor: Color = AppColor.grey200,
    errorIndicatorColor: Color = AppColor.danger400
) {
    val focusManager = LocalFocusManager.current

    Column(verticalArrangement = Arrangement.spacedBy(contentSpacing)) {
        OutlinedTextField(
            modifier = modifier,
            shape = shape,
            readOnly = readOnly,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions ?: KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = keyboardActions ?: KeyboardActions(onDone = {
                focusManager.clearFocus(true)
            }),
            singleLine = singleLine,
            maxLines = maxLines,
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = textColor,
                disabledTextColor = disabledTextColor,
                backgroundColor = backgroundColor,
                cursorColor = cursorColor,
                errorCursorColor = errorCursorColor,
                focusedIndicatorColor = focusedIndicatorColor,
                unfocusedIndicatorColor = unfocusedIndicatorColor,
                disabledIndicatorColor = disabledIndicatorColor,
                errorIndicatorColor = errorIndicatorColor
            ),
            label = {
                AppText(
                    text = placeHolder,
                    style = AppType.subheading3,
                    color = if(isError) errorIndicatorColor else placeHolderColor
                )
            },
            textStyle = textStyle
        )

        if (showWarningMessage) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = R.drawable.ic_alert),
                    contentDescription = "Alert icon",
                    tint = when {
                        isError -> AppColor.danger400
                        else -> AppColor.grey400
                    }
                )
                AppText(
                    text = warningMessage,
                    style = AppType.subheading3,
                    color = when {
                        isError -> AppColor.danger400
                        else -> AppColor.grey400
                    }
                )
            }
        }
    }
}
