package com.gianghv.android.repository

import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ResearcherReport
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun getInChargeResearcher(userId: Int): Flow<Project?>
    suspend fun getInChargeSupervisor(userId: Int): Flow<List<Project>>
    suspend fun getAllProject(): Flow<List<Project>>
    suspend fun getProjectHistorySupervisor(userId: Int): Flow<List<Project>>
    suspend fun getProjectHistoryResearcher(userId: Int): Flow<List<Project>>

    suspend fun addResearcherReport(report: ResearcherReport)
}
