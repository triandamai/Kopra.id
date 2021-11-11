package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import com.trian.kopra.R

@Composable
fun PageDetailOrder(
    modifier:Modifier= Modifier,
){
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    val scrollState = rememberScrollState()
    Scaffold(
        topBar={
           Row(
               modifier = modifier
                   .fillMaxWidth()
                   .padding(10.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
           ){
               Icon(Octicons.ArrowLeft24,"")
               Text(
                   text = "Detail pesanan",
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
        bottomBar = {
            Column(
                modifier = modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick ={ },
                    modifier = modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(
                        text = "Konfirmasi",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = modifier.height(8.dp))
                Button(
                    onClick ={ },
                    modifier = modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = ColorGray
                    )
                ) {
                    Text(
                        text = "Kirim pesan",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp,
                                letterSpacing = 1.sp,
                                color = ColorGray
                            )
                        ),
                        modifier = modifier.padding(8.dp)
                    )
                }

            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(scrollState)
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                elevation = 0.dp,
                border = BorderStroke( 0.5.dp, ColorGray)
            ){
                Text("Ini maps")
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.dummy_doctor), contentDescription = "",
                    modifier = modifier
                        .mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier
                                .height(50.dp)
                                .width(50.dp)
                        )
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = modifier.width(10.dp))
                Column() {
                    Text(
                        text = "Pengepul A",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                    Text(
                        text = "30 Km",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    )
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Alamat",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                    Text(
                        text = "Jl. Desa Desa Desa Desa Desa Desa Desa Desa",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ),
                        modifier = modifier.mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier.width(currentWidth/2)
                        )
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Harga",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                    Text(
                        text = "Rp 14.000/Kg",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ),
                    )
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Column(horizontalAlignment = Alignment.Start){
                    Text(
                        text = "Hari",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                    Text(
                        text = "Senin, 20 feb 2021",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
                }
                Column(horizontalAlignment = Alignment.End){
                    Text(
                        text = "Jam",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                color = ColorGray,
                                letterSpacing = 0.1.sp
                            )
                        )
                    )
                    Text(
                        text = "10:00",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    )
                }
            }
        }
    }
}