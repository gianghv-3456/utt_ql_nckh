package com.gianghv.android.views.main.incharge

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentInChargeBinding
import com.gianghv.android.domain.UserRole
import com.gianghv.android.util.ext.getStateTagBackground
import com.gianghv.android.util.ext.getStateName
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show
import com.gianghv.android.views.MainActivity
import com.gianghv.android.views.main.incharge.adapter.DocumentAdapter
import com.gianghv.android.views.main.incharge.adapter.ResearcherReportAdapter
import com.gianghv.android.views.main.incharge.viewmodel.InChargeViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class InChargeFragment : BaseFragment<FragmentInChargeBinding>() {
    private val viewModel: InChargeViewModel by viewModels()

    var fabType: InChargeFAB? = null

    override val layoutRes: Int
        get() = R.layout.fragment_in_charge

    var activity: MainActivity? = null

    override fun init() {
        activity = requireActivity() as MainActivity
    }

    override fun setUp() {
        viewModel.inCharge.observe(viewLifecycleOwner) {
            if (it == null) {
                binding.containerNoInCharge.show()
                binding.containerHasInCharge.gone()
            } else {
                binding.containerNoInCharge.gone()
                binding.containerHasInCharge.show()

                setData()
            }
        }
    }

    override fun initData() {
        viewModel.getInCharge()
    }

    override fun inflateViewBinding(inflater: LayoutInflater): FragmentInChargeBinding {
        return FragmentInChargeBinding.inflate(inflater)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setData() {
        val data = viewModel.inCharge.value

        Timber.d("data: $data")

        if (data == null) return

        binding.textTitle.text = data.title
        binding.textDescription.text = data.description
        binding.textStatusTag.background = resources.getDrawable(data.state.getStateTagBackground())
        binding.textStatusTag.text = data.state.getStateName()

        val researcherAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data.researcher.map { it.name })
        binding.listResearcher.adapter = researcherAdapter

        val supervisorAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, data.supervisor.map { it.name })
        binding.listSupervisor.adapter = supervisorAdapter

        val documentAdapter = DocumentAdapter()
        binding.listDocument.layoutManager = LinearLayoutManager(requireContext())
        binding.listDocument.adapter = documentAdapter
        documentAdapter.setData(data.documents)

        val adapter = ResearcherReportAdapter()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.listReport.adapter = adapter
        binding.listReport.layoutManager = layoutManager
        adapter.setData(data.reports)

        fabType = getInChargeFAB(data.state, UserRole.RESEARCHER)

        if (fabType == null) {
            binding.fab.gone()
        } else {
            binding.fab.show()
            binding.fab.icon = requireContext().getDrawable(fabType!!.getIcon())
            binding.fab.backgroundTintList = resources.getColorStateList(fabType!!.getBackgroundColor())
            binding.fab.setTextColor(resources.getColor(fabType!!.getTextColor()))
            binding.fab.text = fabType!!.getText()
            binding.fab.iconTint = resources.getColorStateList(fabType!!.getTextColor())
        }
    }
}
