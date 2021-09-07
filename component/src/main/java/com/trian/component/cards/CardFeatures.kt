package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trian.component.R
import com.trian.component.ui.theme.CardColor


@Composable
fun CardFeatures(
    image : String,
    name : String,
    onClickPressed: ()->Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = CardColor),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .height(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dummycardfeature),
                contentDescription ="card feature",
                modifier = Modifier.width(50.dp).height(50.dp)
            )
        }

    }

}
