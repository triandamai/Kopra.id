package com.trian.data.auth

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

val CLIENT_ID = "6vous6vrrd4lnql8hgmactrcm7qsjmak.apps.googleusercontent.com"
fun getGoogleSignInClient(context: Context):GoogleSignInClient{
    val signInOptions = GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN
    )
    //Request id token if you intend to verify google user from your backend server
        .requestIdToken(CLIENT_ID)
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(context,signInOptions)
}