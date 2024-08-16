package com.gianghv.android.views.main.incharge.viewmodel

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
class InChargeViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {
    private val _inCharge = MutableLiveData<List<Project>>(emptyList())
    val inCharge: LiveData<List<Project>> get() = _inCharge


    fun getInCharge() {
        runFlow(Dispatchers.IO) {
            projectRepository.getInCharge().collect {
                Timber.d("get in charge $it")
                _inCharge.postValue(it)
            }
        }
    }

    fun MutableLiveData<List<Project>>.addValue(value: Project) {
        val list = this.value?.toMutableList() ?: mutableListOf()
        list.add(value)
        this.value = list
    }
}
