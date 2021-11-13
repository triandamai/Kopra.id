package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.libraries.maps.model.LatLng
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.formatHoursMinute
import com.trian.common.utils.utils.formatReadableDate
import com.trian.component.cards.CardShowGoogleMap
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.StatusTransaction
import com.trian.domain.models.UnitProduct
import com.trian.domain.models.getUnit
import com.trian.domain.models.network.GetStatus
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft24
import com.trian.kopra.R
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageDetailOrderSeller(
    modifier:Modifier= Modifier,
     scrollState:ScrollState = rememberScrollState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    val transactionId:String = navHostController.currentBackStackEntry?.arguments?.getString("slug") ?:""
    val detailTransaction by mainViewModel.detailTransaction
    LaunchedEffect(Unit){
        mainViewModel.getDetailTransaction(transactionId)
    }

    Scaffold(
        topBar={
           Row(
               modifier = modifier
                   .fillMaxWidth()
                   .padding(10.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,
           ){
              IconToggleButton(checked = false, onCheckedChange = {
                  navHostController.popBackStack()
              }) {
                  Icon(Octicons.ArrowLeft24,"")
              }
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
                when(detailTransaction){
                    is GetStatus.HasData -> {

                        when(detailTransaction.data?.status){
                            StatusTransaction.WAITING -> {
                                Button(
                                    onClick ={
                                        mainViewModel.confirmTransactionFromSeller(
                                            detailTransaction.data?.uid ?: "",
                                        StatusTransaction.PROGRESS){
                                            success, message ->
                                            if(success){

                                            }
                                        }
                                    },
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Text(
                                        text =  "Terima Pesanan",
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
                            }
                            StatusTransaction.PROGRESS -> {
                                Button(
                                    onClick ={
                                        mainViewModel.confirmTransactionFromSeller(
                                            detailTransaction.data?.uid ?: "",
                                            StatusTransaction.PICKUP){
                                                success, message ->
                                            if(success){

                                            }
                                        }
                                    },
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Text(
                                        text =  "Penjemputan",
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
                            }
                            StatusTransaction.PICKUP -> {
                                Button(
                                    onClick ={
                                        mainViewModel.finishTransactionFromSeller(
                                            detailTransaction.data?.uid ?: ""
                                        ){success, message ->
                                            if(success){

                                            }
                                        }
                                    },
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                                    shape = RoundedCornerShape(8.dp),
                                ) {
                                    Text(
                                        text =  "Pesanan Selesai",
                                        style = MaterialTheme.typography.h1.copy(
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 14.sp,
                                                letterSpacing = 1.sp,
                                                color = Color.White

                                        ),
                                        modifier = modifier.padding(8.dp)
                                    )
                                }
                            }
                            StatusTransaction.FINISH -> {

                            }
                            StatusTransaction.CANCELED -> {

                            }
                            StatusTransaction.UNKNOWN -> {

                            }
                            StatusTransaction.NO_DATA -> {

                            }
                            null -> {

                            }
                        }

                    }
                    is GetStatus.Idle -> {}
                    is GetStatus.Loading -> {}
                    is GetStatus.NoData -> {}
                }
                Spacer(modifier = modifier.height(8.dp))
                Button(
                    onClick ={
                             when(detailTransaction){
                                 is GetStatus.HasData -> {navHostController.navigate("${Routes.CHATSCREEN}/${detailTransaction.data?.uid}")}
                                 else->{}
                             }
                    },
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
                when(detailTransaction){
                    is GetStatus.HasData -> {
                        CardShowGoogleMap(location = LatLng(
                            detailTransaction.data?.store?.latitude ?: 0.0,
                            detailTransaction.data?.store?.longitude ?: 0.0
                        ),
                            name = "")}
                    is GetStatus.Idle -> {}
                    is GetStatus.Loading -> {}
                    is GetStatus.NoData -> {}
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ){
                CoilImage(
                    modifier = modifier
                        .clip(RoundedCornerShape(12.dp))
                        .height(50.dp)
                        .width(50.dp)
                        .clickable(
                            onClick = {}
                        ),
                    imageModel = detailTransaction.data?.store?.logo ?: "",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Crop,
                    // shows an image with a circular revealed animation.
                    circularReveal = CircularReveal(duration = 250),
                    // shows a placeholder ImageBitmap when loading.
                    placeHolder = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_profile),
                    // shows an error ImageBitmap when the request failed.
                    error = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_doctor)
                )

                Spacer(modifier = modifier.width(10.dp))
                Column() {
                    Text(
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.store?.storeName ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading -> ""
                            is GetStatus.NoData -> ""
                        },
                        style =  MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp,
                            letterSpacing = 0.1.sp,
                            color = ColorGray

                        )
                    )
                    Text(
                        text = detailTransaction.data?.store?.phoneNumber ?: "",
                        style = MaterialTheme.typography.h1.copy(
                            fontSize = 14.sp,
                            letterSpacing = 0.1.sp,
                            fontWeight = FontWeight.Bold,

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
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.store?.addressStore ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading -> ""
                            is GetStatus.NoData -> ""
                        },
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
                        text = "Produk",
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
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.detail?.productName ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading -> ""
                            is GetStatus.NoData -> ""
                        },
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
                        text = when(detailTransaction){
                            is GetStatus.HasData -> "${detailTransaction.data?.totalPrice ?: ""}/${getUnit(detailTransaction.data?.detail?.unit ?: UnitProduct.UNKNOWN)}"
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading -> ""
                            is GetStatus.NoData -> ""
                        },
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
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.createdAt?.formatReadableDate() ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading ->""
                            is GetStatus.NoData -> ""
                        },
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
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.createdAt?.formatHoursMinute() ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading ->""
                            is GetStatus.NoData -> ""
                        },
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

            Spacer(modifier = modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ){
                Column(horizontalAlignment = Alignment.Start){
                    Text(
                        text = "Status Pemesanan",
                        style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    )
                    Text(
                        text = when(detailTransaction){
                            is GetStatus.HasData -> detailTransaction.data?.status.toString() ?: ""
                            is GetStatus.Idle -> ""
                            is GetStatus.Loading -> ""
                            is GetStatus.NoData -> ""
                        },
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