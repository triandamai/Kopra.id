package com.trian.kopra.ui.pages.main

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.utils.getBitmap
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import com.trian.kopra.utils.coloredShadow
import compose.icons.Octicons
import compose.icons.octicons.ArrowRight16
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.domain.models.network.CurrentUser

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
    mainViewModel: MainViewModel,
    navHostController: NavHostController,
    scope: CoroutineScope,
    restartActivity:()->Unit
){
    val context = LocalContext.current
    var isProfileCompleted by remember{mutableStateOf(false)}

    val currentUser by mainViewModel.currentUser.collectAsState(initial = CurrentUser.NoUser())

    fun processSignOut(){
        mainViewModel.signOut {

        }
    }


    Scaffold(
        backgroundColor= LightBackground,
        topBar = {},
    ) {

        LazyColumn(
            state=listState,
            modifier=modifier
                .padding(horizontal = 16.dp),
            content = {
                item{
                    Row(
                        modifier = modifier
                            .padding(top = 20.dp,bottom = 20.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        when(currentUser){
                            is CurrentUser.HasUser -> {
                               val user = currentUser.user!!

                                CoilImage(
                                    modifier = modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .coloredShadow(ColorFontFeatures)
                                    .height(80.dp)
                                    .width(80.dp)
                                    .clickable(
                                        onClick = {

                                        }
                                    ),
                                    imageModel = user.profilePicture,
                                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                                    contentScale = ContentScale.Crop,
                                    // shows an image with a circular revealed animation.
                                    circularReveal = CircularReveal(duration = 250),
                                    // shows a placeholder ImageBitmap when loading.
                                    placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                                    // shows an error ImageBitmap when the request failed.
                                    error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                                )

                            }
                            is CurrentUser.Loading -> {

                            }
                            is CurrentUser.NoUser -> {

                            }
                            is CurrentUser.UserNotComplete -> {

                            }
                        }

                        Spacer(modifier = modifier.width(16.dp))
                        Column {
                            Text(
                                text =   when(currentUser){
                                    is CurrentUser.HasUser -> {
                                        val user = currentUser.user!!
                                        user.fullName

                                    }
                                    is CurrentUser.Loading -> {
                                        ""
                                    }
                                    is CurrentUser.NoUser -> {
                                        ""
                                    }
                                    is CurrentUser.UserNotComplete -> {
                                        ""
                                    }
                                },
                                style = TextStyle(fontSize = 20.sp,fontWeight = FontWeight.Bold)
                            )
                            Text(text = when(currentUser){
                                is CurrentUser.HasUser -> {
                                    val user = currentUser.user!!
                                    user.username

                                }
                                is CurrentUser.Loading -> {
                                    ""
                                }
                                is CurrentUser.NoUser -> {
                                    ""
                                }
                                is CurrentUser.UserNotComplete -> {
                                    ""
                                }
                            })
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
                                .coloredShadow(
                                    color = ColorFontFeatures
                                )
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
                                        text = "Display Name",
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = when(currentUser){
                                            is CurrentUser.HasUser -> {
                                                val user = currentUser.user!!
                                                user.fullName

                                            }
                                            is CurrentUser.Loading -> {
                                                ""
                                            }
                                            is CurrentUser.NoUser -> {
                                                ""
                                            }
                                            is CurrentUser.UserNotComplete -> {
                                                ""
                                            }
                                        },
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Button(onClick = {

                                },modifier = Modifier.width(91.dp)) {
                                    Text(text = "Edit",style = TextStyle()
                                        .mediaQuery(
                                            Dimensions.Width lessThan 400.dp,
                                            value = TextStyle(fontSize = 14.sp)
                                        ), color = Color.White)
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
                                        text = "Phone",
                                        color = Color.Gray
                                    )
                                    Text(
                                        text = when(currentUser){
                                            is CurrentUser.HasUser -> {
                                                val user = currentUser.user!!
                                                user.phoneNumber

                                            }
                                            is CurrentUser.Loading -> {
                                                ""
                                            }
                                            is CurrentUser.NoUser -> {
                                                ""
                                            }
                                            is CurrentUser.UserNotComplete -> {
                                                ""
                                            }
                                        },
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold
                                        ))
                                }
                                Button(onClick = {  },modifier = Modifier.width(91.dp)) {
                                    Text(text = "Change",style = TextStyle()
                                        .mediaQuery(
                                            Dimensions.Width lessThan 400.dp,
                                            value = TextStyle(fontSize = 14.sp)
                                        ), color = Color.White)
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
                                .coloredShadow(
                                    color = ColorFontFeatures
                                )
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
                                        text = "Device Setting",
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
                                    bottom = 8.dp
                                )
                                .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {

                                    Text(
                                        text = "About App",
                                        style = TextStyle(
                                            fontSize = 16.sp,
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
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))
                    ) {

                        Column(modifier = modifier
                            .fillMaxWidth()
                            .coloredShadow(
                                color = ColorFontFeatures
                            )
                            .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(modifier=modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(text = "History",style=TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                ))
                                Text(text = "Teleconsultation")
                            }
                        }

                    }
                }
                item {
                    Column(
                        modifier= modifier
                            .padding(vertical = 16.dp)
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(10.dp))
                    ) {

                        Column(modifier = modifier
                            .fillMaxWidth()
                            .coloredShadow(
                                color = ColorFontFeatures
                            )
                            .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Column(modifier=modifier
                                .padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(text = "Profile")
                                Text(text = "Your profile not complete")
                            }
                            Spacer(modifier = modifier.height(10.dp))
                            Column(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = when (isProfileCompleted) {
                                                true -> listOf(
                                                    Color(0xFF2b51fa),
                                                    Color(0xFF4d9efd)
                                                )
                                                else -> listOf(
                                                    Color(0xFFff276a),
                                                    Color(0xFffc5a4e)
                                                )
                                            }
                                        )
                                    )
                                    .clickable {

                                    }

                            ) {
                                Spacer(modifier = modifier.height(16.dp))
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(text = "Complete my profile",
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
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(onClick = {

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