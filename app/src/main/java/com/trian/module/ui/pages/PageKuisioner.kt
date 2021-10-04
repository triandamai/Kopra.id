package com.trian.module.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.ContentUI
import com.trian.component.utils.DetailSmartwatchUIDSL
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.KebabHorizontal24
import compose.icons.octicons.Note24

@Composable
fun PageKuisioner(m:Modifier = Modifier){
    val scaffoldState = rememberScaffoldState()
    val scrollState = rememberScrollState()
    val soal1 = listOf<String>(
        "Iya",
        "tidak",
    )
    val mRememberSoal1 = remember { mutableStateOf("") }
    val soal2 = listOf<String>(
        "PCR",
        "Rapid Antigen",
    )
    val mRememberSoal2 = remember { mutableStateOf("") }
    val soal3 = listOf<String>(
        "Negative",
        "Positif",
    )
    val mRememberSoal3 = remember { mutableStateOf("") }
    val soal5 = listOf<String>(
        "Iya",
        "tidak",
    )
    val mRememberSoal5 = remember { mutableStateOf("") }

    val isCheckedDemam = remember { mutableStateOf(false) }
    val mRememberDemam = remember { mutableStateOf("") }
    val isCheckedBersama = remember { mutableStateOf(false) }
    val mRememberBersama = remember { mutableStateOf("") }
    val isCheckedKontak = remember { mutableStateOf(false) }
    val mRememberKontak = remember { mutableStateOf("") }
    val isCheckedTidakTerpapar = remember { mutableStateOf(false) }
    val mRememberTidakTerpapar = remember { mutableStateOf("") }
    val mRememberPilek = remember { mutableStateOf("") }
    val isCheckedPilek = remember { mutableStateOf(false) }
    val mRememberNyeriOtot = remember { mutableStateOf("") }
    val isCheckedNyeriOtot = remember { mutableStateOf(false) }
    val mRemembersesak = remember { mutableStateOf("") }
    val isCheckedSesak = remember { mutableStateOf(false) }
    val mRememberDayaPerasa = remember { mutableStateOf("") }
    val isCheckedDayaPerasa = remember { mutableStateOf(false) }
    val mRememberMuntahDiare = remember { mutableStateOf("") }
    val isCheckedMuntahDiare = remember { mutableStateOf(false) }
    val mRememberBatuk = remember { mutableStateOf("") }
    val isCheckedBatuk = remember { mutableStateOf(false) }
    val mRememberNyeriTenggorokan = remember { mutableStateOf("") }
    val isCheckedNyeriTenggorokan = remember { mutableStateOf(false) }
    val mRememberTidakAda = remember { mutableStateOf("") }
    val isCheckedTidakAda = remember { mutableStateOf(false) }
    ContentUI(appBar = { /*TODO*/ }, scaffoldState = scaffoldState) {
        header {
            Row(
                modifier = m
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.ChevronLeft24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(Octicons.Note24,"")
                    Spacer(modifier = m.width(5.dp))
                    Text(text = "Questionnaire",
                        style=MaterialTheme.typography.h1.copy(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp
                        ))
                }
                Card(
                    shape = RoundedCornerShape(8.dp)
                ){
                    Icon(
                        Octicons.KebabHorizontal24,
                        contentDescription = "",
                        modifier = m.padding(5.dp)
                    )
                }
            }
        }
        body {
            Column(
                modifier = m.verticalScroll(state = scrollState)
            ){
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "1. Apakah anda melakukan pemeriksaan covid-19 dalam 10 hari terakhir?")
                        Spacer(modifier = m.height(10.dp))
                        soal1.forEach { item->Row{
                            RadioButton(
                                selected = mRememberSoal1.value == item,
                                onClick = { mRememberSoal1.value = item},
                                enabled = true,
                                colors = RadioButtonDefaults.colors(selectedColor = BluePrimary)
                            )
                            Text(text = item,modifier = m.padding(start = 8.dp))
                        } }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "2. Pemeriksaan apa yang pernah anda lakukan dalam 10 hari terkahir?")
                        Spacer(modifier = m.height(10.dp))
                        soal2.forEach { item->Row{
                            RadioButton(
                                selected = mRememberSoal2.value == item,
                                onClick = { mRememberSoal2.value = item},
                                enabled = true,
                                colors = RadioButtonDefaults.colors(selectedColor = BluePrimary)
                            )
                            Text(text = item,modifier = m.padding(start = 8.dp))
                        } }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "3. Hasil dari pemeriksaan?")
                        Spacer(modifier = m.height(10.dp))
                        soal3.forEach { item->Row{
                            RadioButton(
                                selected = mRememberSoal3.value == item,
                                onClick = { mRememberSoal3.value = item},
                                enabled = true,
                                colors = RadioButtonDefaults.colors(selectedColor = BluePrimary)
                            )
                            Text(text = item,modifier = m.padding(start = 8.dp))
                        } }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "4. Apakah anda memiliki gejala berikut dalam 14 hari terakhir?")
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedDemam.value = !isCheckedDemam.value
                            if(isCheckedDemam.value){
                                mRememberDemam.value = "Demam"
                            }else{
                                mRememberDemam.value = ""
                            }
                        }
                            .fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedDemam.value,
                                onCheckedChange = {
                                    isCheckedDemam.value = it
                                    if(isCheckedDemam.value){
                                        mRememberDemam.value = "Demam"
                                    }else{
                                        mRememberDemam.value = ""
                                    }
                                                  },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Demam",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedPilek.value = !isCheckedPilek.value
                            if(isCheckedPilek.value){
                                mRememberPilek.value = "Pilek & Hidung Tersumbat"
                            }else{
                                mRememberPilek.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedPilek.value,
                                onCheckedChange = {
                                    isCheckedPilek.value = it
                                    if(isCheckedPilek.value){
                                        mRememberPilek.value = "Pilek & Hidung Tersumbat"
                                    }else{
                                        mRememberPilek.value = ""
                                    }},
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Pilek & Hidung Tersumbat",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedNyeriOtot.value = !isCheckedNyeriOtot.value
                            if(isCheckedNyeriOtot.value){
                                mRememberNyeriOtot.value = "Nyeri Otot"
                            }else{
                                mRememberNyeriOtot.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedNyeriOtot.value,
                                onCheckedChange = {
                                    isCheckedNyeriOtot.value = it
                                    if(isCheckedNyeriOtot.value){
                                        mRememberNyeriOtot.value = "Nyeri Otot"
                                    }else{
                                        mRememberNyeriOtot.value = ""
                                    }},
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Nyeri Otot",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedSesak.value = !isCheckedSesak.value
                            if(isCheckedSesak.value){
                                mRemembersesak.value = "Sesak & Kesulitan Bernafas"
                            }else{
                                mRemembersesak.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedSesak.value,
                                onCheckedChange = {
                                    isCheckedSesak.value = it
                                    if(isCheckedSesak.value){
                                        mRemembersesak.value = "Sesak & Kesulitan Bernafas"
                                    }else{
                                        mRemembersesak.value = ""
                                    }},
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Sesak & Kesulitan Bernafas",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedDayaPerasa.value = !isCheckedDayaPerasa.value
                            if(isCheckedDayaPerasa.value){
                                mRememberDayaPerasa.value = "Kehilangan daya perasa & penciuman"
                            }else{
                                mRememberDayaPerasa.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedDayaPerasa.value,
                                onCheckedChange = {
                                    isCheckedDayaPerasa.value = it
                                    if(isCheckedDayaPerasa.value){
                                        mRememberDayaPerasa.value = "Kehilangan daya perasa & penciuman"
                                    }else{
                                        mRememberDayaPerasa.value = ""
                                    } },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Kehilangan daya perasa & penciuman",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedMuntahDiare.value = !isCheckedMuntahDiare.value
                            if(isCheckedMuntahDiare.value){
                                mRememberMuntahDiare.value = "Muntah atau diare"
                            }else{
                                mRememberMuntahDiare.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedMuntahDiare.value,
                                onCheckedChange = {
                                    isCheckedMuntahDiare.value = it
                                    if(isCheckedMuntahDiare.value){
                                        mRememberMuntahDiare.value = "Muntah atau diare"
                                    }else{
                                        mRememberMuntahDiare.value = ""
                                    }},
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Muntah atau diare",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedBatuk.value = !isCheckedBatuk.value
                            if(isCheckedBatuk.value){
                                mRememberBatuk.value = "Batuk"
                            }else{
                                mRememberBatuk.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedBatuk.value,
                                onCheckedChange = {
                                    isCheckedBatuk.value = it
                                    if(isCheckedBatuk.value){
                                        mRememberBatuk.value = "Batuk"
                                    }else{
                                        mRememberBatuk.value = ""
                                    }},
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Batuk",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedNyeriTenggorokan.value = !isCheckedNyeriTenggorokan.value
                            if(isCheckedNyeriTenggorokan.value){
                                mRememberNyeriTenggorokan.value = "Nyeri Tenggorokan"
                            }else{
                                mRememberNyeriTenggorokan.value = ""
                            }
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedNyeriTenggorokan.value,
                                onCheckedChange = {
                                    isCheckedNyeriTenggorokan.value = it
                                    if(isCheckedNyeriTenggorokan.value){
                                        mRememberNyeriTenggorokan.value = "Nyeri Tenggorokan"
                                    }else{
                                        mRememberNyeriTenggorokan.value = ""
                                    } },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Nyeri Tenggorokan",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedTidakAda.value = !isCheckedTidakAda.value
                            isCheckedDemam.value = false
                            isCheckedPilek.value = false
                            isCheckedNyeriOtot.value = false
                            isCheckedSesak.value = false
                            isCheckedDayaPerasa.value = false
                            isCheckedMuntahDiare.value = false
                            isCheckedBatuk.value = false
                            isCheckedNyeriTenggorokan.value = false
                            if(isCheckedTidakAda.value){
                                mRememberTidakAda.value = "Tidak Ada"
                            }else{
                                mRememberTidakAda.value = ""
                            }
                            mRememberDemam.value = ""
                            mRememberPilek.value = ""
                            mRememberNyeriOtot.value = ""
                            mRemembersesak.value = ""
                            mRememberDayaPerasa.value = ""
                            mRememberMuntahDiare.value = ""
                            mRememberBatuk.value = ""
                            mRememberNyeriTenggorokan.value = ""
                        }.fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedTidakAda.value,
                                onCheckedChange = {
                                    isCheckedTidakAda.value = it
                                    isCheckedDemam.value = false
                                    isCheckedPilek.value = false
                                    isCheckedNyeriOtot.value = false
                                    isCheckedSesak.value = false
                                    isCheckedDayaPerasa.value = false
                                    isCheckedMuntahDiare.value = false
                                    isCheckedBatuk.value = false
                                    isCheckedNyeriTenggorokan.value = false
                                    if(isCheckedTidakAda.value){
                                        mRememberTidakAda.value = "Tidak Ada"
                                    }else{
                                        mRememberTidakAda.value = ""
                                    }
                                    mRememberDemam.value = ""
                                    mRememberPilek.value = ""
                                    mRememberNyeriOtot.value = ""
                                    mRemembersesak.value = ""
                                    mRememberDayaPerasa.value = ""
                                    mRememberMuntahDiare.value = ""
                                    mRememberBatuk.value = ""
                                    mRememberNyeriTenggorokan.value = "" },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Tidak Ada",modifier = m.padding(start = 8.dp))
                        }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "5. Tinggal bersama yang terdiagnosa covid-19?")
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedBersama.value = !isCheckedBersama.value
                            if(isCheckedBersama.value){
                                mRememberBersama.value = "Tinggal bersama yang terdiagnosa coivd-19"
                            }else{
                                mRememberBersama.value = ""
                            }
                        }
                            .fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedBersama.value,
                                onCheckedChange = {
                                    isCheckedBersama.value = it
                                    if(isCheckedBersama.value){
                                        mRememberBersama.value = "Tinggal bersama yang terdiagnosa coivd-19"
                                    }else{
                                        mRememberBersama.value = ""
                                    }
                                },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Tinggal bersama yang terdiagnosa coivd-19",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedKontak.value = !isCheckedKontak.value
                            if(isCheckedKontak.value){
                                mRememberKontak.value = "Kontak erat dengan penderita covid-19 / radius 1 meter minimal lama 15 menit"
                            }else{
                                mRememberKontak.value = ""
                            }
                        }
                            .fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedKontak.value,
                                onCheckedChange = {
                                    isCheckedKontak.value = it
                                    if(isCheckedKontak.value){
                                        mRememberKontak.value = "Kontak erat dengan penderita covid-19 / radius 1 meter minimal lama 15 menit"
                                    }else{
                                        mRememberKontak.value = ""
                                    }
                                },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Kontak erat dengan penderita covid-19 / radius 1 meter minimal lama 15 menit",modifier = m.padding(start = 8.dp))
                        }
                        Spacer(modifier = m.height(10.dp))
                        Row(modifier= m.clickable {
                            isCheckedTidakTerpapar.value = !isCheckedTidakTerpapar.value
                            if(isCheckedTidakTerpapar.value){
                                mRememberKontak.value = "Kontak erat dengan penderita covid-19 / radius 1 meter minimal lama 15 menit"
                            }else{
                                mRememberKontak.value = ""
                            }
                        }
                            .fillMaxWidth()
                        ){
                            Checkbox(
                                checked = isCheckedTidakTerpapar.value,
                                onCheckedChange = {
                                    isCheckedKontak.value = it
                                    if(isCheckedKontak.value){
                                        mRememberKontak.value = "Tidak terpapar"
                                    }else{
                                        mRememberKontak.value = ""
                                    }
                                },
                                enabled = true,
                                colors = CheckboxDefaults.colors(BluePrimary)
                            )
                            Text(text = "Tidak Terpapar",modifier = m.padding(start = 8.dp))
                        }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Card(
                    shape = RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    ),
                    elevation = 0.dp,
                    modifier = m.fillMaxWidth()
                ){
                    Column(modifier = m.padding(10.dp)) {
                        Text(text = "6. Apakah anda bekerja atau berada di fasilitas kesehatan yang merawat pasien coivd-19 dalam 14 hari terakhir?")
                        Spacer(modifier = m.height(10.dp))
                        soal5.forEach { item->Row{
                            RadioButton(
                                selected = mRememberSoal5.value == item,
                                onClick = { mRememberSoal5.value = item},
                                enabled = true,
                                colors = RadioButtonDefaults.colors(selectedColor = BluePrimary)
                            )
                            Text(text = item,modifier = m.padding(start = 8.dp))
                        } }
                    }
                }
                Spacer(modifier = m.height(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = m.fillMaxWidth().padding(10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Submit",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        ),
                        modifier = m.padding(10.dp))
                }
            }
        }
        footer {

        }
    }
}