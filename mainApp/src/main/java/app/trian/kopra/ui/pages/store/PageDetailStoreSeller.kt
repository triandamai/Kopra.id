package app.trian.kopra.ui.pages.store

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kopra.R
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarDetailStore
import com.trian.component.cards.CardItemProduct
import com.trian.component.dialog.DialogShowMap
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.network.GetStatus
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
fun PageDetailStoreSeller (
    modifier:Modifier=Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope:CoroutineScope
){
    val myStore by mainViewModel.myStore
    val products by mainViewModel.productList

    var shouldShowMap by remember {
        mutableStateOf(false)
    }

    DialogShowMap(
        show=shouldShowMap,
        latitude= myStore.data?.latitude ?: 0.0,
        longitude= myStore.data?.longitude ?: 0.0
    ) {
        shouldShowMap = false
    }

    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getDetailMyStore()
        mainViewModel.getProductByStoreAsOwner()
    }
    Scaffold(
        topBar = {
           AppBarDetailStore {
               navHostController.popBackStack()
           }
        },
        bottomBar = {
            when(myStore){
                is GetStatus.HasData -> {
                    Row(
                        modifier = modifier.fillMaxWidth().clickable {
                            navHostController.navigate(Routes.ADD_PRODUCT)
                        },
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Tambahkan Produk")
                        IconToggleButton(checked = false,
                            onCheckedChange = {

                            }
                        ) {
                            Icon(imageVector = Octicons.Plus24, contentDescription = "")
                        }
                    }
                }
                is GetStatus.Idle -> {}
                is GetStatus.Loading -> {}
                is GetStatus.NoData -> {

                }
            }

        },
        backgroundColor = LightBackground,
    ) {
        when(myStore){
            is GetStatus.HasData -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ){

                    LazyColumn(
                        content = {
                            item{
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
                                                text = myStore.data?.storeName ?: "",
                                                color = Color.White,
                                                style = TextStyle(
                                                        fontSize = 25.sp,
                                                        fontWeight = FontWeight.Bold,

                                                ),
                                                maxLines = 2,
                                            )
                                               Text(
                                                   text = when(myStore.data?.haveVehicle){
                                                       true -> "Memiliki Kendaran"
                                                       false -> "Tidak Memiliki Kendaran"
                                                       null -> ""
                                                   },
                                                   color = Color.White,
                                                   style = TextStyle(
                                                       fontSize = 16.sp,
                                                       fontWeight = FontWeight.Normal,
                                                   ),
                                                   maxLines = 2,
                                               )
                                            Text(
                                                text = myStore.data?.phoneNumber ?: "",
                                                color = Color.White,
                                                style = TextStyle(
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Normal
                                                ),
                                                maxLines = 2,
                                            )
                                            Row(
                                                modifier = modifier
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .fillMaxWidth(0.6f)
                                                    .clickable {
                                                        shouldShowMap = true
                                                    },
                                                verticalAlignment = Alignment.CenterVertically
                                            ){
                                                Icon(
                                                    imageVector = Octicons.Location24,
                                                    "",
                                                    tint = Color.White
                                                )
                                                Spacer(modifier = modifier.width(10.dp))
                                                Text(
                                                    text = myStore.data?.addressStore ?: "",
                                                    style = TextStyle(
                                                        color = Color.White,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Medium
                                                    )
                                                )
                                            }

                                            Spacer(modifier = modifier.height(20.dp))
                                            Button(
                                                onClick = {
                                                          navHostController.navigate(Routes.UPDATE_TOKO)
                                                },
                                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                            ) {
                                                Text(
                                                    text = "Ubah Toko",
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
                                            CoilImage(
                                                modifier = modifier
                                                    .clip(RoundedCornerShape(12.dp))
                                                    .height(80.dp)
                                                    .width(80.dp)
                                                    .clickable(
                                                        onClick = {

                                                        }
                                                    ),
                                                imageModel = myStore.data?.logo ?: "",
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
                                }
                                Spacer(modifier = modifier.height(30.dp))
                            }
                            item{
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
                            }
                            when(products){
                                is GetStatus.HasData -> {
                                    items(
                                        count = products.data?.size ?: 0,
                                        itemContent = {
                                                index->
                                            CardItemProduct(
                                                index=index,
                                                product = products.data!![index],
                                                onDetail = {index, product ->
                                                    navHostController.navigate("${Routes.DETAIL_PRODUCT_SELLER}/${product.uid}")
                                                },
                                                onDelete = {index, product ->  },
                                                onEdit = {index, product ->  }
                                            )
                                        }
                                    )
                                }
                                is GetStatus.Idle -> {

                                }
                                is GetStatus.Loading -> {

                                }
                                is GetStatus.NoData -> {

                                }
                            }
                        },
                    )
                }
            }
            is GetStatus.Idle -> {}
            is GetStatus.Loading -> {}
            is GetStatus.NoData -> {
              Column(
                  modifier = modifier
                      .background(Color.White)
                      .fillMaxWidth()
                      .fillMaxHeight(),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  Image(
                      painter = painterResource(id = com.trian.component.R.drawable.ic_no_store),
                      modifier=modifier.size(140.dp),
                      contentScale= ContentScale.FillWidth,
                      contentDescription = ""
                  )
                  Text(
                      text = "Anda belum mempunyai toko",
                      style = TextStyle(
                          fontSize = 20.sp,
                          fontWeight = FontWeight.SemiBold
                      ),
                      textAlign = TextAlign.Center
                  )
                  Spacer(modifier = modifier.height(16.dp))
                  Text(
                      text = stringResource(R.string.belum_punya_toko_subtitle),
                      style = TextStyle(
                          fontSize = 16.sp,
                          fontWeight = FontWeight.Normal
                      ),
                      textAlign = TextAlign.Center
                  )
                  Spacer(modifier = modifier.height(20.dp))
                  Button(onClick = {
                      navHostController.navigate(Routes.CREATE_TOKO)
                  }) {
                      Text(text = "Buat Toko")
                  }
              }
            }
        }
    }
}


@Preview
@Composable
fun PreviewPageDetailMyStore(){
    PageDetailStoreSeller(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}