package com.gianghv.android.views.create

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.gianghv.android.R
import com.gianghv.android.base.BaseActivity
import com.gianghv.android.databinding.ActivityAddBinding
import com.gianghv.android.domain.Document
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.repository.FakeData
import com.gianghv.android.util.app.formatDateToDDMMYYYY
import com.gianghv.android.util.ext.genId
import com.gianghv.android.util.ext.gone
import com.gianghv.android.util.ext.show
import com.gianghv.android.views.common.BGType
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.nareshchocha.filepickerlibrary.models.DocumentFilePickerConfig
import com.nareshchocha.filepickerlibrary.ui.FilePicker
import com.nareshchocha.filepickerlibrary.utilities.appConst.Const
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File
import java.util.Date


@AndroidEntryPoint
class AddActivity : BaseActivity<ActivityAddBinding>() {
    private val files = mutableMapOf<String, String>()

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            val uri = it.data?.data!!
            val filePath = it.data?.getStringExtra(Const.BundleExtras.FILE_PATH)

            if (filePath == null) {
                uploadFileSelected("document_file", uri)
            } else {
                val file = File(filePath)
                val fileName = file.name
                Timber.d("fileName $fileName")
                uploadFileSelected(fileName, file.toUri())
            }
            Timber.d("uri $uri")
        }
    }


    override fun createBinding(): ActivityAddBinding {
        return ActivityAddBinding.inflate(layoutInflater)
    }

    override val context: Context
        get() = this@AddActivity

    private val viewModel: AddViewModel by viewModels()

    private val documentAdapter by lazy {
        ItemAdapter(onClickAdd = {
            pickFile()
        }, onClickRemove = { item ->
            when (item) {
                Item.AddItem -> TODO()
                is Item.DocumentItem -> viewModel.removeDocument(item.document)
                is Item.ResearcherItem -> TODO()
                is Item.SuperVisorItem -> TODO()
                is Item.TitleItem -> TODO()
            }

        })
    }

    private fun pickFile() {
        val pickerConfig = DocumentFilePickerConfig(
            popUpIcon = R.drawable.ic_attach_file,// DrawableRes Id
            popUpText = "File Media",
            allowMultiple = false,// set Multiple pick file
            maxFiles = 0,// max files working only in android latest version
            mMimeTypes = listOf("*/*"),// added Multiple MimeTypes
            askPermissionTitle = null, // set Permission ask Title
            askPermissionMessage = null,// set Permission ask Message
            settingPermissionTitle = null,// set Permission setting Title
            settingPermissionMessage = null,// set Permission setting Messag
        )

        val intent = FilePicker.Builder(this).setPopUpConfig().addPickDocumentFile(pickerConfig).build()

        launcher.launch(intent)
    }

    private fun addDocument() {
        files.forEach { (t, u) ->
            viewModel.addDocument(Document(genId(), t, u))
        }
    }

    private fun uploadFileSelected(fileName: String, uri: Uri? = null) {
        // Handle the selected images here
        showLoading(true)

        if (uri == null) return

        try {
            Firebase.storage.maxChunkUploadRetry = 10000
            Firebase.storage.maxUploadRetryTimeMillis = 10000
            Firebase.storage.maxDownloadRetryTimeMillis = 10000

            val storage = Firebase.storage
            val storageRef = storage.reference

            // Create a child reference
            // imagesRef now points to "images"
            val docsRef: StorageReference = storageRef.child("docs")

            val spaceRef = docsRef.child(uri.lastPathSegment ?: "image${System.currentTimeMillis()}")

            val uploadTask = spaceRef.putFile(uri)
            uploadTask.addOnFailureListener {
                it.printStackTrace()
                Timber.d("Upload fail")
                showLoading(false)
            }

            uploadTask.addOnSuccessListener {
                spaceRef.downloadUrl.addOnSuccessListener {
                    Timber.d("File URL: $it")
                    files[fileName] = it.toString()
                    documentAdapter.setItems(listOf(Item.DocumentItem(Document(genId(), fileName, it.toString()))) ,fileName)
                    showLoading(false)

                }.addOnFailureListener {
                    it.printStackTrace()
                    showLoading(false)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun setUp() {
        super.setUp()

        val type = intent?.getIntExtra("type", 0)
        val projectId = intent?.getIntExtra("projectId", 0)
        setupUI(type ?: 0)

        setupObserver()

        binding.btnSave.setOnClickListener {
            when (type) {
                TYPE_ADD_PROJECT -> {
                    onClickAddProject()
                }

                TYPE_ADD_REPORT -> {
                    if (projectId != null && projectId != 0) {
                        onClickAddReport(projectId)
                    }
                }
            }
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        requirePermission()
    }

    private fun setupObserver() {
        viewModel.documents.observe(this) {
            documentAdapter.setItems(it.map { document ->
                Item.DocumentItem(document)
            }, "Documents")
        }
    }


    private fun onClickAddReport(projectId: Int) {
        addDocument()

        val title = binding.edtTitle.text.toString()
        val content = binding.edtContent.text.toString()
        if (title.isEmpty() || content.isEmpty()) {
            showMessage("Invalid input!", BGType.BG_TYPE_ERROR)
            return
        }

        val report = ResearcherReport(
            genId(),
            title,
            Date(),
            content = content,
            file = viewModel.documents.value?.first() ?: Document(1, "", ""),
            supervisorComments = emptyList(),
            reporter = FakeData.researchers[0]
        )
        viewModel.addReport(report, projectId)
        showMessage("Add report successfully!", BGType.BG_TYPE_SUCCESS)
        onBackPressed()
    }

    private fun onClickAddProject() {
        addDocument()

        val title = binding.edtTitle.text.toString()
        val content = binding.edtContent.text.toString()
        if (title.isEmpty() || content.isEmpty()) {
            showMessage("Invalid input!", BGType.BG_TYPE_ERROR)
            return
        }

        val researcherList = ArrayList<Researcher>()
        researcherList.add(FakeData.researchers[0])
        researcherList.add(FakeData.researchers[1])

        var documents = ArrayList<Document>()
        if (viewModel.documents.value != null){
            documents = viewModel.documents.value as ArrayList<Document>
        }

        val project = Project(
            genId(),
            title,
            content,
            ProjectState.PROPOSED,
            researcher = researcherList,
            supervisor = emptyList(),
            documents = documents,
            reports = emptyList(),
            score = null
        )
        viewModel.addProject(project)
        showMessage("Add project successfully!", BGType.BG_TYPE_SUCCESS)
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

    private fun requirePermission() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                // check if all permissions are granted
                if (report.areAllPermissionsGranted()) {
                    // do you work now
                }


                // check for permanent denial of any permission
                if (report.isAnyPermissionPermanentlyDenied) {
                    // permission is denied permenantly, navigate user to app settings
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                permissions: List<PermissionRequest>, token: PermissionToken
            ) {
                token.continuePermissionRequest()
            }
        }).onSameThread().check()
    }

    companion object {
        const val TYPE_ADD_PROJECT = 1
        const val TYPE_ADD_REPORT = 2
    }
}
