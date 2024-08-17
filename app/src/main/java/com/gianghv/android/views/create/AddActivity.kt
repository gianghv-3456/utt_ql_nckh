package com.gianghv.android.views.create

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityAddBinding
import com.gianghv.android.domain.Document
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.repository.FakeData
import com.gianghv.android.util.app.formatDateToDDMMYYYY
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show
import com.gianghv.android.views.common.BGType
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class AddActivity : BaseActivity<ActivityAddBinding>() {
    override fun createBinding(): ActivityAddBinding {
        return ActivityAddBinding.inflate(layoutInflater)
    }

    override val context: Context
        get() = this@AddActivity

    private val viewModel: AddViewModel by viewModels()

    private val documentAdapter by lazy {
        ItemAdapter(
            onClickAdd = {
                viewModel.addDocument(
                    Document(
                        viewModel.documents.value?.size?.plus(1) ?: 0,
                        "proposal_file.pdf",
                        "https://www.lipsum.com/"
                    )
                )
            },
            onClickRemove = { item ->
                when (item) {
                    Item.AddItem -> TODO()
                    is Item.DocumentItem -> viewModel.removeDocument(item.document)
                    is Item.ResearcherItem -> TODO()
                    is Item.SuperVisorItem -> TODO()
                    is Item.TitleItem -> TODO()
                }

            }
        )
    }

    override fun setUp() {
        super.setUp()

        val type = intent?.getIntExtra("type", 0)
        setupUI(type ?: 0)

        setupObserver()

        binding.btnSave.setOnClickListener {
            when (type) {
                TYPE_ADD_PROJECT -> {

                }

                TYPE_ADD_REPORT -> {
                    onClickAddReport()
                }
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupObserver() {
        viewModel.documents.observe(this) {
            documentAdapter.setItems(it.map { document ->
                Item.DocumentItem(document)
            }, "Documents")
        }
    }

    private fun onClickAddReport() {
        val title = binding.edtTitle.text.toString()
        val content = binding.edtContent.text.toString()
        if (title.isEmpty() || content.isEmpty()) {
            showMessage("Invalid input!", BGType.BG_TYPE_ERROR)
            return
        }

        val report = ResearcherReport(
            FakeData.reports.size + 1,
            title,
            Date(),
            content = content,
            file = Document(FakeData.documents.size + 1, "proposal_file.pdf", "https://www.lipsum.com/"),
            supervisorComments = emptyList(),
            reporter = FakeData.researchers[0]
        )
        viewModel.addReport(report)
        showMessage("Add report successfully!", BGType.BG_TYPE_SUCCESS)
        onBackPressed()
    }

    @SuppressLint("SetTextI18n")
    private fun setupUI(type: Int) {

        binding.rcvDocument.apply {
            adapter = documentAdapter
            layoutManager = LinearLayoutManager(this@AddActivity)
            documentAdapter.setItems(listOf(), "Documents")
        }

        binding.tvDate.text = formatDateToDDMMYYYY(Date())

        when (type) {
            TYPE_ADD_PROJECT -> {
                binding.apply {
                    tvToolbar.text = "Propose New Project"
                    edtTitle.hint = "Tên Project"
                    edtContent.hint = "Description"
                    layoutDate.gone()
                    rcvSupervisor.show()
                    rcvResearcher.show()
                    rcvDocument.show()
                }
            }

            TYPE_ADD_REPORT -> {
                binding.apply {
                    tvToolbar.text = "New Report"
                    edtTitle.hint = "Title"
                    edtContent.hint = "Content"
                    layoutDate.show()
                    rcvSupervisor.gone()
                    rcvResearcher.gone()
                    rcvDocument.show()
                }
            }
        }
    }

    companion object {
        const val TYPE_ADD_PROJECT = 1
        const val TYPE_ADD_REPORT = 2
    }
}
