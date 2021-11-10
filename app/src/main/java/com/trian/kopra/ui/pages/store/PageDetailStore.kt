package com.trian.kopra.ui.pages.store

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
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.component.cards.CardGoogleMap
import com.trian.component.cards.CardItemProduct
import com.trian.component.dialog.DialogShowMap
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.domain.models.Product
import com.trian.domain.models.network.GetStatus
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
    val detailStore  by mainViewModel.detailStore
    val products  by mainViewModel.productList
    var shouldShowMap by remember {
        mutableStateOf(false)
    }

    DialogShowMap(
        show=shouldShowMap,
        latitude= detailStore.data?.latitude ?: 0.0,
        longitude= detailStore.data?.longitude ?: 0.0
    ) {
        shouldShowMap = false
    }
    LaunchedEffect(key1 = scaffoldState){
        //TODO: get id store from navigation
        val storeId:String = (navHostController.currentBackStackEntry?.arguments?.get("slug")?:"") as String
        mainViewModel.getDetailStore(storeId)
        mainViewModel.getProductByStoreAsConsumer(storeId)
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
                IconToggleButton(checked = false, onCheckedChange = {
                    navHostController.popBackStack()
                }) {
                    Icon(
                        Octicons.ArrowLeft24,"",
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text="User",
                        style=TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
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
        when(detailStore){
            is GetStatus.HasData -> {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
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
                                        .padding(16.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Column {
                                            Text(
                                                text = detailStore.data?.storeName ?: "",
                                                color = Color.White,
                                                style = TextStyle(
                                                        fontSize = 25.sp,
                                                        fontWeight = FontWeight.Bold,
                                                ),
                                                maxLines = 2,
                                            )
                                            Text(
                                                text = detailStore.data?.phoneNumber ?: "",
                                                color = Color.White,
                                                style = TextStyle(
                                                        fontSize = 18.sp,
                                                        fontWeight = FontWeight.Normal,
                                                ),
                                                maxLines = 2,
                                            )

                                            Spacer(modifier = modifier.height(16.dp))
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
                                                    text = detailStore.data?.addressStore ?: "",
                                                    style = TextStyle(
                                                        color = Color.White,
                                                        fontSize = 16.sp,
                                                        fontWeight = FontWeight.Medium
                                                    )
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
                                                    .width(80.dp),
                                                imageModel = detailStore.data?.logo ?: "",
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
                                        style =  TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        Text(
                                            text = "Lihat semua",
                                            style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = GreenPrimary
                                                )
                                        )
                                        Icon(Octicons.ChevronRight24,"",
                                            tint= GreenPrimary,
                                            modifier = modifier
                                                .width(20.dp)
                                                .height(20.dp)

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
                                                onDetail = {
                                                        index, product ->
                                                    navHostController.navigate("${Routes.DETAIL_PRODUCT}/${product.uid}")
                                                },
                                                onDelete = {
                                                        index, product ->

                                                },
                                                onEdit = {
                                                        index, product ->

                                                }
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
fun PreviewPageDetailStore(){
    PageDetailStore(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope()
    )
}