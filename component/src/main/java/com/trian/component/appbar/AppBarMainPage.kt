package com.trian.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.TesMultiModuleTheme
import compose.icons.Octicons
import compose.icons.octicons.Bell16
import compose.icons.octicons.Bell24
import compose.icons.octicons.Home16

/**
 * `Persistence Class`
 * Author PT Cexup Telemedicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */

@Preview
@Composable
fun MainAppBarPreview(){
    TesMultiModuleTheme() {
        AppbarMainPage(page = "Main", name = "Andi", onBackPress = {})
    }
}


@Composable
fun AppbarMainPage(page : String, name: String, onBackPress:()->Unit){
    TopAppBar(
        title = {
            Text(
                text = page,
                textAlign = TextAlign.Center,
                color = ColorFontFeatures,
                fontSize = 26.sp
            )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Text(
                    text = "Hello, $name !",
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = com.trian.component.R.drawable.dummy_profile),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                                .border(1.dp, Color.White, CircleShape)
                                .fillMaxHeight()
                                .fillMaxWidth()
                        )
                }
            }

        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth(),
        elevation = 2.dp
    )

}

@Composable
fun AppbarDashboardHome(
    modifier: Modifier=Modifier,
    name: String,
    shouldFloating:Boolean, onBackPress:()->Unit
){
    TopAppBar(
        title = {
            if(shouldFloating){
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    style= TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }else{
                Image(
                    modifier = modifier.width(30.dp),
                    painter = painterResource(id = R.drawable.logo_cexup),
                    contentDescription = "Cexup"
                )
            }
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)
            ) {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Octicons.Bell16,
                        tint = Color.Black,
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(18.dp)                      // clip to the circle shape
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
            }

        },
        backgroundColor = if(shouldFloating){
            Color.White
        }else{
            Color.Transparent
        },
        modifier = Modifier.fillMaxWidth(),
        elevation = if(shouldFloating){
            3.dp
        }else{
            0.dp
        }
    )

}