package com.trian.module.ui.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import compose.icons.Octicons
import compose.icons.octicons.ArrowDown24
import compose.icons.octicons.ArrowUp24
import compose.icons.octicons.Eye24
/**
 * XAxis value formatter
 * Author PT Cexup Telemedicine
 * Created by Kholid Barat daya
 * 03/08/2021
 */
@Composable
fun PageRegister(nav: NavHostController) {
    ComponentRegister(
        onNavigate={
            nav.navigate(Routes.LOGIN)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewRegister(){
    val nav = rememberNavController()
    ComponentRegister(
        onNavigate={
            nav.navigate(Routes.LOGIN)
        }
    )
}

@Composable
fun ComponentRegister(m:Modifier=Modifier,onNavigate:()->Unit){
    val scrollState = rememberScrollState()
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }
    val nameState = remember { mutableStateOf(TextFieldValue(""))}
    val addressState = remember { mutableStateOf(TextFieldValue(""))}
    val numberIdCardState = remember { mutableStateOf(TextFieldValue(""))}
    val usernameState = remember { mutableStateOf(TextFieldValue(""))}
    var passwordShow = remember { false }
    val isChecked = remember { mutableStateOf(true) }
    var expanded by remember { mutableStateOf(false) }
    val suggestions  = listOf("passport","kitas","ktp")
    var selectedText by remember { mutableStateOf("")}
    var textfiledsize by remember { mutableStateOf(Size.Zero)}
    val icon = if(expanded)Octicons.ArrowUp24 else Octicons.ArrowDown24

    Column(
        horizontalAlignment = Alignment.Start,
        modifier = m
            .fillMaxWidth()
            .padding(start = 20.dp,end = 20.dp,top = 18.dp,bottom = 18.dp)
            .verticalScroll(state = scrollState),
    ) {
        Text(
            text="Create\nAccount",
            style= MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 50.sp
            )
        )
        Spacer(modifier = m.height(30.dp))
        Column(){
            Text(text = "Your name",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = nameState.value,
                onValueChange = {nameState.value=it},
                placeholder = {Text(text = "Full Name")},
                singleLine = true,
                modifier = m.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Address",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = addressState.value,
                onValueChange = {addressState.value=it},
                placeholder = {Text(text = "Your Address")},
                singleLine = false,
                modifier = m.fillMaxWidth().border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "ID Card",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = selectedText,
                onValueChange = {selectedText = it},
                placeholder = {Text(text = "Your Type ID Card")},
                singleLine = true,
                modifier = m
                    .fillMaxWidth().border(
                        width = 2.dp,
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                    )
                    .onGloballyPositioned { coordinates ->
                        textfiledsize = coordinates.size.toSize()
                    },
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                trailingIcon = {
                    Icon(icon,"contentDescription",
                        modifier = m.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded=false},
                modifier = m.width(with(LocalDensity.current){textfiledsize.width.toDp()})
            ) {
                suggestions.forEach { label-> DropdownMenuItem(onClick = {
                    selectedText = label
                }) {
                    Text(text = label)
                } }
            }
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Number ID Card",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = numberIdCardState.value,
                onValueChange = {numberIdCardState.value=it},
                placeholder = {Text(text = "Your number id card")},
                singleLine = true,
                modifier = m.fillMaxWidth().border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Email",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = emailState.value,
                onValueChange = {emailState.value=it},
                placeholder = {Text(text = "Your mail")},
                singleLine = true,
                modifier = m.fillMaxWidth().border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Username",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = usernameState.value,
                onValueChange = {usernameState.value=it},
                placeholder = {Text(text = "Username")},
                singleLine = true,
                modifier = m.fillMaxWidth().border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Column(){
            Text(text = "Password",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            TextField(
                value = passwordState.value,
                onValueChange = {passwordState.value=it},
                placeholder = {Text(text = "Your Secret Password")},
                singleLine = true,
                modifier = m.fillMaxWidth().border(
                    width = 2.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White,
                ),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if(passwordShow) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = { IconButton(onClick = {
                    passwordShow = !passwordShow
                }) {
                    Icon(
                        imageVector = Octicons.Eye24,
                        contentDescription =  "",
                        tint = if(passwordShow) ColorFontFeatures else ColorGray
                    )
                } },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Row(horizontalArrangement = Arrangement.Start,verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it },
                enabled = true,
                colors = CheckboxDefaults.colors(BluePrimary)
            )
            Spacer(modifier = m.width(10.dp))
            Text(text = "I agree to the Terms & Conditions\nand Privacy Policy",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp,
            ),)
        }
        Spacer(modifier = m.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = m.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                ),
                modifier = m.padding(10.dp))
        }
        Spacer(modifier = m.height(15.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = m.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                letterSpacing = 1.sp,
                color = ColorGray
            ),)
            TextButton(onClick = onNavigate) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        color = BluePrimary
                    ),
                )
            }
        }
    }
}