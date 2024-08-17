package com.gianghv.android.views.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.UserRole
import com.gianghv.android.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(val projectRepository: ProjectRepository) : BaseViewModel() {
    private val _projects = MutableLiveData<List<Project>>()

    val projects: LiveData<List<Project>>
        get() = _projects

    fun getHistory() {
        runFlow(Dispatchers.IO) {
            if (AppState.userRole == UserRole.SUPERVISOR)
                projectRepository.getProjectHistorySupervisor(AppState.userId).collect {
                    _projects.postValue(it)
                }
            else if (AppState.userRole == UserRole.RESEARCHER)
                projectRepository.getProjectHistoryResearcher(AppState.userId).collect {
                    _projects.postValue(it)
                }
        }
    }
}
