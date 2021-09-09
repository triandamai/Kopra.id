package com.trian.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Preview
@Composable
fun BottomSheetDevices(){
    TesMultiModuleTheme {
    }
}


@ExperimentalMaterialApi
@Composable
fun ModalBottomSheetMainscreenView(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState
) {
    Box(
        Modifier
            .fillMaxWidth()
    ){
        Button(
            modifier = Modifier
                .padding(20.dp)
                .align(alignment = Alignment.TopCenter),
            onClick = {
                scope.launch{
                    modalBottomSheetState.show()
                }
            }
        ) {
            Text(
                text = "Click Button Sheet"
            )
        }
    }
}

@Composable
@ExperimentalMaterialApi
fun ContentBottomSheetDevices(
    devices : List<com.trian.domain.models.Devices>
){
    var search by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .padding(10.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Color(0xFFF0F0F0))
                    .height(10.dp)
                    .width(90.dp),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            placeholder = { Text(text = "Search Device", color = Color.Gray) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(8.8f)
                .background(LightBackground),
            shape = RoundedCornerShape(10.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Device Nearby", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn() {
            items(count=devices.size,itemContent = {
                    index: Int ->
                ListItem(
                    text = {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = devices[index].name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = devices[index].mac,
                                fontSize = 16.sp,
                            )


                        }

                    },
                    icon = {
                        Icon(
                            Icons.Default.PhoneAndroid,
                            contentDescription = "Localized description"
                        )
                    }
                )
            })
        }
    }
}

