package com.gianghv.android.repository

import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class ProjectRepositoryImpl : ProjectRepository {

    override suspend fun getInChargeResearcher(userId: Int): Flow<Project?> {
        val result =
            FakeData.projects.filter { project ->
                project.state !in listOf(
                    ProjectState.COMPLETED,
                    ProjectState.CANCELLED
                )
            }
                .find { project -> project.researcher.any { it.id == userId } }
        Timber.d("get in charge $result")
        if (result != null) {
            return flowOf(result)
        } else {
            return flowOf(null)
        }
    }

    override suspend fun getInChargeSupervisor(userId: Int): Flow<List<Project>> {
        val result = FakeData.projects.filter { project ->
            project.state !in listOf(
                ProjectState.COMPLETED,
                ProjectState.CANCELLED
            )
        }.filter { project -> project.supervisor.any { it.id == userId } }
        Timber.d("get in charge $result")
        return flowOf(result)
    }

    override suspend fun getAllProject(): Flow<List<Project>> {
        val result = FakeData.projects
        Timber.d("get projects $result")
        return flowOf(result)
    }

    override suspend fun getProjectHistorySupervisor(userId: Int): Flow<List<Project>> {
        val result = FakeData.projects.filter { project -> project.state == ProjectState.COMPLETED }
            .filter { project -> project.supervisor.any { it.id == userId } }
        Timber.d("get projects Supervisor $result")
        return flowOf(result)
    }

    override suspend fun getProjectHistoryResearcher(userId: Int): Flow<List<Project>> {
        val result = FakeData.projects.filter { project -> project.state == ProjectState.COMPLETED }
            .filter { project -> project.researcher.any { it.id == userId } }
        Timber.d("get projects Researcher $result")
        return flowOf(result)
    }
}
