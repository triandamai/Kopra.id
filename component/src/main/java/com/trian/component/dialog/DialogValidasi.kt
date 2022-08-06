package com.trian.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery

@Composable
fun DialogValidasi(
    modifier:Modifier=Modifier,
    onCancel:()->Unit,
    show:Boolean,
    isSuccess:Boolean=true,
    isError:Boolean=false,
){
    if(show){
        Dialog(onDismissRequest = onCancel) {
            Card(
                elevation = 0.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(8.dp)
            ){
                Box(
                    modifier= modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier=modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(
                                id = if(isError)R.drawable.warning else if(isSuccess)R.drawable.ic_checklist else R.drawable.remove
                            ), contentDescription = "",
                            modifier = modifier.mediaQuery(
                                Dimensions.Width lessThan 400.dp,
                                modifier = modifier
                                    .height(80.dp)
                                    .width(80.dp)
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = if(isError)"Error" else if(isSuccess)"Sukses" else "Gagal",
                            style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp, value =
                            MaterialTheme.typography.h1.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.1.sp
                            )
                            )
                        )
                        Spacer(modifier = modifier.height(10.dp))
                        Text(
                            text = "Keterangan",
                            style = TextStyle().mediaQuery(Dimensions.Width lessThan 400.dp, value =
                            MaterialTheme.typography.h1.copy(
                                fontSize = 16.sp,
                                color = ColorGray,
                                letterSpacing = 0.1.sp
                            )
                            )
                        )
                        Spacer(modifier = modifier.height(30.dp))
                        Button(
                            onClick =onCancel,
                            modifier = modifier
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(
                                width = 1.dp,
                                color = ColorGray
                            )
                        ) {
                            Text(
                                text = "Kembali",
                                style = TextStyle().mediaQuery(
                                    Dimensions.Width lessThan 400.dp,
                                    value = MaterialTheme.typography.h1.copy(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 14.sp,
                                        letterSpacing = 1.sp,
                                        color = ColorGray
                                    )
                                ),
                                modifier = modifier.padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}