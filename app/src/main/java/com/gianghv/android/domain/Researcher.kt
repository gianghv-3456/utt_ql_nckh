package com.gianghv.android.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Researcher(
    @Json(name = "id")
    override val id: Long,

    @Json(name = "name")
    override val name: String,

    @Json(name = "email")
    override val email: String,

    @Json(name = "password")
    override val password: String,

    @Json(name = "role")
    override val role: UserRole,

    @Json(name = "dob")
    override val dob: String,

    @Json(name = "studentCode")
    val studentCode: String,

    @Json(name = "className")
    val className: String,

    @Json(name = "major")
    val major: String
) : User(
    id, name, email, password, role, dob
), Parcelable


