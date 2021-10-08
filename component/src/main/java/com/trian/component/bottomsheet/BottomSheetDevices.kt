package com.trian.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.trian.domain.models.Devices
import com.trian.domain.usecase.DevicesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheetDevices(
    modifier: Modifier=Modifier,
    device:List<Devices>,
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    onDeviceSelected:(Devices)->Unit
){
    Box(
        modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .defaultMinSize(minHeight = 100.dp)
    ){

        var search by remember { mutableStateOf("") }
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LightBackground)
                .padding(10.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = modifier
                        .clip(shape = RoundedCornerShape(5.dp))
                        .background(Color.LightGray.copy(alpha = 0.6f))
                        .height(10.dp)
                        .width(90.dp),
                )
            }
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                },
                placeholder = {
                    Text(text = "Search Device", color = Color.Gray)
                },
                singleLine = true,
                modifier = modifier
                    .fillMaxWidth(8.8f)
                    .background(LightBackground),
                shape = RoundedCornerShape(10.dp)
            )
            Spacer(
                modifier = modifier.height(10.dp)
            )
            Text(
                text = "Device Nearby",
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = modifier.height(10.dp)
            )
            LazyColumn() {
                items(count=device.size,itemContent = {
                        index: Int ->
                    ListItem(
                        modifier=modifier.clickable {
                          onDeviceSelected(device[index])
                        },
                        text = {
                            Column(
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = device[index].name,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = device[index].mac,
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
}



