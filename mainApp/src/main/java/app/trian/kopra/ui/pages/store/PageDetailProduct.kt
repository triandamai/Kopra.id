package app.trian.kopra.ui.pages.store

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.LevelUser
import com.trian.domain.models.network.GetStatus
import app.trian.kopra.R
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
    var currentUser by mainViewModel.currentUser

    LaunchedEffect(key1 = scaffoldState, block = {
        mainViewModel.getCurrentUser { hasUser, user ->
            currentUser = user
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

            if(currentUser?.levelUser == LevelUser.TENANT || currentUser?.levelUser == LevelUser.COLLECTOR){

            }else{
                Box(
                    modifier= modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Button(
                        onClick = {
                            nav.navigate("${Routes.CHECKOUT}/${detailProduct.data?.uid ?: ""}")
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
               .height(250.dp)
                    ,
                shape = RoundedCornerShape(10.dp),
            ){
                when(detailProduct){
                    is GetStatus.HasData -> {
                        Box(
                            modifier.background(
                                Color.Black,
                            )
                        ){
                            CoilImage(
                                modifier = modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .fillMaxWidth()
                                    .clickable(
                                        onClick = {}
                                    ),
                                imageModel = detailProduct.data?.thumbnail ?: "",
                                // Crop, Fit, Inside, FillHeight, FillWidth, None
                                contentScale = ContentScale.Crop,
                                // shows an image with a circular revealed animation.
                                circularReveal = CircularReveal(duration = 250),
                                // shows a placeholder ImageBitmap when loading.
                                placeHolder = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_profile),
                                // shows an error ImageBitmap when the request failed.
                                error = ImageBitmap.imageResource(com.trian.component.R.drawable.dummy_doctor)
                            )

                        }
                    }
                    is GetStatus.Idle -> {}
                    is GetStatus.Loading -> {}
                    is GetStatus.NoData -> {}
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
                        style =  MaterialTheme.typography.h1.copy(
                                fontSize = 20.sp,
                                letterSpacing = 0.1.sp,
                                color = GreenPrimary

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
                        style =  MaterialTheme.typography.h1.copy(
                                fontSize = 14.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray

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