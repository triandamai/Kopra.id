package com.trian.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogPickImage(
    modifier: Modifier = Modifier,
    show:Boolean,
    onCancel:()->Unit,
    onCamera:()->Unit,
    onGallery:()->Unit
    ){
    if(show){
        Dialog(onDismissRequest = onCancel) {
            Surface(
                modifier = modifier.padding(10.dp),
                shape = RoundedCornerShape(5),
                color = Color.White,
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Text(
                        text = "Ambil gambar melalui : ",
                        fontSize = 18.sp,
                        fontWeight= FontWeight.Bold,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 10.dp),
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = "Galeri",
                        fontSize = 16.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable(onClick = onGallery),
                        textAlign = TextAlign.Left
                    )
                    Divider()
                    Text(
                        text = "Kamera",
                        fontSize = 16.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clickable(onClick = onCamera),
                        textAlign = TextAlign.Left
                    )
                }
            }
        }
    }
}