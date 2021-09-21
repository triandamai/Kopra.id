package com.trian.camera

import android.view.ContextThemeWrapper
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.serenegiant.usb.widget.UVCCameraTextureView
import com.trian.component.appbar.AppBarFeature
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun CameraUi(

){
    Scaffold(
        topBar = {
            AppBarFeature(name = "andi", image = "", onBackPressed = { /*TODO*/ }, onProfile = {})
        },
        bottomBar = {
                    Column(
                        modifier = Modifier
                            .background(LightBackground)
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        var visible by remember {
                            mutableStateOf(true)
                        }
                        var visible2 by remember {
                            mutableStateOf(false)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        
                        ){

                            Text(
                                text = "Photo",
                                fontSize = 24.sp,
                                color = ColorFontFeatures,
                                modifier = Modifier.clickable {
                                    visible = true
                                    visible2 = false
                                })
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "Video",
                                fontSize = 24.sp,
                                color = ColorFontFeatures,
                                modifier = Modifier.clickable {
                                    visible2 = true
                                    visible = false
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .clip(shape = RoundedCornerShape(5.dp)),
                            ) {
                                Image(
                                    painter = painterResource(id = com.trian.component.R.drawable.dummy_profile),
                                    contentDescription = "profile",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(RoundedCornerShape(5.dp))                       // clip to the circle shape
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                        .clickable {
                                                   /* Todo */
                                        },
                                )
                            }

                            if(visible2){
                                if(visible == visible2){
                                    visible = !visible2
                                }
                                var recordState by remember {
                                    mutableStateOf(false)
                                }
                                    Box(
                                        modifier = Modifier
                                            .height(80.dp)
                                            .width(80.dp)
                                            .clip(shape = CircleShape)
                                            .background(MaterialTheme.colors.primary)
                                            .clickable {
                                                recordState = !recordState
                                                       /*To do*/
                                                       },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        if (recordState){
                                            Box(
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .width(40.dp)
                                                    .clip(shape = RoundedCornerShape(4.dp))
                                                    .background(Color.Red)
                                                    .border(
                                                        width = 2.dp,
                                                        MaterialTheme.colors.onPrimary,
                                                        shape = RoundedCornerShape(4.dp)
                                                    ),

                                                )
                                        }else{
                                            Box(
                                                modifier = Modifier
                                                    .height(70.dp)
                                                    .width(70.dp)
                                                    .clip(shape = CircleShape)
                                                    .background(Color.Red)
                                                    .border(
                                                        width = 2.dp,
                                                        MaterialTheme.colors.onPrimary,
                                                        shape = CircleShape
                                                    ),

                                                )
                                        }



                                    }

                            }

                            if(visible){
                                if(visible2 == visible) {
                                    visible2 = !visible2
                                }
                                    Box(
                                        modifier = Modifier
                                            .height(80.dp)
                                            .width(80.dp)
                                            .clip(shape = CircleShape)
                                            .background(MaterialTheme.colors.primary)
                                            .clickable {
                                                       /* To do */
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .height(70.dp)
                                                .width(70.dp)
                                                .clip(shape = CircleShape)
                                                .background(MaterialTheme.colors.primary)
                                                .border(
                                                    width = 2.dp,
                                                    MaterialTheme.colors.onPrimary,
                                                    shape = CircleShape
                                                ),

                                            )

                                    }

                            }

                            Box(
                                modifier = Modifier
                                    .width(80.dp)
                                    .height(80.dp)
                                    .clip(shape = RoundedCornerShape(5.dp)),
                            ) {

                            }

                        }
                    }
        },
        backgroundColor = LightBackground
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .clip(RoundedCornerShape(12.dp))
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            AndroidView(
                factory = {
                    UVCCameraTextureView(ContextThemeWrapper(it, R.style.Chart))
                },
                update = {
                        View ->

                    View.display
                    View.width
                    View.height

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(580.dp)


            )


        }
    }

}



@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview
@Composable
fun CameraPreview(){
    TesMultiModuleTheme {
        CameraUi()
    }
}

