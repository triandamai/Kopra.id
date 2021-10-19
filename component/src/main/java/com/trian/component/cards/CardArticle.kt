package com.trian.component.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trian.component.R
import com.trian.component.utils.webview.HtmlTextRenderer
import com.trian.domain.models.Article

/**
 * `Card Article
 * Author PT Cexup Telemedhicine
 * Created by Kholid Barat Daya
 * 03/09/2021
 */
@Composable
fun CardArticle(
    article: Article,
    onClick: (article: Article, index: Int) -> Unit
) {

    Card(
        shape = RoundedCornerShape(topEndPercent = 30),
    ) {
        Image(painter = painterResource(id = R.drawable.dummy_doctor),contentScale = ContentScale.Crop, contentDescription = "",modifier = Modifier.fillMaxWidth())
        Column(
            modifier =
            Modifier.padding(
                all = 10.dp,
            )
        ) {
            Text(
                text = article.title,
                color = Color.White,
                fontSize = 30.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 30.dp)
            )
        }
    }
}

@Composable
fun CardArticleRow(
    modifier: Modifier=Modifier,
    index:Int=0,
    article: Article,
    onClick:(article:Article,index:Int)->Unit
){
   Card(
       shape = RoundedCornerShape(size = 10.dp),
       elevation=0.dp,
       modifier = modifier.padding(10.dp).clickable { onClick(article, index) }
   ) {
       Column {
           Image(
               painter = painterResource(id = R.drawable.dummy_doctor),
               contentDescription = "image",
               contentScale = ContentScale.Crop,
               modifier = Modifier.fillMaxWidth().heightIn(min=20.dp,max=150.dp)
           )
           Row(
               modifier = modifier
                   .padding(12.dp)
                   .fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceBetween,
           ){
               Text(
                   text = article.category,
                   fontSize = 11.sp,
                   fontWeight = FontWeight.Normal,
                   color = Color(0xFF6E798C))
               Text(
                   text = "20 Days Ago",
                   fontSize = 11.sp,
                   fontWeight = FontWeight.Normal,
                   color = Color(0xFF6E798C),

               )
           }
           Text(
               text = article.title,
                style= TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF081F32),

                ),
               maxLines=2,
               overflow= TextOverflow.Ellipsis,
               modifier = modifier.padding(horizontal = 12.dp)
           )
           Spacer(modifier = modifier.height(5.dp))
//           Text(
//               text = article.content,
//               fontSize = 13.sp,
//               fontWeight = FontWeight.Normal,
//               maxLines = 3,
//               color = Color(0xFF374A59),
//               modifier = modifier.padding(horizontal = 12.dp) ,
//               overflow = TextOverflow.Ellipsis,
//           )
           Spacer(modifier = modifier.height(5.dp))
           Row(modifier = modifier
               .fillMaxWidth()
               .padding(12.dp),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.SpaceBetween,
           ) {
              Row(verticalAlignment = Alignment.CenterVertically){
                  Image(
                      painter = painterResource(id = R.drawable.dummy_doctor),
                      contentDescription = "icon",
                      modifier = modifier
                          .size(16.dp)
                          .clip(CircleShape)
                          .fillMaxWidth(),
                      contentScale = ContentScale.Crop,
                  )
                  Spacer(modifier = modifier.width(10.dp))
                  Text(text = stringResource(R.string.datadummyarticlename),style = MaterialTheme.typography.subtitle1)
              }
           }
       }
   }
}

@ExperimentalMaterialApi
@Composable
fun CardArticleColumn(modifier:Modifier=Modifier, index:Int=0, article:Article, onClick:(article:Article, index:Int)->Unit){
    Card(shape = RoundedCornerShape(10.dp),
    onClick = {onClick(article, index)}){
        Row(){
            Image(painter = painterResource(id = R.drawable.dummy_doctor,),modifier = modifier
                .height(140.dp)
                .width(120.dp)
                .fillMaxWidth(),
                contentDescription = "Image Article",
                contentScale= ContentScale.Crop)
            Column(modifier = modifier.padding(5.dp)) {
                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()){
                    Text(text = article.category,fontSize = 10.sp)
                    Text(text = "20 Days Ago",fontSize = 10.sp)
                }
                Spacer(modifier = modifier.height(10.dp))
                Text(text = article.title,fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF081F32),)
                Spacer(modifier = modifier.height(10.dp))
                Text(text = article.content,fontSize = 10.sp,maxLines = 3,overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCardArticle(){
    CardArticle(article = Article(
        category = "TIPS KESEHATAN",
        category_slug = "tips-kesehatan",
        content = stringResource(R.string.datadummyarticlesubcaption),
        id = 1,
        slug = "5-cara-menjaga-kesehatan-tubuh",
        thumb = "",
        thumb_original = "",
        title =stringResource(R.string.datadummyarticlecaption),
    ),onClick = {article, index ->  })
}

@Preview
@Composable
fun PreviewCardArticleFullWidth(){
    CardArticleRow(
        article = Article(
            category = "TIPS KESEHATAN",
            category_slug = "tips-kesehatan",
            content = stringResource(R.string.datadummyarticlesubcaption),
            id = 1,
            slug = "5-cara-menjaga-kesehatan-tubuh",
            thumb = "",
            thumb_original = "",
            title =stringResource(R.string.datadummyarticlecaption),
        ),onClick = { article, index ->  })
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewCardArticleDetail(){
    CardArticleColumn(
        onClick = {article, index ->  },
        article = Article(
            category = "TIPS KESEHATAN",
            category_slug = "tips-kesehatan",
            content = stringResource(R.string.datadummyarticlesubcaption),
            id = 1,
            slug = "5-cara-menjaga-kesehatan-tubuh",
            thumb = "",
            thumb_original = "",
            title =stringResource(R.string.datadummyarticlecaption),
        )
    )
}