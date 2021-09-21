package com.trian.component.bottomsheet

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.TesMultiModuleTheme


@Composable
fun UploadImage(
    isDialogOpen : MutableState<Boolean>,
    Camera:() -> Unit,
    galerry:ManagedActivityResultLauncher<String,Uri>,
    m: Modifier = Modifier,

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
                        fontSize = 24.sp,
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
//                            Camera()
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
                            galerry.launch("image/*")
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

@Preview
@Composable
fun UploadImagePreview(){
    TesMultiModuleTheme {
    }
}