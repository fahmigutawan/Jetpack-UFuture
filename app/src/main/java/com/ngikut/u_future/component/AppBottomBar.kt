package com.ngikut.u_future.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.util.NavRoute

@Composable
fun AppBottomBar(
    currentRoute:String,
    onClick:(route:String) -> Unit
) {
    BottomAppBar(
        backgroundColor = AppColor.grey50,
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { onClick(AppBottomBarItem.values()[0].route) }) {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = when(AppBottomBarItem.values()[0].route){
                            currentRoute -> AppBottomBarItem.values()[0].selectedIconId
                            else -> AppBottomBarItem.values()[0].unselectedIconId
                        }
                    ),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }

            Box(modifier = Modifier)

            IconButton(onClick = { onClick(AppBottomBarItem.values()[1].route) }) {
                Icon(
                    painter = rememberAsyncImagePainter(
                        model = when(AppBottomBarItem.values()[1].route){
                            currentRoute -> AppBottomBarItem.values()[1].selectedIconId
                            else -> AppBottomBarItem.values()[1].unselectedIconId
                        }
                    ),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

enum class AppBottomBarItem(
    val selectedIconId:Int,
    val unselectedIconId:Int,
    val route:String
){
    Home(
        R.drawable.bottombar_home_selected,
        R.drawable.bottombar_home_unselected,
        NavRoute.Home.name
    ),
    Profile(
        R.drawable.bottombar_profile_selected,
        R.drawable.bottombar_profile_unselected,
        NavRoute.Profile.name
    )
}