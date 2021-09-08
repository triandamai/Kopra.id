package com.trian.module.ui.pages

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.domain.models.OnBoarding
import com.trian.module.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalPagerApi
@Composable
fun PageOnBoarding(nav: NavHostController,scope:CoroutineScope,page:Int=0) {
   ComponentOnBoarding(scope = scope, onNavigate = {
       nav.navigate(it)
   })
}

@ExperimentalPagerApi
@Composable
fun ComponentOnBoarding(scope:CoroutineScope,onNavigate:(String)->Unit,modifier: Modifier = Modifier){
    val pagerState = rememberPagerState(pageCount = 3)
    fun move(index:Int){
        scope.launch {
            pagerState.animateScrollToPage(index)
        }
    }
    Scaffold(modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        HorizontalPager(state = pagerState) {
                page: Int ->
            when(page){
                0 -> OnBoardPage(modifier,page ,onNavigate)

                1 -> OnBoardPage(modifier,page ,onNavigate)

                2 -> OnBoardPage(modifier,page,onNavigate )

                else -> OnBoardPage(modifier,page,onNavigate)
            }
        }
    }
}

@Composable
fun OnBoardPage(modifier: Modifier,page:Int=0,onNavigate: (String) -> Unit){

        Column(modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.onboard), contentDescription = "")
            Text(text = "Teleconsultation",style = TextStyle(fontSize = 16.sp,fontWeight = FontWeight.Bold))
            Text(
                text = "Dengan Digital Health Sensors & Video Call,anda dapat konsultasi ke dokter selayaknya anda bertemu di Rumah Sakit.",
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light),
            textAlign = TextAlign.Center,
            softWrap = true)
            if(page == 2){
                Button(onClick = { onNavigate(Routes.LOGIN.name) }) {
                    Text(text = "Logn")
                }

            }

    }
}


@ExperimentalPagerApi
@Composable
@Preview
fun PageOnBoarder(m: Modifier=Modifier){
    val scope = rememberCoroutineScope()
    Column(modifier = m.fillMaxSize()) {
    TopSection()
        val items=OnBoarding.get()
        val state = rememberPagerState(pageCount = items.size)
        HorizontalPager(state = state,modifier= m
            .fillMaxSize()
            .weight(0.8f)) {
            page -> OnBoardingItem(item = items[page])
        }
        BottomSection(size = items.size, index = state.currentPage) {
         if(state.currentPage+1<items.size)
            scope.launch {
                state.scrollToPage(page = state.currentPage+1)
            }
        }
    }
}


@Composable
fun OnBoardingItem(m: Modifier=Modifier,item:OnBoarding){
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,modifier =m.fillMaxSize() ) {
        Image(painter = painterResource(id = item.image), contentDescription = "")
        Text(
            text = stringResource(id = item.title),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(id = item.text),
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground.copy(
                alpha = 0.8f),textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TopSection(m:Modifier=Modifier){
    Box(modifier= m
        .fillMaxWidth()
        .padding(12.dp)){
        IconButton(onClick = { /*TODO*/ },modifier = m.align(Alignment.CenterStart)) {
            Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
        }

        TextButton(onClick = { /*TODO*/ },modifier = m.align(Alignment.CenterEnd)) {
            Text(text = "Skip",color=MaterialTheme.colors.onBackground)
        }
    }
}

@Composable
fun BottomSection(m: Modifier=Modifier,size: Int,index: Int,onClick:()->Unit){
    Box(modifier = m
        .fillMaxWidth()
        .padding(12.dp)){
        Indicators(size = size, index = index)
        FloatingActionButton(
            onClick = onClick,
            modifier = m.align(Alignment.CenterEnd),
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, contentDescription = "")
        }
    }
}

@Composable
fun BoxScope.Indicators(m: Modifier=Modifier,size:Int,index:Int){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = m.align(Alignment.CenterStart),
    ){
        repeat(size){
            Indicator(isSelected = it == index)
        }
    }

}

@Composable
fun Indicator(m:Modifier=Modifier,isSelected:Boolean){
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp,
    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy))
    Box(modifier = m
        .height(10.dp)
        .width(width.value)
        .clip(CircleShape)
        .background(
            color =
            if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground.copy(
                alpha = 0.5f
            )
        )
    ) {

    }
}

@ExperimentalPagerApi
@Preview
@Composable
fun PreviewComponentOnBoarding(){
    ComponentOnBoarding(scope = rememberCoroutineScope(), onNavigate ={})
}