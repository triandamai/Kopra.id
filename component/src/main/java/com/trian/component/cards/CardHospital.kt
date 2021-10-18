package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.trian.component.R
import com.trian.component.ui.theme.*
import com.trian.domain.models.Hospital
import compose.icons.Octicons
import compose.icons.octicons.Location24




@Composable
fun CardHospital(
    modifier: Modifier = Modifier,
    index: Int = 0,
    hospital: Hospital,
    onClick: (hospital: Hospital, index: Int) -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp)
            .clickable { onClick(hospital, 1) },
        elevation = 0.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = when (index) {
                        0 -> 16.dp
                        else -> 8.dp
                    }, bottom = 8.dp, start = 16.dp, end = 16.dp
                )
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            Box(modifier = Modifier.height(120.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.hospital),
                    contentDescription = "Picture hospital",
                    contentScale = ContentScale.Crop,
                    modifier = modifier.clip(shape = RoundedCornerShape(5.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Image(
                        painter = rememberImagePainter(data = hospital.thumb.toString(), builder = {crossfade(true)}),
                        contentDescription = "Logo Hospital",
                        modifier = Modifier
                            .size(90.dp)
                            .padding(8.dp),
                    )
                }
            }
            Spacer(modifier = modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = hospital.name,
                    style = TextStyle(color = Color.Black.copy(0.9f), fontSize = 16.sp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold

                )
                Spacer(modifier = modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Icon(
                        Octicons.Location24,
                        contentDescription = "location",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Blue
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = hospital.address?:"",
                        style = TextStyle(color = Color.DarkGray, fontSize = 14.sp),
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = modifier.height(10.dp))
        }

    }

}

@Preview
@Composable
fun PreviewCardHospital(){
    TesMultiModuleTheme() {
       CardHospital(
           hospital =
           Hospital(
                id = 1,
                slug = "rs-tele-cexup",
                description = "",
                thumb = "",
                thumb_original = "",
                name = "RS Tele Cexup",
                address = "Jl. Jakarta Barat RT005/003, Meruya",
                others = "")
       ) { hospital, index -> }
    }
}