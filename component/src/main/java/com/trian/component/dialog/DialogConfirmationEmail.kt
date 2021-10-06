package com.trian.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.FontDeviceConnected
import com.trian.component.ui.theme.TesMultiModuleTheme

/**
 * Dashboard Profile
 * Author PT Cexup Telemedicine
 * Created by Rachman Ecky Retnaldi
 * 11/09/2021
 */

@Composable
fun DialogConfirmationEmail(
    modifier: Modifier = Modifier,
    description:String="",
    onConfirmClick:()->Unit
){
    Surface(
        modifier = modifier
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
        color = Color.White
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .size(150.dp)
            )
            Text(
                text = "Check Your Mail",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    ),
                textAlign = TextAlign.Center
            )
            Text(
                text = description,
                modifier = modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = FontDeviceConnected,
                fontSize = 16.sp
            )

            Button(
                onClick =onConfirmClick,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Ok",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                    modifier = modifier.padding(10.dp))
            }

        }
    }

}

@Composable
fun DialogConfirmationEmailSuccess(
    show:Boolean,
    modifier: Modifier = Modifier,
    description:String="",
    onConfirmClick:()->Unit
){
    if(show) {
        Dialog(onDismissRequest = onConfirmClick) {
            Surface(
                modifier = modifier
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .size(130.dp)
                    )
                    Text(
                        text = "Check Your Mail",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp,
                            ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Please check your email to confirm the $description has been change.",
                        modifier = modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp,
                                bottom = 16.dp
                            )
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = FontDeviceConnected,
                        fontSize = 12.sp
                    )
                    Row(
                        modifier = modifier
                            .padding( horizontal = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick =onConfirmClick,
                            modifier = modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = "Ok",
                                style = MaterialTheme.typography.h1.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    letterSpacing = 1.sp,
                                    color = Color.White
                                ),
                                modifier = modifier.padding(10.dp))
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DialogEditPreview(){
    TesMultiModuleTheme {
        DialogConfirmationEmail(description = "Please check your email to confirm the trian@gmail.com has been change.",onConfirmClick = {})
    }
}