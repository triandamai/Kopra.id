package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.ColorGray
import com.trian.component.R
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import android.R.color
import androidx.compose.foundation.BorderStroke
import com.trian.common.utils.utils.coloredShadow
import com.trian.component.ui.theme.ColorFontFeatures


@Composable
fun CardStore(
    modifier:Modifier=Modifier,
    storeName:String,
    address:String,
    price:String
){
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.fillMaxWidth().padding(bottom = 10.dp),
        elevation=0.1.dp,
        border = BorderStroke(
            width=1.dp,
            color = ColorGray.copy(alpha = 0.5f)
        )
    ){
        Row(
            modifier= modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Column() {
                Text(
                    text = storeName,
                    style= MaterialTheme.typography.h1.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = address,
                    style= MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp,
                        color = ColorGray,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = price,
                    style= MaterialTheme.typography.h1.copy(
                        fontSize = 16.sp,
                        color = ColorGray,
                        letterSpacing = 0.1.sp
                    )
                )
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = 0.dp,
                modifier = modifier.mediaQuery(
                    Dimensions.Width lessThan 400.dp,
                    modifier = modifier
                        .width(80.dp)
                        .height(80.dp)
                ),
            ) {
                Box(modifier=modifier.background(color = Color.Black)){
                    Image(
                        painter = painterResource(id = R.drawable.dummy_doctor), contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = modifier.alpha(0.9f)
                    )
                }
            }
        }
    }
}