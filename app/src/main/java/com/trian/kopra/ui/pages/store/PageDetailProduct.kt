package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.trian.kopra.R
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24

@Composable
fun PageDetailProduct(
    modifier:Modifier=Modifier,
    nav:NavHostController
){
    val scrollState = rememberScrollState()
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
                Icon(
                    Octicons.ArrowLeft24,"",
                )
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
                    onClick = { nav.navigate(Routes.CHECKOUT) },
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
            Text(
                "Viar",
                style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                    value = MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp
                    )
                )
            )
            Spacer(modifier = modifier.height(5.dp))
            Text(
                "Rp 10.000/Hari",
                style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                    value = MaterialTheme.typography.h1.copy(
                        fontSize = 20.sp,
                        letterSpacing = 0.1.sp,
                        color = GreenPrimary
                    )
                )
            )
            Spacer(modifier = modifier.height(15.dp))
            Text(
                "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.",
                style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                    value = MaterialTheme.typography.h1.copy(
                        fontSize = 14.sp,
                        letterSpacing = 0.1.sp,
                        color = ColorGray
                    )
                )
            )
        }
    }
}