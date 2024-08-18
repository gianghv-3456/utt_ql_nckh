package com.gianghv.android.views.main.project

import android.content.Intent
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentProjectBinding
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.Project
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show
import com.gianghv.android.views.create.AddActivity
import com.gianghv.android.views.main.project.adapter.ProjectListAdapter
import com.gianghv.android.views.main.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {
    private val viewModel: ProjectViewModel by viewModels()
    private val adapter: ProjectListAdapter by lazy { ProjectListAdapter() }

    override val layoutRes: Int
        get() = R.layout.fragment_project

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

        binding.fab.setOnClickListener {
            Intent(activity, AddActivity::class.java).apply {
                putExtra("type", AddActivity.TYPE_ADD_PROJECT)
                startActivity(this)
            }
        }
    }

    override fun initData() {
        viewModel.getAllProject()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProject()
    }

    private fun setData(data: List<Project>) {
        adapter.setData(data)

        val fabType = getProjectListFAB(AppState.userRole)
        binding.fab.show()
        when (fabType) {
            ProjectListFAB.NEW -> {
                binding.fab.text = "Thêm"
            }

            ProjectListFAB.PROPOSED -> {
                binding.fab.text = "Đề xuất"
            }
        }
    }

    private fun openAddProject() {

    }

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentProjectBinding.inflate(inflater)
}
