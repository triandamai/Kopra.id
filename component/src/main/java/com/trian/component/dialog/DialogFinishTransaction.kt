package com.trian.component.dialog

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.Archive24
import compose.icons.octicons.Note24
import compose.icons.octicons.Person24

@Composable
fun DialogFinishTransaction(
    modifier: Modifier = Modifier,
    show:Boolean=false,
    onCancel:()->Unit,
    onConfirm:(image:Bitmap)->Unit
) {

    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
    )

    var storeImageBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    val pickImageCamera = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview() ){
            bitmap: Bitmap? ->
        bitmap?.let {
            storeImageBitmap = it
        }

    }

    fun processPickImage(){
        pickImageCamera.launch(null)
    }

    if(show){
        Dialog(onDismissRequest = { onCancel() }) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(10.dp),
            ) {
                Text("Bukti Transaksi")
                Spacer(modifier = modifier.height(10.dp))
                Box(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .clickable {
                            processPickImage()
                        },
                    contentAlignment = Alignment.Center
                ){
                    Canvas(modifier = modifier.fillMaxSize()) {
                        drawRoundRect(color = ColorGray,
                            style = stroke,
                            cornerRadius = CornerRadius(10.0F,10.0F))
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        storeImageBitmap?.let {
                            Image(bitmap = it.asImageBitmap(), contentDescription = "")
                        }?: Icon(Octicons.Archive24,"")
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Klik disini untuk upload foto")
                    }
                }


                Spacer(modifier = modifier.height(16.dp))

                Button(
                    onClick ={
                        storeImageBitmap?.let(onConfirm)
                    },
                    modifier = modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "Komfirmasi Sekarang",
                        style = TextStyle().mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            value = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                letterSpacing = 1.sp,
                                color = Color.White
                            )
                        ),
                        modifier = modifier.padding(10.dp)
                    )
                }
            }
        }
    }

}