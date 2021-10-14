package com.trian.component.utils.webview

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.startActivity

/**
 * `Support Different Screen Size`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 14/10/2021
 */
class WebClientView(val context: Context) :WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == "www.example.com") {
            // This is my web site, so do not override; let my WebView load the page
            return false
        }
        // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            startActivity(context,this,null)
        }
        return true
    }
}