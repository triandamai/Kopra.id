package com.trian.data.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.trian.domain.entities.Nurse
import com.trian.domain.entities.User
import kotlin.reflect.typeOf

/**
 * Persistence Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */


@SuppressLint("CommitPrefEdits")
class Peristence(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    lateinit var editor: SharedPreferences.Editor
    init {
        editor = sharedPreferences.edit()
    }
    /**
    * Auth login user
    * save data user if already logged in
    * @param {com.trian.domain.entities.User}
    * @returns void
    * */
    private  val key_user = "user"
    @SuppressLint("CommitPrefEdits")
    fun setUser(user:User){
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
     * Auth login user
     * save data user if already logged in
     * @param Nurse
     * */
    private val key_nurse = "key_nurse"

    fun setNurse(nurse: Nurse){
        editor.putString(key_nurse,gson.toJson(nurse))
        editor.apply()
    }
    fun getNurse():Nurse?{
        val data = sharedPreferences.getString(key_nurse,null)
        if(data != null){
            return gson.fromJson(data,Nurse::class.java)
        }
        return null
    }

    fun dropNurse(){
        editor.remove(key_nurse)
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

     fun  getItem(key:String,type:Any):Any? = when(type) {
         is Boolean -> sharedPreferences.getBoolean(key, false)
         is String -> sharedPreferences.getString(key, null)
         is Int -> sharedPreferences.getInt(key, 0)
         else -> null
     }

    /**
     * SignOut for both User and Nurse
     *
     * */
    fun signOut(){
        dropUser()
        dropNurse()
        dropToken()
    }
}