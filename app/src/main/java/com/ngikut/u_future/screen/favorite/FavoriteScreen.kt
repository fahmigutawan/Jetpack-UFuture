package com.ngikut.u_future.screen.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ngikut.u_future.component.AppTextInputNormal
import com.ngikut.u_future.component.AppTopBarMidTitle
import com.ngikut.u_future.component.JurusanItem
import com.ngikut.u_future.model.dummy.DummyAiInfoJurusanRecommendation
import com.ngikut.u_future.model.remote.response.base.SingleJurusanResponse
import com.ngikut.u_future.ui.theme.AppColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController
) {
    val listOfDummyJurusan = listOf(
        DummyAiInfoJurusanRecommendation(
            prodiName = "Teknik Informatika",
            arah = "Saintek",
            tag = "Komputer, Jaringan",
            percent = 75.0
        ),
        DummyAiInfoJurusanRecommendation(
            prodiName = "Teknologi Informasi",
            arah = "Saintek",
            tag = "Komputer, Jaringan",
            percent = 39.0
        ),
        DummyAiInfoJurusanRecommendation(
            prodiName = "Sistem Informasi",
            arah = "Saintek",
            tag = "Komputer, Jaringan",
            percent = 66.0
        ), DummyAiInfoJurusanRecommendation(
            prodiName = "Pendidikan Teknologi Informasi",
            arah = "Saintek",
            tag = "Komputer, Jaringan",
            percent = 72.0
        ), DummyAiInfoJurusanRecommendation(
            prodiName = "Teknik Komputer",
            arah = "Saintek",
            tag = "Komputer, Jaringan",
            percent = 74.0
        )
    )
    val tmp = remember{ mutableStateOf("") }

    Scaffold(
        topBar = {
            AppTopBarMidTitle(onBackClicked = { /*TODO*/ }, title = "Favorit")
        }
    ) {
        LazyColumn{
            item {
                AppTextInputNormal(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    placeHolder = "Kata Kunci",
                    value = tmp.value,
                    onValueChange = {
                        tmp.value = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = AppColor.grey500
                        )
                    },
                    trailingIcon = {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(3.dp))
                                .background(AppColor.primary50)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = rememberRipple(
                                        color = AppColor.grey800,
                                        bounded = true
                                    ),
                                    onClick = {
                                        /*TODO call search function here*/
                                    }
                                )
                        ) {
                            Icon(
                                modifier = Modifier.padding(6.dp),
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "",
                                tint = AppColor.primary400
                            )
                        }
                    })
            }

            items(listOfDummyJurusan.map {
                SingleJurusanResponse(
                    "",
                    "",
                    it.percent.toFloat()/100,
                    it.prodiName,
                    it.tag,
                    it.arah
                )
            }) {
                JurusanItem(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                    item = it,
                    onClick = {/*TODO*/ }
                )
            }
        }
    }
}