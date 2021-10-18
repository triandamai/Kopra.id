package com.trian.module.ui.pages.main

import android.os.Handler
import android.os.Looper
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.trian.common.utils.network.DataStatus
import com.trian.common.utils.route.Routes
import com.trian.common.utils.utils.getLastDayTimeStamp
import com.trian.common.utils.utils.getTodayTimeStamp
import com.trian.component.cards.*
import com.trian.component.datum.listServices
import com.trian.domain.models.Product
import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.data.viewmodel.MainViewModel
import com.trian.data.viewmodel.TelemedicineViewModel
import com.trian.domain.models.ServiceType

/**
 * Dashboard Dashboard
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 10/09/2021
 */
@ExperimentalAnimationApi
@Composable
fun DashboardHome(
    modifier: Modifier =Modifier,
    scaffoldState: ScaffoldState= rememberScaffoldState(),
    scrollState: ScrollState,
    nav: NavHostController,
    viewModel:MainViewModel,
    telemedicineViewModel: TelemedicineViewModel,
    scope: CoroutineScope,
    toFeature:(feature:ServiceType)->Unit
){
    val stateAnimation = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = false
        }
    }


    val user by viewModel.user
    val name = user?.name ?: ""
    val article by telemedicineViewModel.articleStatus.observeAsState()
    val product by telemedicineViewModel.productStatus.observeAsState()


   LaunchedEffect(key1 = scaffoldState){
       viewModel.getDetailHealthStatus(getLastDayTimeStamp(), getTodayTimeStamp())
       telemedicineViewModel.getListArticle {  }
       telemedicineViewModel.getProduct()
   }
    scope.run {
        Handler(Looper.myLooper()!!).postDelayed({
            stateAnimation.targetState = true
        },500)
    }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {

        Row(modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Hello!",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.dummy_profile),
                modifier= modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale= ContentScale.FillWidth,
                contentDescription = ""
            )
        }
        CardHeaderSection(title = "Health Status", moreText = "Details") {
            nav.navigate(Routes.DETAIL_HEALTH)
        }
        CardHealthStatus(state = stateAnimation,viewModel = viewModel,scope = scope)
        CardHeaderSection(title = "Services", moreText = "More") {
            nav.navigate(Routes.SHEET_SERVICE)
        }
        LazyRow(modifier = modifier.padding(vertical = 8.dp,horizontal = 4.dp)){
            items(count=3,itemContent = {index:Int->
                CardServices(service = listServices[index], onClick ={toFeature(it.type) } ,index=index)
            })
        }
        CardHeaderSection(title = "Shop", moreText = "More") {
            //to list shop/all product
        }

        LazyRow(modifier = modifier.padding(vertical = 8.dp)){
            when(product){
                is DataStatus.HasData->{
                    val size = article?.let { it.data?.let { it1-> if(it1.size > 5) { 5 }else{ it1.size } }?:0 }?:0
                    items(count=size,itemContent = {index:Int->
                        if(index == 0){
                            Spacer(modifier = Modifier.width(14.dp))
                        }
                        CardProduct(product = product?.data!![index],
                            index=index,
                            onClick ={}
                        )
                    })
                }
            }
        }
        CardHeaderSection(title = "News", moreText = "More") {
            nav.navigate(Routes.LIST_ARTICLE){
                launchSingleTop = true
            }
        }
        LazyRow(){
           when(article){
               is DataStatus.HasData -> {
                   val size = article?.let { it.data?.let { it1-> if(it1.size > 5) { 5 }else{ it1.size } }?:0 }?:0
                   items(count=size,itemContent = {index:Int->
                       if(index == 0){
                           Spacer(modifier = Modifier.width(14.dp))
                       }
                       CardArticleRow(article = article?.data!![index],index=index, onClick ={
                         article, index ->
                           nav.navigate(Routes.READ_ARTICLE){
                               launchSingleTop=true
                           }
                       })
                   })
               }
               is DataStatus.Loading -> {}
               is DataStatus.NoData -> {}
               null -> { }
           }

        }
        CardAppVersion()
        Spacer(modifier = modifier.height(70.dp))
    }
}
