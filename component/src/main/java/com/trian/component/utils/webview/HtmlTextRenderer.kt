package com.trian.component.utils.webview

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat

/**
 * `Support Different Screen Size`
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 14/10/2021
 */

@Composable
fun HtmlTextRenderer(html:String){
    AndroidView(factory = {
        TextView(it).apply {
            text = HtmlCompat.fromHtml(html,HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    })
}