package com.gianghv.android.repository

import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.ResearcherReport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

interface ProjectRepository {
    suspend fun getInChargeResearcher(userId: Long): Flow<Project?>
    suspend fun getInChargeSupervisor(userId: Long): Flow<List<Project>>
    suspend fun getAllProject(): Flow<List<Project>>
    suspend fun getProjectHistorySupervisor(userId: Long): Flow<List<Project>>
    suspend fun getProjectHistoryResearcher(userId: Long): Flow<List<Project>>

    suspend fun addResearcherReport(report: ResearcherReport, projectId: Int)
    suspend fun addProject(project: Project)
}

class ProjectRepositoryImpl : ProjectRepository {

    override suspend fun getInChargeResearcher(userId: Long): Flow<Project?> {
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

    override suspend fun getInChargeSupervisor(userId: Long): Flow<List<Project>> {
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

    override suspend fun getProjectHistorySupervisor(userId: Long): Flow<List<Project>> {
        val result = FakeData.projects.filter { project -> project.state == ProjectState.COMPLETED }
            .filter { project -> project.supervisor.any { it.id == userId } }
        Timber.d("get projects Supervisor $result")
        return flowOf(result)
    }

    override suspend fun getProjectHistoryResearcher(userId: Long): Flow<List<Project>> {
        val result = FakeData.projects.filter { project -> project.state == ProjectState.COMPLETED }
            .filter { project -> project.researcher.any { it.id == userId } }
        Timber.d("get projects Researcher $result")
        return flowOf(result)
    }

    override suspend fun addResearcherReport(report: ResearcherReport, projectId: Int) {
//        FakeData.reports.add(report)
        val project = FakeData.projects.find { it.id.toInt() == projectId }
//        project?.reports?.add(report)
        val temp = project?.reports?.toMutableList()
        temp?.add(report)
        if (temp != null) {
            project.reports = temp
        }
    }

    override suspend fun addProject(project: Project) {
        FakeData.projects.add(project)

    }
}
