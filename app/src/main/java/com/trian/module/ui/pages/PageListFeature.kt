package com.trian.module.ui.pages

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.CardAppVersion
import com.trian.component.cards.CardDoctor
import com.trian.component.cards.CardFeatures
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.TextTab
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.OnlineSchedule
import kotlinx.coroutines.CoroutineScope

/**
 * List Feature
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 11/09/2021
 */

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun PageListFeature(modifier:Modifier = Modifier,scrollState: LazyListState = rememberLazyListState()){

    var currentState by remember {
        mutableStateOf(HeaderState.Expanded)
    }
    currentState = if(scrollState.firstVisibleItemIndex > 0){
        HeaderState.Collapsed
    }else{
        HeaderState.Expanded
    }

    val transition = updateTransition(targetState = currentState, label = "a")
    val height by transition.animateDp(
        label = "a",
        transitionSpec = {
            when{
                HeaderState.Expanded isTransitioningTo HeaderState.Collapsed->
                    tween(durationMillis = 1000)
                else -> tween(durationMillis = 1000)
            }
        }
    ) { state ->
        when (state) {
            HeaderState.Collapsed -> 0.dp
            HeaderState.Expanded -> 160.dp
        }

    }
    var title = if(currentState == HeaderState.Collapsed){
        "Discover Feature"
    }else{
        ""
    }
    Scaffold(topBar = {
        AppBarDetail(page = title,elevation =when(currentState){
            HeaderState.Collapsed-> 4.dp
                HeaderState.Expanded-> 0.dp
        }) {

        }
    }) {

        Column {
            Column(modifier = modifier
                .height(height)
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)) {
                Row(modifier = modifier
                    .fillMaxWidth()) {
                    Text(
                        text="Discover Feature",
                        style= TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    IconToggleButton(checked = false, onCheckedChange = {}) {

                    }
                }
                Spacer(modifier = modifier
                    .height(8.dp))
                Text(text = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.")

            }
            LazyVerticalGrid(
                state=scrollState,
                cells = GridCells.Fixed(2),
                modifier = modifier
                    .padding(horizontal = 8.dp),
                content = {
                    items(count = 10,itemContent = {
                        index->

                        CardFeatures(image = "", name = "Smartwatch",index=index) {

                        }
                    })
                })
        }

    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewListFeature(){
    PageListFeature(scrollState = rememberLazyListState())
}