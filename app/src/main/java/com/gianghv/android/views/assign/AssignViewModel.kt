package com.gianghv.android.views.assign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssignViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {

    private val _supervisors = MutableLiveData<List<Supervisor>>()
    val supervisors: LiveData<List<Supervisor>> get() = _supervisors

    private val _researchers = MutableLiveData<List<Researcher>>()
    val researchers: LiveData<List<Researcher>> get() = _researchers

}
