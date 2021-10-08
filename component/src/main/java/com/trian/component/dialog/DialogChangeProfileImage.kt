package com.trian.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.trian.component.ui.theme.TesMultiModuleTheme


@Composable
fun DialogChangeProfileImage(
    modifier: Modifier = Modifier,
    show : Boolean=false,
    openCamera:() -> Unit,
    openGallery:()->Unit,
    onConfirm:()->Unit,
    onCancel:()->Unit

    ){

    if(show){
        Dialog(onDismissRequest = onCancel) {
            Surface(
                modifier = modifier
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Upload Image",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_images),
                        contentDescription = "",
                        modifier = modifier
                            .size(150.dp)
                    )

                    Button(
                        onClick = openCamera,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = BluePrimary,
                            contentColor = Color.White
                        ),
                        modifier = modifier
                            .padding(vertical = 16.dp, horizontal = 25.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Take photo",
                        )
                    }

                    Button(
                        onClick = openGallery,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = BluePrimary,
                            contentColor = Color.White
                        ),
                        modifier = modifier
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