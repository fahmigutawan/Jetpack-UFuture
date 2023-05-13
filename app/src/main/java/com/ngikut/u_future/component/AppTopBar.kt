package com.ngikut.u_future.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType

@Composable
fun AppTopBar(
    onBackClicked: () -> Unit,
    title: String
) {
    TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = AppColor.grey50) {
        Row(modifier = Modifier.padding(horizontal = 16.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(AppColor.grey50)
                    .border(color = AppColor.grey500, width = 1.dp, shape = CircleShape)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = AppColor.grey800),
                        onClick = onBackClicked
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = AppColor.grey500
                )
            }

            AppText(text = title, style = AppType.subheading1)
        }
    }
}
@Composable
fun AppTopBarMidTitle(
    onBackClicked: () -> Unit,
    title: String
) {
    TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = AppColor.grey50) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart){
            Box(
                modifier = Modifier
                    .size(42.dp)
//                    .padding(horizontal = 16.dp)
                    .clip(CircleShape)
                    .background(AppColor.grey50)
                    .border(color = AppColor.grey500, width = 1.dp, shape = CircleShape)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = true, color = AppColor.grey800),
                        onClick = onBackClicked
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = AppColor.grey500
                )
            }

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                AppText(text = title, style = AppType.subheading1)
            }
        }
    }
}