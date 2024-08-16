package com.gianghv.android.repository

import com.gianghv.android.domain.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun getInCharge(): Flow<List<Project>>

}
