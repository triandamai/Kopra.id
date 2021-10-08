package com.trian.common.utils.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri


fun Uri.getBitmap(c:ContentResolver):Bitmap?{
    return try {
        val input =  c.openInputStream(this)
        BitmapFactory.decodeStream(input)
    }catch (e:Exception){
        null
    }
}