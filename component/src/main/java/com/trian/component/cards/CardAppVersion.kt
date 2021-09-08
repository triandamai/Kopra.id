package com.trian.component.cards

import android.content.pm.PackageInfo
import android.os.Build
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp







/**
 * `Card App Version`
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 08/09/2021
 */

@Composable
fun CardAppVersion(){
    val context = LocalContext.current
    val pinfo: PackageInfo = context.packageManager.getPackageInfo(context.packageName,0)


    val versionName: String = pinfo.versionName
    Text(
        text ="V$versionName",
        modifier= Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        textAlign = TextAlign.Center
    )
}