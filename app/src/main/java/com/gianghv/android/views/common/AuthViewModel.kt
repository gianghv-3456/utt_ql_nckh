package com.gianghv.android.views.common

import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.UserRole
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : BaseViewModel() {
    fun signIn(userName: String, password: String): UserRole? {
        if (userName == "researcher" && password == "123456") {
            return UserRole.RESEARCHER
        }
        if (userName == "supervisor" && password == "123456") {
            return UserRole.SUPERVISOR
        }
        return null
    }
}
