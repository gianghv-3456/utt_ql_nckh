package com.gianghv.android.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseRoomEvaluation(
    @PrimaryKey
    val id: String,
    val content: String,
    val star: Int
)
