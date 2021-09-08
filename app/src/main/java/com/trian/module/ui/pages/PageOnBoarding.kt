package com.trian.module.ui.pages

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.trian.common.utils.route.Routes
import com.trian.component.ui.theme.ColorGray
import com.trian.domain.models.OnBoarding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun PageOnBoarding(modifier: Modifier=Modifier, nav: NavHostController, scope:CoroutineScope){

        Column(modifier = modifier.fillMaxSize()) {
        TopSection(
            onBackPressed = {
              nav.popBackStack()
            },onSkipPressed = {
              nav.navigate(Routes.LOGIN.name)
            }
        )

        val items=OnBoarding.get()
        val state = rememberPagerState(pageCount = items.size)
        HorizontalPager(state = state,modifier= modifier
            .fillMaxSize()
            .weight(0.8f)) {
            page -> OnBoardingItem(item = items[page])
        }
        BottomSection(size = items.size, index = state.currentPage) {
            val stateCurrent = (state.currentPage +1)
            if(stateCurrent < items.size){
                scope.launch {
                    state.scrollToPage(page = state.currentPage+1)
                }

            }else{
                nav.navigate(Routes.LOGIN.name)
            }

        }
    }
}


@Composable
fun OnBoardingItem(m: Modifier=Modifier,item:OnBoarding){
    Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center,modifier =m.fillMaxSize() ) {
        Image(painter = painterResource(id = item.image), contentDescription = "")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = m.padding(15.dp)){
            Text(
                text = stringResource(id = item.title),
                fontSize = 24.sp,
                color = MaterialTheme.colors.onBackground,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = m.height(5.dp))
            Text(
                text = stringResource(id = item.text),
                fontSize = 18.sp,
                color = ColorGray.copy(
                    alpha = 0.8f),textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TopSection(m:Modifier=Modifier,onBackPressed:()->Unit,onSkipPressed:()->Unit){
    Box(modifier= m
        .fillMaxWidth()
        .padding(12.dp)){
        IconButton(onClick = onBackPressed,modifier = m.align(Alignment.CenterStart)) {
            Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = "")
        }

        TextButton(onClick = onSkipPressed,modifier = m.align(Alignment.CenterEnd)) {
            Text(text = "Skip",color=MaterialTheme.colors.onBackground)
        }
    }
}

@Composable
fun BottomSection(modifier: Modifier=Modifier, size: Int, index: Int, onClick:()->Unit){
    Box(modifier = modifier
        .fillMaxWidth()
        .padding(12.dp)){
        Indicators(size = size, index = index)
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier.align(Alignment.CenterEnd),
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
fun PreviewPageOnboard(){
    PageOnBoarding(nav = rememberNavController() , scope = rememberCoroutineScope() )
}