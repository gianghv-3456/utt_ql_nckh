package com.gianghv.android.repository

import com.gianghv.android.domain.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class ProjectRepositoryImpl : ProjectRepository {
    override suspend fun getInCharge(): Flow<List<Project>> {
        Timber.d("get in charge ${FakeData.projects}")
        return flowOf(FakeData.projects)
    }

}
