package com.gianghv.android.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseRoom(
    @PrimaryKey val id: String,
    val name: String,
    val desc: String,
    val type: String,
    val status: String,
    val countPeople: Int,
    val price: Int,
    val active: String,
    val createdAt: String,
    val updatedAt: String
)
