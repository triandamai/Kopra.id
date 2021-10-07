package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures

@Composable
fun cardNotFound(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(Color.Transparent),
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No Internet Connection",
                fontSize = 22.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
            Spacer(modifier = modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_notfound),
                contentDescription = "Not Found",
                modifier = modifier.width(300.dp)
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = "Looks like you are lost!, maybe you are not connected to the internet",
                textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = modifier.width(300.dp)
            )
            Spacer(modifier = modifier.height(20.dp))
            Button(onClick = { }, modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(BluePrimary) ) {
                Text(
                    text = "Try Again",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }

}

@Preview
@Composable
fun previewNotFound(){
    cardNotFound()
}