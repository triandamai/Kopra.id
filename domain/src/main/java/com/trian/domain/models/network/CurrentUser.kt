package com.trian.domain.models.network

import com.trian.domain.models.User


sealed class CurrentUser(val user: User?){
    class Loading():CurrentUser(null)
    class HasUser(user: User) : CurrentUser(user)
    class UserNotComplete(user: User) : CurrentUser(user)
    class NoUser() : CurrentUser(null)
}
