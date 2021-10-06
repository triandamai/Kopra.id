package com.trian.component.bottomsheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.fonts

@Composable
fun DialogCheckout(m:Modifier = Modifier, isDialogCheckout: MutableState<Boolean>){
    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density
    if(isDialogCheckout.value){
        Dialog(onDismissRequest = { isDialogCheckout.value = false }) {
            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(5)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = m.padding(15.dp)
                ){
                    Spacer(modifier = m.padding(15.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_checklist),
                        "",
                        modifier = m.width(80.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = m.height(10.dp))
                    Text(
                        text = "Confirmation",
                        style = MaterialTheme.typography.h1.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = m.height(10.dp))
                    Text(
                        text = "Your Appointment with Dr.Yakob Simatupang confirmed",
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = m.padding(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = m.fillMaxWidth()
                    ){
                        Text(
                            text = "Date",
                        )
                        Text(
                            text = "12-08-2021",
                            style = MaterialTheme.typography.h1.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = m.padding(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = m.fillMaxWidth()
                    ){
                        Text(
                            text = "Time",
                        )
                        Text(
                            text = "12:00-14:00",
                            style = MaterialTheme.typography.h1.copy(fontSize = 16.sp,fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = m.padding(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = m.fillMaxWidth()
                    ){
                        Button(
                            onClick = { isDialogCheckout.value = false },
                            modifier = m.width(currentWidth/2-40.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            ),
                            border = BorderStroke(width = 1.dp,color = Color.Black)
                        ){
                            Text(text = "Cancel",
                                style = MaterialTheme.typography.h1.copy(
                                color = Color.Black,
                                fontSize = 12.sp,
                                letterSpacing = 0.1.sp,
                            ))
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = m.width(currentWidth/2-40.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary
                            ),
                        ) {
                            Text(
                                text = "Pay now",
                                style = MaterialTheme.typography.h1.copy(
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    letterSpacing = 0.1.sp,
                                )
                            )
                        }
                    }
                    Spacer(modifier = m.padding(15.dp))
                }
            }
        }
    }
}