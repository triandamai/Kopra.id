package com.trian.module.ui.pages.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.route.Routes
import com.trian.component.appbar.AppBarDetail
import com.trian.component.cards.CardArticle
import com.trian.component.cards.CardArticleColumn
import com.trian.component.ui.theme.LightBackground
import com.trian.component.ui.theme.TesMultiModuleTheme
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.Article


@ExperimentalMaterialApi
@Composable
fun PageListArticle(
    m:Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    nav: NavHostController,
    telemedicineViewModel: TelemedicineViewModel,
){
    val article by telemedicineViewModel.articleStatus.observeAsState()
    LaunchedEffect(key1 = scaffoldState) {
        telemedicineViewModel.getListArticle {  }
    }
    Scaffold(
        topBar = {
            AppBarDetail(page = "Article") {
            }
        }, backgroundColor = LightBackground
    ) {
            LazyColumn(
                modifier = m.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues( vertical = 8.dp),
            content = {
                when(article){
                    is DataStatus.NoData ->{
                        Log.e("Article:","No Data")
                    }
                    is DataStatus.Loading ->{

                    }
                    is DataStatus.HasData ->{
                        items(
                            count = article?.data!!.size,
                            itemContent = { index ->
                                CardArticleColumn(
                                    article = article?.data!![index],
                                    onClick = { articles, i ->
                                        nav.navigate("${Routes.READ_ARTICLE}/${article?.data!![index].slug}") }
                                )
                            }
                        )
                    }
                }
            })
    }
    
}


@Preview
@Composable
fun PreviewListArticle2(){
    TesMultiModuleTheme {
//        PageListArticle()
    }
}