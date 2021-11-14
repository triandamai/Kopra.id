package com.trian.kopra.ui.pages.reminder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.capitalizeWords
import com.trian.component.R
import com.trian.component.cards.CardReminder
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.TYPE_STORE
import com.trian.domain.models.network.GetStatus
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Plus24

@Composable
fun PageListReminder(
    modifier:Modifier=Modifier,
    lazyState:LazyListState = rememberLazyListState(),
    mainViewModel: MainViewModel,
    nav:NavHostController
){
    val listReminder by mainViewModel.listReminder

    LaunchedEffect(key1 = Unit, block = {
        mainViewModel.getListReminder()
    })
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ){
                IconToggleButton(checked = false, onCheckedChange = {
                    nav.popBackStack()
                }) {
                    Icon(Octicons.ArrowLeft24,"")
                }
                Text(
                    text = "Pengingat",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                )
                Box(){}
            }
        },
        bottomBar = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    nav.navigate(Routes.CREATE_REMINDER)
                }
            ) {
                Icon(Octicons.Plus24,"")
            }
        }
    ) {
            LazyColumn(
                state = lazyState,
                modifier = modifier.padding(10.dp),
                content = {
                when(listReminder){
                    is GetStatus.HasData -> {
                        items(
                            count = listReminder.data?.size ?: 0,
                            itemContent = {index->
                                CardReminder(
                                    index=index,
                                    reminder = listReminder.data!![index]
                                ){
                                        index, reminder ->
                                }
                            })
                    }
                    is GetStatus.Idle -> {}
                    is GetStatus.Loading -> {}
                    is GetStatus.NoData -> {}
                }
            }
            )
    }
}