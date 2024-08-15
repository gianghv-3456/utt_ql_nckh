package com.gianghv.android.views.signup

import com.gianghv.android.domain.UserRole
import java.io.Serializable

data class RegisterInfo(
    val name: String,
    val email: String,
    val password: String,
    val birthday: String,
    val role: UserRole
) : Serializable
