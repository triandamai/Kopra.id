package com.trian.module.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.ui.theme.BluePrimary
import com.trian.component.ui.theme.ColorFontFeatures
import com.trian.component.ui.theme.ColorGray
import com.trian.component.utils.coloredShadow
import com.trian.module.R
import compose.icons.Octicons
import compose.icons.octicons.*


@Composable
fun PageDetailDoctor(m:Modifier=Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        ComponentTopDetailDoctor()
        BodySection()
    }
}

@Composable
private fun ComponentTopDetailDoctor(m: Modifier=Modifier){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = m.background(color = ColorGray)
            .padding(10.dp)
    ){
        Image(
            painter = painterResource(
                id = R.drawable.dummy_doctor
            ),
            contentDescription = "",
            modifier = m
                .clip(
                    shape = CircleShape
                )
                .height(height = 80.dp)
                .width(width = 80.dp),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = m.height(10.dp))
        Text(
            text = "Dr. Yakob Sitogar",
            style = MaterialTheme.typography.h1.copy(
                fontSize = 12.sp,
                letterSpacing = 0.1.sp,
                fontWeight = FontWeight.Medium
            ),
        )
        Text(
            text = "Obgyn",
            style = MaterialTheme.typography.h1.copy(
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )
        )
        Spacer(modifier = m.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Box(modifier= m
                .clip(CircleShape)
                .height(20.dp)
                .width(20.dp)
                .background(color = BluePrimary.copy(alpha = 0.2f))
            ){
                Icon(Octicons.Megaphone24,
                    contentDescription = "",
                    modifier = m.padding(5.dp),
//                    tint = Color.White
                )
            }
            Spacer(modifier = m.width(5.dp))
            Box(modifier= m
                .clip(CircleShape)
                .height(20.dp)
                .width(20.dp)
                .background(color = BluePrimary.copy(alpha = 0.2f))
            ){
                Icon(Octicons.CommentDiscussion24,
                    contentDescription = "",
                    modifier = m.padding(5.dp),
//                    tint = Color.White
                )
            }
        }

    }
}


@Composable
private fun BodySection(m: Modifier=Modifier){
    Column(
        modifier = m
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(topStart = 10.dp,topEnd = 10.dp))
    ) {
        Column(modifier = m.padding(10.dp)) {
            Text(text = "About Doctor",
                style = MaterialTheme.typography.h1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                )
            )
            Spacer(modifier = m.height(5.dp))
            Text(
                text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 12.sp,
                    letterSpacing = 0.1.sp
                )
            )
        }
        Column(modifier = m.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text(text = "Reviews",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = m.width(5.dp))
                Icon(
                    Octicons.StarFill24,
                    contentDescription = "",
                    tint = Color.Yellow,
                    modifier = m.height(15.dp)
                )
                Spacer(modifier = m.width(2.dp))
                Text(text = "4.9",
                    style = MaterialTheme.typography.h1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp
                    )
                )
                Spacer(modifier = m.width(2.dp))
                Text(text = "(123)",
                    style = MaterialTheme.typography.h1.copy(
                        fontSize = 12.sp,
                        letterSpacing = 0.1.sp,
                        color = ColorGray
                    ),
                )
            }
        }
        CardReview()
    }
}

@Composable
private fun CardReview(m: Modifier=Modifier){
        Column(
            modifier = m
                .background(color = Color.White)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = m
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(id = R.drawable.dummy_doctor),
                        contentDescription = "",
                        modifier = m
                            .clip(shape = CircleShape)
                            .width(80.dp)
                            .height(80.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = m.width(10.dp))
                    Column() {
                        Text(text = "Nur Kholid",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                                letterSpacing = 0.1.sp
                            )
                        )
                        Text(text = "1 Day Ago",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 16.sp,
                                letterSpacing = 0.1.sp,
                                color = ColorGray
                            )
                        )
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        Octicons.StarFill24,
                        contentDescription = "",
                        tint = Color.Yellow,
                        modifier = m.height(20.dp)
                    )
                    Spacer(modifier = m.width(2.dp))
                    Text(text = "4.9",
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            letterSpacing = 0.1.sp
                        )
                    )
                }
            }
            Text(
                text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.",
                modifier = m.padding(10.dp),
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    letterSpacing = 0.1.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun PreviewPageDetailDoctor(){
    PageDetailDoctor()
}