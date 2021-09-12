package com.trian.module.ui.pages

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.CardDoctor
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.TextTab
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.HospitalList
import com.trian.domain.models.OnlineSchedule

/**
 * Dashboard Detail Hospital
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 11/09/2021
 */
enum class HeaderState{
    Collapsed,
    Expanded
}
@ExperimentalFoundationApi
@Composable
fun PageDetailHospital(modifier:Modifier = Modifier,scrollState:LazyListState = rememberLazyListState()){

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
            HeaderState.Expanded -> 300.dp
        }

    }
    var title = if(currentState == HeaderState.Collapsed){
        "RS Universitas Indonesia"
    }else{
        ""
    }
    Scaffold(topBar = {
        AppBarDetail(page = title,elevation = 0.dp) {

        }
    }) {

        Column {
            Column(modifier = modifier
                .height(height)
                .padding(top = 20.dp, bottom = 20.dp)) {
                Column(modifier = modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.dummy_profile),
                        modifier= modifier
                            .clip(RoundedCornerShape(12.dp))
                            .coloredShadow(
                                color = ColorFontFeatures
                            )
                            .width(120.dp)
                            .height(120.dp),
                        contentScale= ContentScale.FillWidth,
                        contentDescription = "Image hospital")
                    Spacer(modifier = modifier
                        .height(16.dp))
                    Text(
                        text="Rs Universitas Indonesia",
                        style=TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    Spacer(modifier = modifier
                        .height(8.dp))
                    Text(text = "Meruya selatan kembangan")
                    Spacer(modifier = modifier
                        .height(20.dp))
                }
                TextTab(tabSelected = 0, tabData = listOf("Obgyn","Dentist","Pediatrician","Cardiologist","General Practician","Family Physician"), onSelected = {})

            }
            LazyVerticalGrid(
                state=scrollState,
                cells = GridCells.Fixed(2),
                modifier = modifier
                    .padding(horizontal = 8.dp),
                content = {
                items(count = 10,itemContent = {

                    CardDoctor(doctor = Doctor(
                        title= "Dr. Yakob togar",
                        slug= "",
                        description= "",
                        offlineSchedule= null,
                        onlineSchedule= OnlineSchedule(
                            monday="",
                            tuesday="",
                            wednesday=""
                        ),
                        speciality= "Kandungan",
                        hospital= "",
                        hospitalList= listOf(),
                        thumbOriginal= "",
                        thumb= ""
                    ), onClick ={
                            doctor, index ->
                    })
                })
            })



        }

    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewDetailHospital(){
    PageDetailHospital(scrollState = rememberLazyListState())
}