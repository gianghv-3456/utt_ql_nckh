package com.gianghv.android.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseUser(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val role: String
)
