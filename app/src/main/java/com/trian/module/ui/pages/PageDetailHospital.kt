package com.trian.module.ui.pages

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState

import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.appbar.AppBarDetail
import com.trian.component.appbar.AppBarDetailHospital
import com.trian.component.cards.CardDoctor
import com.trian.component.cards.CardDoctorHospital
import com.trian.component.cards.CardNotFound
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.utils.TextTab
import com.trian.component.utils.coloredShadow
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.Doctor
import com.trian.domain.models.Hospital
import com.trian.domain.models.Schedule
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

@Composable
fun DetailHospital(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    modifier: Modifier = Modifier,
    nav:NavHostController,
    telemedicineViewModel: TelemedicineViewModel
) {
    val listDoctor by telemedicineViewModel.doctorStatus.observeAsState()
    LaunchedEffect(key1 = scaffoldState) {
        telemedicineViewModel.getListDoctor {  }
    }
    Scaffold(
        topBar = {
            AppBarDetailHospital(hospital =Hospital(
                id = 1,
                slug = "rs-tele-cexup",
                description = "",
                thumb = "",
                thumb_original = "",
                name = "RS Tele Cexup",
                address = "Jl. Jakarta Barat RT005/003, Meruya, Kecamatan Meruaya, Kelurahan Meruya, Kota Jakarta",
                others = "",
            ), onBackPressed = { /*TODO*/ },hospitalPict = painterResource(id = R.drawable.hospital), onNameClick = {nav.navigate(Routes.SHEET_DETAIL_HOSPITAL)})
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
                when(listDoctor){
                    is DataStatus.NoData ->{
                        item{
                            CardNotFound()
                        }
                    }
                    is DataStatus.Loading ->{
                        /*loading to do*/
                    }
                    is DataStatus.HasData ->{
                        items(count = listDoctor?.data!!.size, itemContent = { index ->
                            CardDoctorHospital(
                                doctor = listDoctor?.data!![index],
                                index,
                                onClick = { doctor, index:Int -> nav.navigate(Routes.DETAIL_DOCTOR)},
                            )
                        })
                    }
                }

            })
        }
    }

}