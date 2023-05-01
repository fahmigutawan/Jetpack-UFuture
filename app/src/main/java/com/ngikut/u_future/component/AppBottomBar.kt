package com.ngikut.u_future.component

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppBottomBar(
    currentRoute: String,
    onClick: (route: String) -> Unit
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
            repeat(AppBottomBarItem.values().size) { index ->
                if (index == (AppBottomBarItem.values().size / 2)) {
                    Box(modifier = Modifier)
                }

                IconButton(onClick = { onClick(AppBottomBarItem.values()[index].route) }) {
                    AnimatedContent(targetState = AppBottomBarItem.values()[index].route) {
                        when (it) {
                            currentRoute -> Icon(
                                painter = rememberAsyncImagePainter(
                                    model = AppBottomBarItem.values()[index].selectedIconId
                                ),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )

                            else -> Icon(
                                painter = rememberAsyncImagePainter(
                                    model = AppBottomBarItem.values()[index].unselectedIconId
                                ),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class AppBottomBarItem(
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val route: String
) {
    Home(
        R.drawable.bottombar_home_selected,
        R.drawable.bottombar_home_unselected,
        NavRoute.Home.name
    ),
    Compare(
        R.drawable.bottombar_komparasi_selected,
        R.drawable.bottombar_komparasi_unselected,
        NavRoute.Comparation.name
    ),
    Favorite(
        R.drawable.bottombar_favorite_selected,
        R.drawable.bottombar_favorite_unselected,
        NavRoute.Favorite.name
    ),
    Profile(
        R.drawable.bottombar_profile_selected,
        R.drawable.bottombar_profile_unselected,
        NavRoute.Profile.name
    )
}