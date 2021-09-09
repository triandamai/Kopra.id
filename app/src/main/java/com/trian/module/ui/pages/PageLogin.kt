package com.trian.module.ui.pages


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.Eye24
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageLogin(nav: NavHostController,scope:CoroutineScope,modifier: Modifier = Modifier) {
    ComponentLogin(onNavigate={
        nav.navigate(Routes.DASHBOARD.name)
    })
}

@Composable
fun ComponentLogin(modifier: Modifier = Modifier,onNavigate:()->Unit){
    var username by remember {mutableStateOf<String>("") }
    var password by remember {mutableStateOf<String>("") }

    Column(
       modifier = modifier.background(color= Color.White),
    ) {
        TextField(
            value =username,
            onValueChange = {username = it},
            label ={
                Text(text = "Username")
            }
        )
        TextField(
            value =password,
            onValueChange = {password = it},
            label ={
                Text(text = "Password")
            }
        )
        Button(onClick =  onNavigate) {
            Text(text = "Login")
        }

    }
}

@Composable
fun PageLogin(m:Modifier=Modifier,){
    ComponentTopSection()
}

@Preview
@Composable
fun ComponentTopSection(m:Modifier=Modifier){
    Box(
        modifier = m
            .height(150.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 80.dp))
            .background(color = Color.White)
            .shadow(
                elevation = 2.dp
            ),
    ) {
        Column(modifier = m.padding(top = 10.dp,start = 40.dp)) {
            Image(
                painter =
                painterResource(
                    id = R.drawable.logo_cexup
                ),
                contentDescription = "",
                modifier = m
                    .height(100.dp)
                    .width(100.dp)
            )
            Text(text = "It's good to see you again",
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    color = ColorFontFeatures,
                    fontWeight = FontWeight.Medium,
                )
            )
        }
    }
}

@Preview(showBackground = true,backgroundColor = 0xffffff)
@Composable
fun ComponentBodySection(m:Modifier=Modifier){
    val emailState = remember { mutableStateOf(TextFieldValue(""))}
    val passwordState = remember { mutableStateOf(TextFieldValue(""))}
    val passwordShow = remember { false }
    val isChecked = remember { mutableStateOf(true)}

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = m
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column(){
            Text(text = "Email Address",style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                letterSpacing = 1.sp
            ),)
            Spacer(modifier = m.height(10.dp))
            OutlinedTextField(
                value = emailState.value,
                onValueChange = {emailState.value=it},
                placeholder = {Text(text = "Email Address")},
                singleLine = true,
                modifier = m.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f)
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
                value = emailState.value,
                onValueChange = {emailState.value=it},
                placeholder = {Text(text = "Your Secret Password")},
                singleLine = true,
                modifier = m.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                visualTransformation = if(passwordShow) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = { IconButton(onClick = {}) {
                    Icon(
                        imageVector = Octicons.Eye24,
                        contentDescription =  "",
                        tint = if(passwordShow) ColorFontFeatures else ColorGray
                    )
                } },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = BluePrimary.copy(alpha = 0.1f)
                ),
            )
        }
        Spacer(modifier = m.height(20.dp))
        Row() {
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = { isChecked.value = it },
                enabled = true,
                colors = CheckboxDefaults.colors(BluePrimary)
            )
            Spacer(modifier = m.width(10.dp))
            Text(text = "I agree to the Terms & Conditions\nand Privacy Policy")
        }
        Spacer(modifier = m.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = m.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)) {
            Text(
                text = "Sign In",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),
                modifier = m.padding(10.dp))
        }
        Spacer(modifier = m.height(15.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = m.fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                ),
                modifier = m.padding(10.dp))
        }
    }
}