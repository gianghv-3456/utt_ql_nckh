package com.gianghv.android.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supervisor(
    override val id: Long,
    override val name: String,
    override val email: String,
    override val password: String,
    override val role: UserRole,
    override val dob: String,
    val teacherCode: String,
    val title: String,
    val major: String
) : User(
    id,
    name,
    email,
    password,
    role,
    dob
),
    Parcelable
