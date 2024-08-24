package com.gianghv.android.views.assign

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityAssignBinding
import com.gianghv.android.util.app.formatDateToDDMMYYYY
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show


import java.util.Date

class AssignActivity : BaseActivity<ActivityAssignBinding>()  {



    override fun createBinding(): ActivityAssignBinding {
        return ActivityAssignBinding.inflate(layoutInflater)
    }

    override val context: Context
        get() = this@AssignActivity

    private val viewModel: AssignViewModel by viewModels()

    private val documentAdapter by lazy {
        ItemAdapter(onClickAdd = {
        }, onClickRemove = { item ->
            when (item) {
                Item.AddItem -> TODO()
                is Item.ResearcherItem -> TODO()
                is Item.SuperVisorItem -> TODO()
                is Item.TitleItem -> TODO()
            }

        })
    }






    override fun setUp() {
        super.setUp()

        val type = intent?.getIntExtra("type", 0)
        val projectId = intent?.getIntExtra("projectId", 0)
        setupUI(type ?: 0)

        setupObserver()

        binding.btnSave.setOnClickListener {

        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setupObserver() {
//        viewModel.documents.observe(this) {
//            documentAdapter.setItems(it.map { document ->
//                Item.DocumentItem(document)
//            }, "Documents")
//        }
    }




    @SuppressLint("SetTextI18n")
    private fun setupUI(type: Int) {

        binding.rcvDocument.apply {
            adapter = documentAdapter
            layoutManager = LinearLayoutManager(this@AssignActivity)
            documentAdapter.setItems(listOf(), "Documents")
        }

        binding.tvDate.text = formatDateToDDMMYYYY(Date())


                binding.apply {

                    layoutDate.gone()
                    rcvSupervisor.show()
                    rcvResearcher.show()
                    rcvDocument.show()
                }


        }
    }


