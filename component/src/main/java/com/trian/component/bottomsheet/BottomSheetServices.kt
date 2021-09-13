package com.trian.component.bottomsheet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.cards.CardServices
import com.trian.component.datum.listServices
import com.trian.component.ui.theme.LightBackground
import com.trian.domain.models.ServiceType

/**
 * Bottom Sheet Services
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 09/09/2021
 */
@ExperimentalFoundationApi
@Composable
fun BottomSheetServices(modifier:Modifier = Modifier,onClick:(ServiceType)->Unit){
    Column(modifier= modifier
        .fillMaxWidth()
        .background(LightBackground)
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
        .wrapContentHeight()
    ) {
        Row(modifier=modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = modifier
                .width(100.dp)
                .background(Color.LightGray)
                .clip(RoundedCornerShape(16.dp))) {

            }
        }
        LazyVerticalGrid(
            modifier=modifier.padding(all = 16.dp),
            cells = GridCells.Fixed(3),
            content = {
                items(
                    count = listServices.size,
                    itemContent = {
                        index:Int->
                        CardServices(service = listServices[index], index = index, onClick = {
                            onClick(it.type)
                        })
                    })
            })
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewBottomSheetServices(){
    BottomSheetServices(onClick = {

    })
}