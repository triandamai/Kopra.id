package com.trian.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.ui.theme.BluePrimary
import compose.icons.Octicons
import compose.icons.octicons.Person16

/**
 * Dashboard Profile
 * Author PT Cexup Telemedicine
 * Created by Rachman Ecky Retnaldi
 * 11/09/2021
 */

@Composable
fun DialogChangeName(
    modifier: Modifier = Modifier,
    show : Boolean = false,
    onConfirm:()->Unit,
    onCancel:()->Unit,
){

    val nameState = remember { mutableStateOf(TextFieldValue("")) }
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change Your name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    TextField(
                        value = nameState.value,
                        leadingIcon = { Icon(Octicons.Person16, contentDescription ="", tint = Color.Black ) },
                        onValueChange = {nameState.value=it},
                        placeholder = { Text(text = "Your name", color = Color.LightGray) },
                        singleLine = true,
                        modifier = modifier
                            .fillMaxWidth()
                            .border(
                                width = 2.dp,
                                shape = RoundedCornerShape(10.dp),
                                color = Color.White,
                            )
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = BluePrimary.copy(alpha = 0.1f),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        ),

                        )
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = onConfirm,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary,
                                contentColor = Color.White
                            ),
                            modifier = modifier.width(84.dp)
                        ) {
                            Text(
                                text = "Save",
                            )
                        }
                        Spacer(modifier = modifier.width(8.dp))
                        Button(
                            onClick = onCancel,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = BluePrimary
                            ),
                            modifier = modifier.width(84.dp)
                        ) {
                            Text(
                                text = "Cancel",
                            )
                        }

                    }

                }

            }
        }
    }

}