package app.trian.kopra.ui.pages.transaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery

import app.trian.kopra.R
import com.trian.component.Routes

@Composable
fun PageOrderInformation(
    modifier:Modifier=Modifier,
    nav:NavHostController
){
    val isSuccess by remember { mutableStateOf(false) }
    val transactionId:String = ( nav.currentBackStackEntry?.arguments?.get("slug") ?: "") as String

    Scaffold(
        bottomBar={
            Column(
                modifier = modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    onClick ={
                             nav.navigate("${Routes.CHATSCREEN}/${transactionId}")
                    },
                    modifier = modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = "Hubungi penjual",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
                TextButton(
                    onClick = {
                        nav.navigate(Routes.Dashboard.LIST_TRANSACTION)
                    }
                ) {
                    Text(
                        text = "Lihat orderanmu",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = ColorGray
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
            }

        }
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(20.dp)
            ) {
                Image(painter = painterResource(
                    id = if(isSuccess)R.drawable.ic_order_confirmed else R.drawable.ic_waiting_confirmation),
                    contentDescription = "",
                    modifier
                        .fillMaxWidth()
                        .mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier.height(150.dp)
                        ),
                )
                Spacer(modifier = modifier.height(50.dp))
                Text(
                    text = if(isSuccess)"Pesanan terkonfirmasi!" else "Menunggu konfirmasi",
                    style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp
                        )
                    ),
                    textAlign = TextAlign.Center,
                    modifier = modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = "Pesanan kamu sudah berhasil dan " +
                            "${if(isSuccess)
                                "sudah di konfirmasi oleh pengepul/penyewa" 
                            else
                                "sedang dalam proses konfirmasi pengepul/penyewa."} ",
                    style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray
                        )
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}