package com.gianghv.android.views.main.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.Project
import com.gianghv.android.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(val projectRepository: ProjectRepository) : BaseViewModel() {
    private val _projects = MutableLiveData<List<Project>>(emptyList())
    val projects: LiveData<List<Project>> get() = _projects

    fun getAllProject() {
        runFlow(Dispatchers.IO) {
            projectRepository.getAllProject().collect {
                Timber.d("get projects $it")
                _projects.postValue(it)
            }
        }
    }
}
