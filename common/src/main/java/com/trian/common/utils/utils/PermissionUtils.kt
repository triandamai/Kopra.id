package com.trian.common.utils.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity

class PermissionUtils(private val context: Context) {
    companion object{
        val listPermission: List<String> = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        const val PERMISSION_CODE_REQUEST = 321

    }

    /**
     * check if app already have permission
     * @return Boolean
     * **/
    fun checkHasPermission():Boolean=
                (ActivityCompat.checkSelfPermission(context, listPermission[0]) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, listPermission[1]) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, listPermission[2]) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, listPermission[3]) == PackageManager.PERMISSION_GRANTED &&
                //storage permission
                ActivityCompat.checkSelfPermission(context, listPermission[4]) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, listPermission[5]) == PackageManager.PERMISSION_GRANTED)

    /**
     * request permission
     * @param activity
     * @return void
     * **/
    fun requestPermission(activity: Activity){
        ActivityCompat.requestPermissions(activity,
            listPermission.toTypedArray(), PERMISSION_CODE_REQUEST)
    }
    /**
     * after request and check if result granted or no
     * @param requestCode
     * @param grantResults
     * @return boolean
     * **/
    fun checkPermissionRequestResult(requestCode:Int,grantResults:Array<Int>) =
            requestCode == PERMISSION_CODE_REQUEST &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED &&
            grantResults[2] == PackageManager.PERMISSION_GRANTED &&
            grantResults[3] == PackageManager.PERMISSION_GRANTED &&
            //storage permission
            grantResults[4] == PackageManager.PERMISSION_GRANTED &&
            grantResults[5] == PackageManager.PERMISSION_GRANTED;
}