package com.trian.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.datastore.preferences.core.preferencesKey
import com.google.gson.Gson
import com.trian.domain.models.User


/**
 * Persistence Class
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 22/10/2021
 */

@SuppressLint("CommitPrefEdits")
class Persistence(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson = Gson()
) {
    val editor :SharedPreferences.Editor = sharedPreferences.edit()

    /**
    * Auth login user
    * save data user if already logged in
    * @param {com.trian.domain.entities.User}
    * @returns void
    * */
    private  val key_user = "user"
     fun setUser(user: User){
         editor.putString(key_user,gson.toJson(user))
         editor.apply()
    }

     fun getUser():User?{
         val data = sharedPreferences.getString(key_user,null)
         if(data != null) {
             return  gson.fromJson(data, User::class.java)

         }
         return null
    }

    fun dropUser(){
        editor.remove(key_user)
        editor.apply()
    }


    /**
     * Token
     *
     * */

    private val key_token = "key_token"
    fun setToken(token:String){
        editor.putString(key_token,token)
        editor.apply()
    }

    fun getToken():String?{
        return sharedPreferences.getString(key_token,null)
    }

    fun dropToken(){
        editor.remove(key_token)
        editor.apply()
    }

    /**
     * function that can store dynamic data and key
     *
     * */
    fun setItem(key:String,value:Any){
        when(value){
            is Boolean -> editor.putBoolean(key,value)
            is String -> editor.putString(key,value)
            is Int -> editor.putInt(key,value)
        }
        editor.apply()
    }

     fun  getItemBoolean(key:String):Boolean  =  sharedPreferences.getBoolean(key, false)
     fun  getItemString(key:String):String?  =  sharedPreferences.getString(key, null)
     fun  getItemInt(key:String):Int?  =  sharedPreferences.getInt(key, 0)





    /**
     * SignOut for both User and Nurse
     *
     * */
    fun signOut(){
        dropUser()
        dropToken()

    }
}

