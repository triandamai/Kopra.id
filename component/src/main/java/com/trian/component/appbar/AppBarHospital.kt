package com.trian.component.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.GrayInput
import com.trian.component.ui.theme.TesMultiModuleTheme
import compose.icons.Octicons
import compose.icons.octicons.*


@Composable
fun AppBarHospital(
    modifier:Modifier = Modifier,
    color: Color=Color.Transparent,
    elevation: Dp =0.dp,
    query: MutableState<String>,
    onHistoryClick:()->Unit,
    onNotification:()->Unit


){
    TopAppBar(
        title = {
                TextField(
                    value = query.value,
                    leadingIcon = { Icon(Octicons.Search16, contentDescription ="" ) },
                    onValueChange = {query.value=it},
                    placeholder = {
                        Text(
                        text = "Search Hospital",
                            fontSize = 14.sp
                    )
                                  },
                    singleLine = true,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                    ,
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = BluePrimary.copy(alpha = 0.1f),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp),
            ) {
                IconButton(onClick = { onHistoryClick}) {
                    Icon(
                        Octicons.ListUnordered16,
                        contentDescription = "bell",
                        modifier = modifier
                            .size(20.dp),
                        tint = BluePrimary.copy(alpha = 0.5f)
                    )
                }
                IconButton(onClick = { onNotification }) {
                    Icon(
                        Octicons.Bell16,
                        contentDescription = "bell",
                        modifier = modifier
                            .size(20.dp),
                        tint = BluePrimary.copy(alpha = 0.5f)
                    )
                }
            }
        },

        backgroundColor = color,
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        elevation = elevation
    )

}


@Preview
@Composable
fun AppBarHospitalPriview(){
    val isDialogName= remember { mutableStateOf("false") }

    TesMultiModuleTheme {
        AppBarHospital(query = isDialogName, onHistoryClick = {},onNotification = {})
    }
}