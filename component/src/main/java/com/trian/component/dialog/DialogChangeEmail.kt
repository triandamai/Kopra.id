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
import compose.icons.octicons.Mail16

/**
 * Dashboard Profile
 * Author PT Cexup Telemedicine
 * Created by Rachman Ecky Retnaldi
 * 11/09/2021
 */
@Composable
fun DialogChangeEmail(
    modifier: Modifier = Modifier,
    show : Boolean = false,
    email: String="",
    onConfirm:()->Unit,
    onCancel:()->Unit,
){

    val emailState = remember { mutableStateOf(TextFieldValue("")) }

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
                        .height(300.dp)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change Email",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    Text(
                        text = "Your current email is $email. What would you like to update it to?",
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),

                        )
                    TextField(
                        value = emailState.value,
                        leadingIcon = { Icon(Octicons.Mail16, contentDescription ="" ) },
                        onValueChange = {emailState.value=it},
                        placeholder = { Text(text = "New email address") },
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
                            .fillMaxHeight()
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
                            modifier = modifier.width(83.dp)
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
                            modifier = modifier.width(83.dp)
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