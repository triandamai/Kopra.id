package com.trian.component.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.trian.common.utils.route.Routes
import com.trian.component.R
import com.trian.component.ui.theme.ColorGray
import com.trian.component.ui.theme.LightBackground
import com.trian.component.utils.mediaquery.Dimensions
import com.trian.component.utils.mediaquery.lessThan
import com.trian.component.utils.mediaquery.mediaQuery
import com.trian.domain.models.Product
import com.trian.domain.models.ProductCategory

@Composable
fun CardItemProduct (
    modifier:Modifier = Modifier,
    index:Int =0,
    product:Product,
    onDetail:(index:Int,product:Product)->Unit,
    onDelete:(index:Int,product:Product)->Unit,
    onEdit:(index:Int,product:Product)->Unit
){
    Card(
    modifier = modifier
    .fillMaxWidth()
    .padding(bottom = 15.dp)
    .clickable {
        onDetail(index,product)
    },
    shape = RoundedCornerShape(10.dp),
    elevation = 0.dp,
    ){
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column() {
                Text(
                    text = product.productName,
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                )
                Text(
                    text = "Rp ${product.price}",
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = TextStyle(
                            fontSize = 16.sp,
                            color = ColorGray
                        )
                    )
                )
                Text(
                    text = when(product.category){
                        ProductCategory.VEHICLE -> "Kendaraan"
                        ProductCategory.COMODITI -> "Kopra"
                        ProductCategory.UNKNOWN -> ""
                        ProductCategory.NO_DATA -> ""
                    },
                    style = TextStyle().mediaQuery(
                        Dimensions.Width lessThan 400.dp,
                        value = TextStyle(
                            fontSize = 16.sp,
                            color = ColorGray
                        )
                    )
                )
            }
            Card(
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = LightBackground
                ),
                elevation = 0.dp
            ){
                Box(
                    modifier = modifier
                        .background(color = Color.Black),
                ){
                    CoilImage(
                        modifier = modifier
                            .clip(RoundedCornerShape(12.dp)).mediaQuery(
                                Dimensions.Width lessThan 400.dp,
                        modifier = modifier
                            .height(80.dp)
                            .width(80.dp)),
                        imageModel = product.thumbnail,
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = ImageBitmap.imageResource(R.drawable.dummy_profile),
                        // shows an error ImageBitmap when the request failed.
                        error = ImageBitmap.imageResource(R.drawable.dummy_doctor)
                    )
                    Image(
                        painter = painterResource(id = com.trian.component.R.drawable.motor),
                        contentDescription = "", contentScale = ContentScale.Crop,
                        alpha = 0.9f,
                        modifier = modifier.mediaQuery(
                            Dimensions.Width lessThan 400.dp,
                            modifier = modifier
                                .height(80.dp)
                                .width(80.dp)
                        )
                    )
                }
            }
        }
    }
}