package com.trian.data.model.network

import com.trian.data.model.User


sealed class CurrentUser(val user: User?){
    class Loading():CurrentUser(null)
    class HasUser(user: User) : CurrentUser(user)
    class UserNotComplete(user: User) : CurrentUser(user)
    class NoUser() : CurrentUser(null)
}
