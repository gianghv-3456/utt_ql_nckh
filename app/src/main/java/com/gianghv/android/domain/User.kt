package com.gianghv.android.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class User(
    open val id: Long,
    open val name: String,
    open val email: String,
    open val password: String = "",
    open val role: UserRole,
    open val dob: String
) : Parcelable
