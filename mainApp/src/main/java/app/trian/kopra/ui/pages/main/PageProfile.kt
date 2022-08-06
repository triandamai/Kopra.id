package app.trian.kopra.ui.pages.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kopra.MainViewModel
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground

import compose.icons.Octicons
import compose.icons.octicons.ArrowRight16
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.component.Routes
import com.trian.data.model.LevelUser
import com.trian.data.model.getType


/**
 * Page Dashboard Profile
 * Author Trian damai
 * Created by Trian Damai
 * 25/10/2021
 */

@Composable
fun PageProfile(
    modifier: Modifier = Modifier,
    listState:LazyListState = rememberLazyListState(),
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope,
    restartActivity:()->Unit
){
    val ctx = LocalContext.current
    var isProfileCompleted by remember{mutableStateOf(false)}

    var currentUser by mainViewModel.currentUser

    val myStore by mainViewModel.detailStore
    fun processSignOut(){
        mainViewModel.signOut {
            restartActivity()
        }
    }
    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getCurrentUser { hasUser, user ->
            currentUser = user
        }
    }


        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LightBackground)
        ) {
            LazyColumn(
                state=listState,
                modifier=modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
                    .background(LightBackground),
                content = {
                    item{
                        Row(
                            modifier = modifier
                                .padding(top = 20.dp,bottom = 20.dp),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            CoilImage(
                                modifier = modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable(
                                        onClick = {}
                                    ),
                                imageModel = currentUser?.profilePicture,
                                // Crop, Fit, Inside, FillHeight, FillWidth, None
                                contentScale = ContentScale.Crop,
                                // shows an image with a circular revealed animation.
                                circularReveal = CircularReveal(duration = 250),
                                // shows a placeholder ImageBitmap when loading.
                                placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                                // shows an error ImageBitmap when the request failed.
                                error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                            )



                            Spacer(modifier = modifier.width(16.dp))
                            Column {
                                Text(
                                    text = currentUser?.fullName ?: "",
                                    style = TextStyle(fontSize = 20.sp,fontWeight = FontWeight.Bold)
                                )
                                Text(text =currentUser?.username ?:"")
                            }

                        }
                    }
                    item {
                        Column(
                            modifier= modifier
                                .fillMaxWidth()
                                .background(Color.Transparent)
                                .clip(RoundedCornerShape(10.dp))

                        ) {
                            Column(
                                modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)) {
                                Row(modifier = modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 20.dp,
                                        bottom = 8.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "Nama Lengkap",
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = currentUser?.fullName ?:"",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }

                                }
                                Row(modifier = modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 16.dp,
                                        bottom = 20.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "Telp",
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = currentUser?.phoneNumber ?:"",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            ))
                                    }

                                }
                            }
                        }
                    }
                    item {
                        Column(
                            modifier= modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .background(Color.Transparent)
                                .clip(RoundedCornerShape(10.dp))

                        ) {
                            Column(
                                modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)) {
                                Row(modifier = modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 20.dp,
                                        bottom = 8.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {

                                        Text(
                                            text = "Riwayat Transaksi",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }
                                Row(modifier = modifier
                                    .clickable {
                                        navHostController.navigate(Routes.ABOUT)
                                    }
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 16.dp,
                                        bottom = 8.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {

                                        Text(
                                            text = "Tentang Aplikasi",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            ))
                                    }

                                }
                            }
                        }
                    }

                    item {
                        Column(
                            modifier= modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .background(Color.Transparent)
                                .clip(RoundedCornerShape(10.dp))

                        ) {
                            Column(
                                modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        navHostController.navigate(Routes.UPDATE_PROFILE)
                                    }
                                    .background(Color.White)) {
                                Row(modifier = modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 20.dp,
                                        bottom = 8.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {

                                        Text(
                                            text = "Update Profile",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }

                            }
                            Column(
                                modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .clickable {
                                        navHostController.navigate(Routes.LIST_REMINDER)
                                    }
                                    .background(Color.White)) {
                                Row(modifier = modifier
                                    .padding(
                                        start = 16.dp,
                                        end = 16.dp,
                                        top = 20.dp,
                                        bottom = 8.dp
                                    )
                                    .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {

                                        Text(
                                            text = "Pengingat",
                                            style = TextStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }

                            }
                        }
                    }

                    item {
                        when(ctx.getType()){
                            LevelUser.TENANT -> {
                                Column(
                                    modifier= modifier
                                        .padding(vertical = 16.dp)
                                        .fillMaxWidth()
                                        .background(Color.Transparent)
                                        .clip(RoundedCornerShape(10.dp))
                                ) {

                                    Column(modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Column(modifier=modifier
                                            .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                            Text(
                                                text = "Toko Saya"
                                            )
                                            Text(
                                                text = "Lihat Toko Saya"
                                            )
                                        }
                                        Spacer(modifier = modifier.height(10.dp))
                                        Column(
                                            modifier = modifier
                                                .fillMaxWidth()
                                                .background(
                                                    brush = Brush.horizontalGradient(
                                                        colors =  listOf(
                                                            Color(0xFF2b51fa),
                                                            Color(0xFF4d9efd)
                                                        )
                                                    )
                                                )
                                                .clickable {
                                                    navHostController.navigate(Routes.DETAIL_MY_TOKO)
                                                }

                                        ) {
                                            Spacer(modifier = modifier.height(16.dp))
                                            Row(
                                                modifier = modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                Text(text = "Lihat detail",
                                                    color = Color.White)
                                                Spacer(modifier = modifier
                                                    .width(16.dp))
                                                Icon(
                                                    imageVector = Octicons.ArrowRight16,
                                                    tint= Color.White,
                                                    contentDescription = "See all")

                                            }
                                            Spacer(modifier = modifier.height(16.dp))
                                        }
                                    }

                                }
                            }
                            LevelUser.COLLECTOR -> {
                                Column(
                                    modifier= modifier
                                        .padding(vertical = 16.dp)
                                        .fillMaxWidth()
                                        .background(Color.Transparent)
                                        .clip(RoundedCornerShape(10.dp))
                                ) {

                                    Column(modifier = modifier
                                        .fillMaxWidth()
                                        .background(Color.White),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Column(modifier=modifier
                                            .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                            Text(
                                                text = "Toko Saya"
                                            )
                                            Text(
                                                text = "Lihat Toko Saya"
                                            )
                                        }
                                        Spacer(modifier = modifier.height(10.dp))
                                        Column(
                                            modifier = modifier
                                                .fillMaxWidth()
                                                .background(
                                                    brush = Brush.horizontalGradient(
                                                        colors =  listOf(
                                                            Color(0xFF2b51fa),
                                                            Color(0xFF4d9efd)
                                                        )
                                                    )
                                                )
                                                .clickable {
                                                    navHostController.navigate(Routes.DETAIL_MY_TOKO)
                                                }

                                        ) {
                                            Spacer(modifier = modifier.height(16.dp))
                                            Row(
                                                modifier = modifier
                                                    .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Center,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {

                                                Text(text = "Lihat detail",
                                                    color = Color.White)
                                                Spacer(modifier = modifier
                                                    .width(16.dp))
                                                Icon(
                                                    imageVector = Octicons.ArrowRight16,
                                                    tint= Color.White,
                                                    contentDescription = "See all")

                                            }
                                            Spacer(modifier = modifier.height(16.dp))
                                        }
                                    }

                                }
                            }
                            LevelUser.FARMER -> {}
                            LevelUser.UNKNOWN -> {}
                        }

                    }
                    item {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextButton(onClick = {
                                processSignOut()
                            }
                            ) {
                                Text(
                                    text = "Sign Out",
                                    style=TextStyle(
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    color = Color.Red
                                )
                            }
                        }
                    }

                    item {
                        Spacer(modifier =modifier.height(80.dp))
                    }
                })
        }

}

@Preview
@Composable
fun PreviewPageProfile(){
    PageProfile(
        mainViewModel = viewModel(),
        navHostController = rememberNavController(),
        scope = rememberCoroutineScope() ){

    }
}