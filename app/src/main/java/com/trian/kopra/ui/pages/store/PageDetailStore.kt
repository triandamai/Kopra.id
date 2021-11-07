package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.CoroutineScope

/**
 * Persistence Class
 * Author Trian Damai
 * Created by Trian Damai
 * 30/10/2021
 */
@Composable
fun PageDetailStore (
    modifier:Modifier=Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getDetailMyStore()
        mainViewModel.getProductByStoreAsOwner()
    }
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("User",style=TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value=TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    ))
                    Spacer(modifier = modifier.width(10.dp))
                    Card(
                        elevation = 0.dp,
                        shape = CircleShape,
                        modifier = modifier
                            .width(40.dp)
                            .height(40.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.sendsucces),"",
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        },
        bottomBar = {},
        backgroundColor = LightBackground,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
        ){
            Card(
                modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                backgroundColor = GreenPrimary
            ) {
                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column() {
                        Text(
                            text = "Toko Ediwijaya",
                            color = Color.White,
                            style = TextStyle().mediaQuery(
                                Dimensions.Width lessThan 400.dp,
                                value = TextStyle(
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            ),
                            maxLines = 2,
                        )
                        Spacer(modifier = modifier.height(20.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                        ) {
                            Text(
                                text = "Kirim pesan",
                                color = GreenPrimary
                            )
                        }
                    }
                    Card(
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 1.dp,
                            color = LightBackground
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.sendsucces),
                            contentDescription ="",
                            contentScale = ContentScale.Crop,
                            modifier = modifier.mediaQuery(
                                Dimensions.Width lessThan 400.dp,
                                modifier = modifier
                                    .height(80.dp)
                                    .width(80.dp)
                            )
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(30.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
            ){
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painterResource(id = R.drawable.ic_baseline_person_pin_circle_24),
                        "",
                        tint = Color.Unspecified,
                        modifier = modifier.mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier
                                .height(40.dp)
                                .width(40.dp)
                        )
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Column() {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ){
                            Text(
                                text = "Alamat",
                                style = TextStyle().mediaQuery(
                                    Dimensions.Width lessThan  400.dp,
                                    value = TextStyle(
                                        color = ColorGray,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium
                                    ) )
                            )
                            Icon(
                                Octicons.ChevronDown24,"",
                                modifier = modifier.mediaQuery(
                                    Dimensions.Width lessThan 400.dp,
                                    modifier = modifier.width(18.dp)
                                )
                            )
                        }
                        Text(
                            text = "Jl. Otto Iskandar Dinata No. 69",
                            style = TextStyle().mediaQuery(
                                Dimensions.Width lessThan  400.dp,
                                value = TextStyle(
                                    color = GreenPrimary,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                ) )
                        )
                    }
                }
            }
            Spacer(modifier = modifier.height(30.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Produk",
                    style = TextStyle().mediaQuery(Dimensions.Width lessThan  400.dp,
                        value = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        ))
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Lihat semua",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan  400.dp,
                            value = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = GreenPrimary
                            ))
                    )
                    Icon(Octicons.ChevronRight24,"",
                        tint= GreenPrimary,
                        modifier = modifier.mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier
                                .width(20.dp)
                                .height(20.dp)
                        )
                    )
                }
            }
            Spacer(modifier = modifier.height(15.dp))
            LazyColumn(
                content = {
                          items(
                              count = 2,
                              itemContent = {index->
                                  Card(
                                      modifier = modifier
                                          .fillMaxWidth()
                                          .padding(bottom = 15.dp),
                                      shape = RoundedCornerShape(10.dp),
                                      elevation = 0.dp,
                                  ){
                                      Row(
                                          modifier = modifier
                                              .fillMaxWidth()
                                              .padding(10.dp),
                                          horizontalArrangement = Arrangement.SpaceBetween,
                                          verticalAlignment = Alignment.CenterVertically
                                      ){
                                          Column() {
                                              Text(
                                                  text = "Motor Viar",
                                                  style = TextStyle().mediaQuery(
                                                      Dimensions.Width lessThan 400.dp,
                                                      value =TextStyle(
                                                          fontWeight = FontWeight.Bold,
                                                          fontSize = 16.sp
                                                      )
                                                  )
                                              )
                                              Text(
                                                  text = "Jl. Otto Iskandar Dinata No.69",
                                                  style = TextStyle().mediaQuery(
                                                      Dimensions.Width lessThan 400.dp,
                                                      value =TextStyle(
                                                          fontSize = 16.sp,
                                                          color = ColorGray
                                                      )
                                                  )
                                              )
                                              Text(
                                                  text = "Rp. 100.000",
                                                  style = TextStyle().mediaQuery(
                                                      Dimensions.Width lessThan 400.dp,
                                                      value =TextStyle(
                                                          fontSize = 16.sp,
                                                          color = ColorGray
                                                      )
                                                  )
                                              )
                                          }
                                          Card(
                                              shape = RoundedCornerShape(10.dp),
                                              border = BorderStroke(
                                                  width = 1.dp,
                                                  color = LightBackground
                                              ),
                                              elevation = 0.dp
                                          ){
                                              Box(
                                                  modifier = modifier
                                                      .background(color = Color.Black),
                                              ){
                                                  Image(
                                                      painter = painterResource(id = R.drawable.motor),
                                                      contentDescription = "", contentScale = ContentScale.Crop,
                                                      alpha = 0.9f,
                                                      modifier = modifier.mediaQuery(
                                                          Dimensions.Width lessThan 400.dp,
                                                          modifier = modifier
                                                              .height(80.dp)
                                                              .width(80.dp)
                                                      )
                                                  )
                                              }
                                          }
                                      }
                                  }
                              }
                          )
                },
            )
        }
    }
}


@Preview
@Composable
fun PreviewPageDetailStore(){
    PageDetailStore(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}