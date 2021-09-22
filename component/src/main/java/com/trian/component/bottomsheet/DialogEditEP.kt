package com.trian.component.bottomsheet

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.FontDeviceConnected
import com.trian.component.ui.theme.TesMultiModuleTheme
import compose.icons.Octicons
import compose.icons.octicons.Mail16
import compose.icons.octicons.Number16
import compose.icons.octicons.Person16

@Composable
fun DialogEditEmail(
    isDialogEmail : MutableState<Boolean>,
    email: String,
    m: Modifier = Modifier,
    ){
    val isSuccess = remember { mutableStateOf(false) }
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    DialogAfterSucces(isSucces = isSuccess, Confirm = "number phone")
    if(isDialogEmail.value){
        Dialog(onDismissRequest = { isDialogEmail.value = false}) {
            Surface(
                modifier = m
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= m
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change Email",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    Text(
                        text = "Your current email is $email. What would you like to update it to?",
                        modifier = m
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        textAlign = TextAlign.Justify

                    )
                    TextField(
                        value = emailState.value,
                        leadingIcon = { Icon(Octicons.Mail16, contentDescription ="" ) },
                        onValueChange = {emailState.value=it},
                        placeholder = {Text(text = "New email address")},
                        singleLine = true,
                        modifier = m
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
                        modifier = m
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = {
                                isDialogEmail.value = false
                                isSuccess.value = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary,
                                contentColor = Color.White
                            ),
                            modifier = m.width(83.dp)
                        ) {
                            Text(
                                text = "Save",
                            )
                        }
                        Spacer(modifier = m.width(8.dp))
                        Button(
                            onClick = {
                                isDialogEmail.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = BluePrimary
                            ),
                            modifier = m.width(83.dp)
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

@Composable
fun DialogEditPhone(
    isDialogPhone : MutableState<Boolean>,
    phone: String,
    m: Modifier = Modifier,
){
    val isSuccess = remember { mutableStateOf(false) }
    val phoneState = remember { mutableStateOf(TextFieldValue("")) }
    DialogAfterSucces(isSucces = isSuccess, Confirm = "number phone")
    if(isDialogPhone.value){
        Dialog(onDismissRequest = { isDialogPhone.value = false}) {
            Surface(
                modifier = m
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= m
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change Phone Number",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    Text(
                        text = "Your current phone number is $phone. What would you like to update it to?",
                        modifier = m
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                        textAlign = TextAlign.Justify

                    )
                    TextField(
                        value = phoneState.value,
                        leadingIcon = { Icon(Octicons.Number16, contentDescription ="" ) },
                        onValueChange = {phoneState.value=it},
                        placeholder = {Text(text = "New phone number")},
                        singleLine = true,
                        modifier = m
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
                        modifier = m
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = {
                                isSuccess.value = true
                                isDialogPhone.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary,
                                contentColor = Color.White
                            ),
                            modifier = m.width(83.dp)
                        ) {
                            Text(
                                text = "Save",
                            )
                        }
                        Spacer(modifier = m.width(8.dp))
                        Button(
                            onClick = {
                                isDialogPhone.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = BluePrimary
                            ),
                            modifier = m.width(83.dp)
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

@Composable
fun DialogEditName(
    isDialogName : MutableState<Boolean>,
    m: Modifier = Modifier,
){

    val nameState = remember { mutableStateOf(TextFieldValue("")) }
    if(isDialogName.value){
        Dialog(onDismissRequest = { isDialogName.value = false}) {
            Surface(
                modifier = m
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier= m
                        .fillMaxWidth()
                        .height(500.dp)
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change Your name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Left

                    )
                    TextField(
                        value = nameState.value,
                        leadingIcon = { Icon(Octicons.Person16, contentDescription ="", tint = Color.Black ) },
                        onValueChange = {nameState.value=it},
                        placeholder = {Text(text = "Your name", color = Color.LightGray)},
                        singleLine = true,
                        modifier = m
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
                        modifier = m
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(vertical = 16.dp, horizontal = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick = {
                                isDialogName.value = false

                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = BluePrimary,
                                contentColor = Color.White
                            ),
                            modifier = m.width(84.dp)
                        ) {
                            Text(
                                text = "Save",
                            )
                        }
                        Spacer(modifier = m.width(8.dp))
                        Button(
                            onClick = {
                                isDialogName.value = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White,
                                contentColor = BluePrimary
                            ),
                            modifier = m.width(84.dp)
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

@Composable
fun DialogAfterSucces(
    isSucces:MutableState<Boolean>,
    m: Modifier = Modifier,
    Confirm:String
){
    if(isSucces.value) {
        Dialog(onDismissRequest = { isSucces.value = false }) {
            Surface(
                modifier = m
                    .padding(5.dp),
                shape = RoundedCornerShape(5.dp),
                color = Color.White
            ) {
                Column(
                    modifier = m
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(vertical = 16.dp)
                        .background(MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_email),
                        contentDescription = "",
                        modifier = m
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp)
                            .size(150.dp)
                    )
                    Text(
                        text = "Check Your Mail",
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        modifier = m
                            .fillMaxWidth()
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp,
                            ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Please check your email to confirm the $Confirm has been change.",
                        modifier = m
                            .padding(
                                start = 16.dp,
                                end = 16.dp,
                                top = 16.dp,
                            )
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = FontDeviceConnected,
                        fontSize = 16.sp
                    )
                    Row(
                        modifier = m
                            .padding( horizontal = 16.dp)
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(
                            onClick ={ isSucces.value = false},
                            modifier = m
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                            shape = RoundedCornerShape(15.dp)
                        ) {
                            Text(
                                text = "Ok",
                                style = MaterialTheme.typography.h1.copy(
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    letterSpacing = 1.sp,
                                    color = Color.White
                                ),
                                modifier = m.padding(10.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ContentReview(
    m:Modifier=Modifier,
    Confirm:String
){
    Surface(
        modifier = m
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
        color = Color.White
    ) {
        Column(
            modifier = m
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .background(MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_email),
                contentDescription = "",
                modifier = m
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .size(150.dp)
            )
            Text(
                text = "Check Your Mail",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = m
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    ),
                textAlign = TextAlign.Center
            )
            Text(
                text = "Please check your email to confirm the $Confirm has been change.",
                modifier = m
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                    )
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = FontDeviceConnected,
                fontSize = 16.sp
            )

            Button(
                onClick ={},
                modifier = m
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Ok",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    ),
                    modifier = m.padding(10.dp))
            }

        }
    }

}


@Preview
@Composable
fun DialogEditPreview(){
    TesMultiModuleTheme {
        ContentReview(Confirm = "email")
    }
}