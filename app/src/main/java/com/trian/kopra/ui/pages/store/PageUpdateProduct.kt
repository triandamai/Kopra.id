package com.trian.kopra.ui.pages.store

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Money
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.trian.common.utils.utils.PermissionUtils
import com.trian.component.appbar.AppBarFormStore
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.GreenPrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.data.viewmodel.MainViewModel
import compose.icons.Octicons
import compose.icons.octicons.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun PageUpdateProduct(
    modifier:Modifier=Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    permissionUtils: PermissionUtils,
    mainViewModel: MainViewModel,
    navHostController: NavHostController
){
    val scrollState = rememberScrollState()
    var nameState by remember{ mutableStateOf("")}
    var descState by remember{ mutableStateOf("")}
    var categoryState by remember{ mutableStateOf("")}
    var priceState by remember{ mutableStateOf("")}
    var unitState by remember{ mutableStateOf("")}
    var expand by remember { mutableStateOf(false) }
    val unitType = listOf("kg","hari")
    val stroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
    )

    val icon = if(expand){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    val currentWidth = LocalContext
        .current
        .resources
        .displayMetrics.widthPixels.dp/
            LocalDensity.current.density

    Scaffold(
        topBar = {
            AppBarFormStore(
                title = "Update Produk",
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {

            }
        },
        bottomBar = {}
    ) {
        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text("Foto produk")
            Spacer(modifier = modifier.height(10.dp))
            Box(
                modifier
                    .fillMaxWidth()
                    .mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        modifier = modifier.height(100.dp)
                    ),contentAlignment = Alignment.Center){
                Canvas(modifier = modifier.fillMaxSize()) {
                    drawRoundRect(color = ColorGray,style = stroke,cornerRadius = CornerRadius(10.0F,10.0F))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(Octicons.Archive24,"")
                    Spacer(modifier = modifier.height(10.dp))
                    Text(
                        text = "Klik disini untuk upload foto")
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Text("Nama Produk")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = nameState,
                onValueChange = {nameState=it},
                placeholder = { Text(text = "nama produk anda...") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Person24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Deskripsi")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = descState,
                onValueChange = {descState=it},
                placeholder = { Text(text = "Deskripsi produk anda...") },
                singleLine = false,
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Note24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Kategori")
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = categoryState,
                onValueChange = {categoryState=it},
                placeholder = { Text(text = "Kategori produk anda...") },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth()
                    .navigationBarsWithImePadding(),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = ColorGray,
                    focusedIndicatorColor = GreenPrimary,
                    unfocusedIndicatorColor = ColorGray,
                    disabledIndicatorColor = Color.Transparent,
                ),
                leadingIcon = {
                    Icon(Octicons.Link24,"")
                },
            )
            Spacer(modifier = modifier.height(20.dp))
            Text("Harga per unit")
            Spacer(modifier = modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                OutlinedTextField(
                    value = priceState,
                    onValueChange = {priceState=it},
                    placeholder = { Text(text = "Harga produk...") },
                    singleLine = true,
                    modifier = modifier
                        .width(currentWidth / 2)
                        .navigationBarsWithImePadding(),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = ColorGray,
                        focusedIndicatorColor = GreenPrimary,
                        unfocusedIndicatorColor = ColorGray,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Icons.Outlined.AttachMoney,"")
                    },
                )
                Spacer(modifier=modifier.width(5.dp))
                Text("/")
                Spacer(modifier=modifier.width(5.dp))
                Column() {
                    OutlinedTextField(
                        value = unitState,
                        onValueChange = {unitState=it},
                        placeholder = { Text(text = "unit") },
                        singleLine = true,
                        modifier = modifier
                            .width(currentWidth / 2)
                            .navigationBarsWithImePadding()
                            .clickable { expand = true },
                        shape = RoundedCornerShape(10.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = ColorGray,
                            focusedIndicatorColor = GreenPrimary,
                            unfocusedIndicatorColor = ColorGray,
                            disabledIndicatorColor = ColorGray,
                        ),
                        enabled = false,
                        readOnly = true,
                        trailingIcon = {
                            Icon(icon, contentDescription = "")
                        }
                    )
                    DropdownMenu(
                        expanded = expand,
                        onDismissRequest = { expand = false },
                        modifier = modifier
                            .width(currentWidth / 2)
                    ){
                        unitType.forEach { label ->
                            DropdownMenuItem(onClick = {
                                unitState = label
                                expand = false }) {
                                Text(text = label, color = Color.Black)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(20.dp))
            Button(
                onClick ={},
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = GreenPrimary),
                shape = RoundedCornerShape(10.dp)) {
                Text(
                    text = "Tambah",
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