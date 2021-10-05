package com.trian.component.appbar



import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
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
import com.trian.component.ui.theme.*
import compose.icons.Octicons
import compose.icons.octicons.ArrowLeft16
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.Bell16

/**
 * `Persistence Class`
 * Author PT Cexup Telemedhicine
 * Created by Rahman Ecky Retnaldi
 * 03/09/2021
 */



@Composable
fun AppBarFeature(
    name: String,
    image: String,
    onBackPressed: ()->Unit,
    onProfile:()->Unit
) {
    TopAppBar(
        title = {
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp),
            ) {
                Text(
                    text = "Hello, $name !",
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.dummy_profile),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                                .border(1.dp, Color.White, CircleShape)
                                .fillMaxHeight()
                                .fillMaxWidth(),
                        )

                    }
                }
            }

        },
        backgroundColor = Color.Transparent,
        modifier = Modifier.fillMaxWidth(),
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.ArrowBackIos,
                    contentDescription = "Arrow",
                    tint = ColorFontFeatures,
                )
            }
        },


    )

}


@Composable
fun AppbarFeatureSmartWatch(
    name: String,
    shouldFloating:Boolean,
    onBackPressed:()->Unit,
    onSettingPressed:() -> Unit
){
    TopAppBar(
        navigationIcon={
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Octicons.ArrowLeft24,
                        contentDescription = "Arrow",
                        tint = ColorFontFeatures
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { onSettingPressed() }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Arrow",
                    tint = ColorFontFeatures
                )
            }
        },
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)
            ) {

                IconButton(onClick = { /*TODO*/ }) {
                    Image(
                        painter=painterResource(id = R.drawable.dummy_profile),
                        contentDescription = "profile",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .size(34.dp)                      // clip to the circle shape
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                }
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    color = ColorFontFeatures,
                    style= TextStyle(
                        fontSize = 20.sp,
                    ),
                )
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
        },

    )

}
@Preview
@Composable
fun ComposViewfeature(){
    TesMultiModuleTheme() {
//        AppBarFeature(name = "Setting",image = "", onBackPressed = {/*todo*/}, onProfile = {})
        AppbarFeatureSmartWatch(name = "Andi", shouldFloating = false, onSettingPressed = {},onBackPressed = {})
    }
}