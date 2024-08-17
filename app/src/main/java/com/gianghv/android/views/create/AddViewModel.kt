package com.gianghv.android.views.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.Document
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {

    private val _documents = MutableLiveData<List<Document>>()
    val documents: LiveData<List<Document>> get() = _documents

    private val _supervisors = MutableLiveData<List<Supervisor>>()
    val supervisors: LiveData<List<Supervisor>> get() = _supervisors

    private val _researchers = MutableLiveData<List<Researcher>>()
    val researchers: LiveData<List<Researcher>> get() = _researchers

    fun addDocument(document: Document) {
        val list = _documents.value?.toMutableList() ?: mutableListOf()
        list.add(document)
        _documents.value = list
    }

    fun removeDocument(document: Document) {
        val list = _documents.value?.toMutableList() ?: mutableListOf()
        list.remove(document)
        _documents.value = list
    }

    fun addReport(report: ResearcherReport) {
        runFlow(Dispatchers.IO) {
            projectRepository.addResearcherReport(report)
        }
    }
}
