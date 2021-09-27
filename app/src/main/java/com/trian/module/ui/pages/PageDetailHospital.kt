package com.trian.module.ui.pages

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.appbar.AppBarDetailHospital
import com.trian.component.cards.CardDoctor
import com.trian.component.cards.CardDoctorHospital
import com.trian.component.cards.HospitalCard
import com.trian.component.ui.theme.BlackOpacity
import com.trian.component.ui.theme.BlueOpacity
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.TextTab
import com.trian.component.utils.coloredShadow
import com.trian.domain.models.Doctor
import com.trian.domain.models.Hospital
import com.trian.domain.models.HospitalList
import com.trian.domain.models.Schedule
import compose.icons.Octicons
import compose.icons.octicons.Location16
import kotlinx.coroutines.CoroutineScope

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
fun PageDetailHospital(modifier:Modifier = Modifier,scope:CoroutineScope,scrollState:LazyListState = rememberLazyListState()){

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
                        onlineSchedule= Schedule(
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
//                        nav.navigate(Routes.DETAIL_DOCTOR)
                    })
                })
            })



        }

    }
}

@Composable
fun DetailHospital(
    modifier: Modifier = Modifier,
    nav:NavHostController
) {
    Scaffold(
        topBar = {
            AppBarDetailHospital(hospital =Hospital(
                id = 1,
                slug = "rs-tele-cexup",
                description = "",
                thumb = "",
                thumbOriginal = "",
                name = "RS Tele Cexup",
                address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                others = "",
            ), onBackPressed = { /*TODO*/ },hospitalPict = painterResource(id = R.drawable.hospital), onNameClick = {nav.navigate(Routes.SHEET_DETAILHOSPITAL)})
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),

            ) {

            TextTab(tabSelected = 0, tabData = listOf(
                "Obgyn",
                "Dentist",
                "Pediatrician",
                "Cardiologist",
                "General Practician",
                "Family Physician"
            ), onSelected = {})

            LazyColumn(content = {
                items(count = 10, itemContent = {
                    CardDoctorHospital(
                        doctor = Doctor(
                            speciality = "Specialist Kandungan",
                            onlineSchedule = Schedule(
                                monday = "13:00-14:00",
                                tuesday = "13:00-14:00",
                                wednesday = "13:00-14:00"
                            ),
                            offlineSchedule = Schedule(
                                monday = "13:00-14:00",
                                tuesday = "13:00-14:00",
                                wednesday = "13:00-14:00"
                            ),
                            hospitalList = listOf(),
                            hospital = "Cexup",
                            description = "",
                            slug = "",
                            thumb = "",
                            thumbOriginal = "",
                            title = "Dr. Yakob Simatupang",
                        ),
                        hospital = Hospital(
                            id = 1,
                            slug = "rs-tele-cexup",
                            description = "",
                            thumb = "",
                            thumbOriginal = "",
                            name = "RS Tele Cexup",
                            address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                            others = "",
                        ),
                        onClick = { doctor, index -> },
                    )
                })

            })
        }
    }

}


@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewDetailHospital(){
    val nav = rememberNavController()
    val onlineSchedule = Schedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    val offlineSchedule = Schedule(monday = "13:00-14:00",tuesday = "13:00-14:00",wednesday = "13:00-14:00")
    DetailHospital(
        nav = nav
    )
}