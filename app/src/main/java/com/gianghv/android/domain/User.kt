package com.gianghv.android.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class User(
    open val id: Int,
    open val name: String,
    open val email: String,
    open val role: String,
    open val dob: String
) : Parcelable
