package com.trian.module.ui.pages

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.appbar.AppBarDetailHospital
import com.trian.component.cards.CardDoctorHospital
import com.trian.component.cards.CardNotFound
import com.trian.component.utils.TextTab
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.Hospital

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
    var tabSelected by remember {
        mutableStateOf(0)
    }
    val data = listOf<String>(
        "Obgyn",
        "umum",
        "Pediatrician",
        "Cardiologist",
        "General Practician",
        "Family Physician",
    )
    val listDoctor by telemedicineViewModel.specialistStatus.observeAsState()
    val detailHospital by telemedicineViewModel.detailHospitalStatus.observeAsState()
    val listSpecialist by telemedicineViewModel.listSpecialistStatus.observeAsState()
    fun getDoctorSpecialist(slug: String){
        telemedicineViewModel.getSpecialist(slug){ }
    }
    LaunchedEffect(key1 = scaffoldState) {
        telemedicineViewModel.getListDoctor {  }
        telemedicineViewModel.getDetailHospital("rs-telecexup-indonesia"){}
        telemedicineViewModel.getSpeciality()

    }
    var tab = when(listSpecialist){
        is DataStatus.HasData -> {
            listSpecialist?.data!!.map { it }
        }else -> listOf()
    }

    Scaffold(
        topBar = {
            when(detailHospital){
                is DataStatus.NoData ->{
                    AppBarDetailHospital(hospital = Hospital(
                        id = 1,
                        slug = "",
                        description = "",
                        thumb = "",
                        thumb_original = "",
                        name = "",
                        address = "",
                        others = "",
                    ), onBackPressed = { /*TODO*/ },hospitalPict = painterResource(id = R.drawable.hospital), onNameClick = {nav.navigate(Routes.SHEET_DETAIL_HOSPITAL)})
                    Log.e("nodata", detailHospital?.data.toString())
                }
                is DataStatus.Loading ->{

                }
                is DataStatus.HasData ->{
                    AppBarDetailHospital(
                        hospital = detailHospital?.data!!,
                        onBackPressed = { /*TODO*/ },
                        hospitalPict = painterResource(id = R.drawable.hospital),
                        onNameClick = { nav.navigate(Routes.SHEET_DETAIL_HOSPITAL) }
                    )
                    Log.e("nodata", detailHospital?.data.toString())
                }
            }
            }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),

            ) {
            if (tab.isNotEmpty()){
                if(tabSelected == 0){
                    getDoctorSpecialist(listSpecialist?.data!![0].slug)
                }
                TextTab(tabSelected = tabSelected, tabData = tab, onSelected = {index,specialist ->
                        getDoctorSpecialist(specialist.slug)
                        tabSelected = index
                    })

            }


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