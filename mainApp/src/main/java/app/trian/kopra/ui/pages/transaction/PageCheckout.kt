package app.trian.kopra.ui.pages.transaction

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import app.trian.kopra.MainViewModel
import com.google.android.gms.maps.model.LatLng
import com.trian.component.Routes
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.cards.CardShowGoogleMap
import com.trian.component.dialog.DialogConfirmationCheckout
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.formatHoursMinute
import com.trian.component.utils.formatReadableDate
import com.trian.component.utils.getTodayTimeStamp
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.model.UnitProduct
import com.trian.data.model.getUnit
import com.trian.data.model.network.GetStatus
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageCheckout(
    modifier:Modifier=Modifier,
    scrollState:ScrollState = rememberScrollState(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){

    val detailStore by mainViewModel.detailStore
    val detailProduct by mainViewModel.detailProduct
    var product by mainViewModel.transactionProduct
    var store by mainViewModel.transactionStore

    var shouldShowConfirmation by remember {
        mutableStateOf(false)
    }



    fun procesCheckout(){
        mainViewModel.createNewTransaction {
            success,id->
            if(success) {
                navHostController.navigate("${Routes.ORDER_INFORMATION}/${id}")
            }
        }

    }
    when(detailStore){
        is GetStatus.HasData ->{
            detailStore.data?.let {
                store = it
            }
        }
        else->{}
    }

    when(detailProduct){
        is GetStatus.HasData ->{
            detailProduct.data?.let {
                product = it
            }
        }
        else -> {

        }

    }

    DialogConfirmationCheckout(
        show=shouldShowConfirmation,
        storeName = detailStore.data?.storeName ?: "",
        productName = detailProduct.data?.productName ?:"",
        shouldRentVehicle = detailStore.data?.haveVehicle ?: false,
        onCancel = {shouldShowConfirmation=false }) {
        procesCheckout()
        shouldShowConfirmation = false
    }

    LaunchedEffect(key1 = scaffoldState, block ={

    })

    Scaffold (
        topBar = {
            AppBarFormStore(title="Checkout") {
                navHostController.popBackStack()
            }
        },
        bottomBar = {
            Button(
                onClick ={
                         shouldShowConfirmation = true
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Pesan",
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
        }
    ){
        Column (
            modifier= modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(10.dp)
        ){
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp,
                border = BorderStroke(
                    0.5.dp,
                    ColorGray,
                )
            ){
                Column(
                    modifier= modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Informasi lengkap",
                        style =  MaterialTheme.typography.h1.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp

                        )
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.Person,"")
                        Spacer(modifier = modifier.width(10.dp))
                        Column{
                            Text(
                                text = "Nama Toko",
                                style = MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            when(detailStore){
                                is GetStatus.HasData ->{

                                    Text(
                                        text = detailStore.data?.storeName ?: "",
                                        style = MaterialTheme.typography.h1.copy(
                                                fontSize = 14.sp,
                                                letterSpacing = 0.1.sp,
                                                fontWeight = FontWeight.Bold

                                        )
                                    )
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }

                        }
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.PhoneAndroid,"")
                        Spacer(modifier = modifier.width(10.dp))
                        Column{
                            Text(
                                text = "No. telepon",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            when(detailStore){
                                is GetStatus.HasData ->{
                                    Text(
                                        text = detailStore.data?.phoneNumber ?: "",
                                        style =  MaterialTheme.typography.h1.copy(
                                                fontSize = 14.sp,
                                                letterSpacing = 0.1.sp,
                                                fontWeight = FontWeight.Bold

                                        )
                                    )
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }

                        }
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Icon(Icons.Filled.PhoneAndroid,"")
                        Spacer(modifier = modifier.width(10.dp))
                        Column{
                            Text(
                                text = "Kendaraan",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            when(detailStore){
                                is GetStatus.HasData ->{

                                    Text(
                                        text = if((detailStore.data?.haveVehicle == true)){
                                            "Toko akan mengambil ke rumah anda"
                                        }else{
                                            "Toko tidak menyediakan kendaraan"
                                        },
                                        style =  MaterialTheme.typography.h1.copy(
                                                fontSize = 14.sp,
                                                letterSpacing = 0.1.sp,
                                                fontWeight = FontWeight.Bold

                                        )
                                    )
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }

                        }
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Column{
                        Text(
                            text = "Alamat",
                            style =  MaterialTheme.typography.h1.copy(
                                    fontSize = 14.sp,
                                    color = ColorGray,
                                    letterSpacing = 0.1.sp

                            )
                        )
                        when(detailStore){
                            is GetStatus.HasData ->{
                                Text(
                                    text = detailStore.data?.addressStore ?: "",
                                    style =  MaterialTheme.typography.h1.copy(
                                            fontSize = 14.sp,
                                            letterSpacing = 0.1.sp,
                                            fontWeight = FontWeight.Bold

                                    )
                                )
                            }
                            is GetStatus.Idle -> {

                            }
                            is GetStatus.Loading -> {

                            }
                            is GetStatus.NoData -> {

                            }
                        }

                        Spacer(modifier = modifier.height(5.dp))
                        Card(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            elevation = 0.dp,
                            border = BorderStroke( 0.5.dp, ColorGray)
                        ){
                            when(detailStore){
                                is GetStatus.HasData ->{
                                    CardShowGoogleMap(location = LatLng(detailStore.data?.latitude ?: 0.0,detailStore.data?.longitude ?: 0.0), name =detailStore.data?.storeName ?:"")
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }

                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(10.dp))
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 0.dp,
                border = BorderStroke(
                    0.5.dp,
                    ColorGray,
                )
            ){
                Column(
                    modifier= modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Order info",
                        style =  MaterialTheme.typography.h1.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp

                        )
                    )
                    Spacer(modifier = modifier.height(20.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Column{
                            Text(
                                text = "Catatan",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            Text(
                                text = "Kosong",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        letterSpacing = 0.1.sp,
                                        fontWeight = FontWeight.Bold

                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(Icons.Filled.Edit,"")
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ){
                        Column{
                            Text(
                                text = "Harga",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            when(detailProduct){
                                is GetStatus.HasData ->{
                                    Text(
                                        text = "Rp ${detailProduct.data?.price} / ${getUnit(detailProduct.data?.unit ?: UnitProduct.KG)}",
                                        style =  MaterialTheme.typography.h1.copy(
                                                fontSize = 14.sp,
                                                letterSpacing = 0.1.sp,
                                                fontWeight = FontWeight.Bold

                                        )
                                    )
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }

                        }
                        Column{
                            Text(
                                text = "Admin",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            Text(
                                text = "Rp 2.000",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        letterSpacing = 0.1.sp,
                                        fontWeight = FontWeight.Bold

                                )
                            )
                        }
                    }
                    Spacer(modifier = modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = modifier.fillMaxWidth()
                    ){
                        Column{
                            Text(
                                text = "Hari",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            Text(
                                text = getTodayTimeStamp().formatReadableDate(),
                                style = MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        letterSpacing = 0.1.sp,
                                        fontWeight = FontWeight.Bold

                                )
                            )
                        }
                        Column{
                            Text(
                                text = "Jam",
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        color = ColorGray,
                                        letterSpacing = 0.1.sp

                                )
                            )
                            Text(
                                text = getTodayTimeStamp().formatHoursMinute(),
                                style =  MaterialTheme.typography.h1.copy(
                                        fontSize = 14.sp,
                                        letterSpacing = 0.1.sp,
                                        fontWeight = FontWeight.Bold

                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(100.dp))
        }
    }
}