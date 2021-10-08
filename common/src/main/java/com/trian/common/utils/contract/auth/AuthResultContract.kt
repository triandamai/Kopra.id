package com.trian.common.utils.contract.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

class AuthResultContract:ActivityResultContract<Int,Task<GoogleSignInAccount>?>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        val gso= getGoogleSignInClient(context)
        gso.signOut()
        return gso.signInIntent
    }


    override fun parseResult(resultCode: Int, intent: Intent?): Task<GoogleSignInAccount>? {
        return when (resultCode){
            Activity.RESULT_OK->{
                return GoogleSignIn.getSignedInAccountFromIntent(intent)
            }
            else -> null
        }
    }

}