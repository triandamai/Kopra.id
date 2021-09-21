package com.trian.component.bottomsheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.TesMultiModuleTheme


@Composable
fun UploadImage(
    isDialogOpen : MutableState<Boolean>,
    Camera:() -> Unit,
    Gallery:() -> Unit,
    m: Modifier = Modifier
){
    if(isDialogOpen.value){
        Dialog(onDismissRequest = { isDialogOpen.value = false}) {
            Surface(
                modifier = m
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= m
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Upload Image",
                        fontWeight = FontWeight.Bold,
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_images),
                        contentDescription = "",
                        modifier = m
                            .size(150.dp)
                    )

                    Button(
                        onClick = {
                            Camera
                            isDialogOpen.value = false
                            },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF407BFF),
                            contentColor = Color.White
                        ),
                        modifier = m
                            .padding(vertical = 16.dp, horizontal = 25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Take photo",
                        )
                    }

                    Button(
                        onClick = {
                            Gallery
                            isDialogOpen.value = false},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF407BFF),
                            contentColor = Color.White
                        ),
                        modifier = m
                            .padding(start = 25.dp, end = 25.dp, bottom = 16.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Choose existing photo"
                        )
                    }
                }

            }
        }
    }

}

@Composable
fun AlretDialog1(
    m:Modifier = Modifier
){
  val isDialogOpen = remember { mutableStateOf(false)}
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = m
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
//        UploadImage(isDialogOpen)

        Button(
            onClick = {
                isDialogOpen.value = true
            },
            modifier = m
                .padding(10.dp)
                .fillMaxSize(0.5f)
                .height(50.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF407BFF),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Show Pop up",
            color = Color.White)
        }
    }

}

@Preview
@Composable
fun UploadImagePreview(){
    TesMultiModuleTheme {
        AlretDialog1()
    }
}