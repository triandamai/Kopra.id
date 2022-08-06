package app.trian.kopra.ui.pages.auth

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.trian.kopra.MainViewModel

import kotlinx.coroutines.CoroutineScope
import com.trian.component.R
import com.trian.component.Routes
import com.trian.data.model.checkShouldUpdateProfile

/**
 * Splash Screen Page
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 03/09/2021
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun PageSplashScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel,
    nav:NavHostController,
    scope:CoroutineScope,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    LaunchedEffect(key1 = scaffoldState){
        mainViewModel.getCurrentUser { hasUser, user ->
            if(hasUser){
                if(user.checkShouldUpdateProfile()){
                    nav.navigate(Routes.COMPLETE_PROFILE){
                        launchSingleTop = true
                        popUpTo(Routes.SPLASH){
                            inclusive = true
                        }
                    }
                }else{
                    nav.navigate(Routes.DASHBOARD){
                        launchSingleTop = true
                        popUpTo(Routes.SPLASH){
                            inclusive = true
                        }
                    }
                }

            }else{
                nav.navigate(Routes.LOGIN){
                    launchSingleTop = true
                    popUpTo(Routes.SPLASH){
                        inclusive = true
                    }
                }
            }
        }
    }





    Scaffold(modifier = modifier.fillMaxHeight(),
    bottomBar = {
        //version
    }) {
        Column(modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_kopra),
                modifier= modifier
                    .width(150.dp)
                    .height(150.dp),
                contentDescription = "Logo Cexup"
            )
        }
    }


}
@Preview
@Composable
fun PreviewSplashScreen(){
    PageSplashScreen(mainViewModel = viewModel(),nav = rememberNavController() , scope = rememberCoroutineScope())
}