package com.trian.kopra.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import compose.icons.Octicons
import compose.icons.octicons.ArrowDown24
import compose.icons.octicons.ArrowLeft24
import compose.icons.octicons.ArrowUp24

@Composable
fun PageLevelUser(
    m:Modifier = Modifier
){
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = m
                    .fillMaxWidth()
                    .padding(20.dp),
            ){
                Icon(
                    Octicons.ArrowLeft24,"",
                )
                Text(
                    text="Kopra.Id",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value=MaterialTheme.typography.h1.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.1.sp,))
                )
            }
        }
    ) {
        Column(
            modifier = m.padding(
                20.dp
            ),
        ) {
            Text(
                text = "Konfirmasi status user",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(10.dp))
            Text(
                text = "Kami akan mengirimkan kode konfirmasi",
                style = TextStyle().mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    value= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        letterSpacing = 0.1.sp,
                    )
                ),
            )
            Spacer(modifier = m.height(20.dp))
            DropDownLevel()
            Spacer(modifier = m.height(50.dp))
            Button(
                onClick ={
                },
                modifier = m.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = BluePrimary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Konfirmasi",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.White
                        )
                    ),
                    modifier = m.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun DropDownLevel(
    m:Modifier = Modifier
){
    var expanded by remember{ mutableStateOf(false)}
    val list = listOf("Petani","Pengepul","Penyewa Kendaraan")
    var selectedItem by remember{ mutableStateOf("")}

    var textfieldsize by remember { mutableStateOf(Size.Zero) }
    val icon = if(expanded){
        Octicons.ArrowUp24
    }else{
        Octicons.ArrowDown24
    }

    Column() {
        OutlinedTextField(
            value = selectedItem, onValueChange = {selectedItem = it},
            modifier = m
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    textfieldsize = layoutCoordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon,"",modifier = m.clickable { expanded = !expanded })
            }, shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = BluePrimary.copy(alpha = 0.9f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "Pilih Status User")
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = m.width(with(LocalDensity.current){textfieldsize.width.toDp()})
        ) {
            list.forEach { label->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewPageLevelUser(){
    PageLevelUser()
}