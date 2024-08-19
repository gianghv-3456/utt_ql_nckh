package com.gianghv.android.views.main.incharge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.domain.UserRole
import com.gianghv.android.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InChargeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {
    private val _inCharge = MutableLiveData<Project?>(null)
    val inCharge: LiveData<Project?> get() = _inCharge

    fun getInCharge() {
        runFlow(Dispatchers.IO) {
            if (AppState.userRole == UserRole.RESEARCHER) {
                projectRepository.getInChargeResearcher(AppState.userId).collect {
                    Timber.d("get in charge $it")
                    _inCharge.postValue(it)
                }
            } else {
                projectRepository.getInChargeSupervisor(AppState.userId).collect {
                    Timber.d("get in charge $it")
                    _inCharge.postValue(it.firstOrNull())
                }
            }
        }
    }

    fun addReport(report: ResearcherReport) {
//        runFlow(Dispatchers.IO) {
//            projectRepository.addResearcherReport(report)
//        }
    }

    fun cancelProject() {
        _inCharge.value = inCharge.value?.copy(state = ProjectState.CANCELLED)
    }

    fun pauseProject() {
        _inCharge.value = inCharge.value?.copy(state = ProjectState.PAUSED)
    }

    fun resumeProject() {
        _inCharge.value = inCharge.value?.copy(state = ProjectState.IN_PROGRESS)
    }
}

fun MutableLiveData<List<Project>>.addValue(value: Project) {
    val list = this.value?.toMutableList() ?: mutableListOf()
    list.add(value)
    this.value = list
}
