package com.trian.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun DialogConfirmationCheckout(
    modifier: Modifier = Modifier,
    show:Boolean=false,
    storeName:String,
    productName:String,
    shouldRentVehicle:Boolean,
    onCancel:()->Unit,
    onConfirmation:()->Unit
) {
    if(show){
        Dialog(onDismissRequest = { /*TODO*/ }) {
            Column(
                modifier = modifier
                    .background(MaterialTheme.colors.surface)
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            ) {
                Text(
                    "Konfirmasi Pesanan",
                    style= TextStyle(
                        fontSize = 23.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = modifier.width(20.dp))
                Text(
                    "Apakah Anda yakin untuk melanjutkan memesan $productName dari toko $storeName ?",
                    style= TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
                if(shouldRentVehicle){
                    Spacer(modifier = modifier.width(20.dp))
                    Text("*Toko tidak menyediakan kendaraan silahkan menyewa kendaraan dari toko berbeda")
                }
                Spacer(modifier = modifier.width(20.dp))
                Row {
                    Button(onClick = { onCancel() }) {
                        Text(text = "Batal")
                    }
                    Spacer(modifier = modifier.width(16.dp))
                    Button(onClick = { onConfirmation() }) {
                        Text(text = "Lanjutkan")
                    }
                }
            }
        }
    }
}