package com.trian.module.ui.pages

import android.annotation.SuppressLint
import android.util.Log
import android.view.ContextThemeWrapper
import android.webkit.WebView
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.trian.component.utils.webview.WebClientView

/**
 * Dashboard Page Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 14/10/2021
 */

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PageShowArticle(
    nav:NavController
){
    Log.e("Slug article", nav.currentBackStackEntry?.arguments?.getString("slug").toString())
    val context = LocalContext.current
    Scaffold(
        topBar = {
            
        }
    ) {
        AndroidView(
            factory ={
                WebView(ContextThemeWrapper(it,com.trian.component.R.style.Chart))
                    .apply {
                        loadUrl("https://cexup.com/article/show/${nav.currentBackStackEntry?.arguments?.getString("slug")}")
                        settings.apply {
                            javaScriptEnabled = true
                        }
                        webViewClient = WebClientView(context = context)


                    }
                     },
            update = {
                view->
            }
        )
    }
}