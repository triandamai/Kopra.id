package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.LevelUser
import com.trian.domain.models.network.GetStatus
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PageDetailProduct(
    modifier:Modifier=Modifier,
    scrollState:ScrollState = rememberScrollState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    nav:NavHostController,
    scope:CoroutineScope,
){
    val productId:String = (nav.currentBackStackEntry?.arguments?.get("slug") ?: "") as String
    val detailProduct by mainViewModel.detailProduct


    LaunchedEffect(key1 = scaffoldState, block = {
        mainViewModel.getCurrentUser { hasUser, user ->

        }
        mainViewModel.getDetailProduct(productId = productId)
    })
    fun notify(message:String){
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold (
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        color = Color.Transparent
                    ),
            ){
                IconToggleButton(checked = false, onCheckedChange = {
                    nav.popBackStack()
                }) {
                    Icon(
                        Octicons.ArrowLeft24,"",
                    )
                }
                Text("Detail Produk",style= TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp, value = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp
                    )))
                Box(){

                }
            }
        },
        bottomBar = {
            Box(
                modifier= modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Button(
                    onClick = {
                        mainViewModel.getCurrentUser { hasUser, user ->
                            if(hasUser){
                               if(user.levelUser == LevelUser.COLLECTOR || user.levelUser == LevelUser.TENANT){
                                  notify("Penyewa atau Pengepul tidak diperkenankan memesan")
                               }else{

                               }
                                nav.navigate("${Routes.CHECKOUT}/${detailProduct.data?.uid ?: ""}")
                            }else{
                                notify("Masuk terlebih dahulu!!")
                            }
                        }

                    },
                    modifier = modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.Center),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Pesan sekarang",
                        modifier = modifier.padding(10.dp),
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                color = Color.White,
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp
                            )
                        )
                    )
                }
            }
        }
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(scrollState)
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = modifier.height(250.dp)
                    ),
                shape = RoundedCornerShape(10.dp),
            ){
                Box(
                    modifier.background(
                        Color.Black,
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.motor), contentDescription ="",
                        contentScale = ContentScale.Crop,
                        modifier = modifier.fillMaxSize(),
                        alpha = 0.9f
                    )
                }
            }
            Spacer(modifier = modifier.height(10.dp))

            when(detailProduct){
                is GetStatus.HasData -> {
                    Text(
                        detailProduct.data?.productName ?: "",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp
                            )
                        )
                    )
                }
                is GetStatus.Idle -> {}
                is GetStatus.Loading -> {}
                is GetStatus.NoData -> {}
            }

            Spacer(modifier = modifier.height(5.dp))
            when(detailProduct){
                is GetStatus.HasData -> {
                    Text(
                        "Rp ${detailProduct.data?.price}/Hari",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 20.sp,
                                letterSpacing = 0.1.sp,
                                color = GreenPrimary
                            )
                        )
                    )
                }
                is GetStatus.Idle -> {}
                is GetStatus.Loading -> {}
                is GetStatus.NoData -> {}
            }

            Spacer(modifier = modifier.height(15.dp))
            when(detailProduct){
                is GetStatus.HasData -> {
                    Text(
                        detailProduct.data?.description ?:"",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                }
                is GetStatus.Idle -> {}
                is GetStatus.Loading -> {}
                is GetStatus.NoData -> {}
            }

        }
    }
}