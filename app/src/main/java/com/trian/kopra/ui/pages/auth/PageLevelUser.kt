package com.trian.kopra.ui.pages.auth

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.LevelUser
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageLevelUser(
    m:Modifier = Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope

){
    val listStatus = listOf(LevelUser.TENANT,LevelUser.COLLECTOR,LevelUser.FARMER)
    var sizeBorder by remember{ mutableStateOf(0.dp)}
    var colorBorder by remember{ mutableStateOf(Color.Unspecified)}
    var leverUser by mainViewModel.userLevel

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = m
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Text(
                    text="Kopra.Id",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value=MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
            }
        }
    ) {
        Column(
            modifier = m.padding(
                20.dp
            ),
        ) {
            Text(
                text = "Pilih status",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(10.dp))
            Text(
                text = "Sebelum menggunakan fitur-fitur di aplikasi Kopra.Id, Silahkan konfirmasi status user anda",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(20.dp))
            LazyColumn(content = {
                items(count = listStatus.size,itemContent = {index->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = m
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .selectable(
                                selected = leverUser == listStatus[index],
                                onClick = {
                                    leverUser = listStatus[index]
                                }
                            ),
                        elevation = 0.dp,
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        border = BorderStroke(width = 1.dp,color = BluePrimary )
                    ) {
                        Row(
                            modifier = m
                                .padding(12.dp)
                        ){
                            RadioButton(
                                selected = leverUser == listStatus[index],
                                onClick = { leverUser = listStatus[index] },
                                enabled = true,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = BluePrimary,
                                ),
                            )
                            Text(
                                text = when(listStatus[index]){
                                    LevelUser.TENANT -> "Saya Penyewa Kendaraan"
                                    LevelUser.COLLECTOR -> "Saya Pengepul"
                                    LevelUser.FARMER -> "Saya Petani"
                                    LevelUser.UNKNOWN -> ""
                                },modifier = m.padding(start = 8.dp))
                        }
                    }
                })
            })

            Spacer(modifier = m.height(20.dp))
            Button(
                onClick ={
                         navHostController.navigate(Routes.UPDATE_PROFILE)
                },
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Konfirmasi",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        )
                    ),
                    modifier = m.padding(10.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewPageLevelUser(){
    PageLevelUser(mainViewModel = viewModel(),navHostController = rememberNavController(),scope = rememberCoroutineScope())
}