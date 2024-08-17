package com.gianghv.android.views.main.history

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentHistoryBinding
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.Project
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show
import com.gianghv.android.views.main.project.ProjectListFAB
import com.gianghv.android.views.main.project.adapter.ProjectListAdapter
import com.gianghv.android.views.main.project.getProjectListFAB
import com.gianghv.android.views.main.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModels()
    private val adapter: ProjectListAdapter by lazy { ProjectListAdapter() }

    override val layoutRes: Int
        get() = R.layout.fragment_history

    override fun init() {
        binding.listProject.adapter = adapter
        binding.listProject.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setUp() {
        viewModel.projects.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.containerNoInCharge.show()
                binding.containerHasInCharge.gone()
            } else {
                binding.containerNoInCharge.gone()
                binding.containerHasInCharge.show()
                setData(it)
            }
        }
    }

    override fun initData() {
        viewModel.getHistory()
    }

    private fun setData(data: List<Project>) {
        adapter.setData(data)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater)
    }
}
