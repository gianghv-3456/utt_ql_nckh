package com.gianghv.android.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Supervisor(
    override val id: Int,
    override val name: String,
    override val email: String,
    override val role: String,
    override val dob: String,
    val teacherCode: String,
    val title: String,
    val major: String
) : User(
    id, name, email, role, dob
), Parcelable

