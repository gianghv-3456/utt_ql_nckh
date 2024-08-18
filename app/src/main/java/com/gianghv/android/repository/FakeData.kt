package com.gianghv.android.repository

import com.gianghv.android.domain.Document
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.domain.SupervisorComment
import com.gianghv.android.domain.UserRole
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.Date

object FakeData {
    val projects = mutableListOf<Project>()
    val researchers = mutableListOf<Researcher>()
    val supervisors = mutableListOf<Supervisor>()
    val documents = mutableListOf<Document>()
    val reports = mutableListOf<ResearcherReport>()
    val comments = mutableListOf<SupervisorComment>()

    fun fakeData() {


        val researcher = Researcher(
            1,
            name = "Hoang Van A",
            "sv1@gmail.com",
            "123456",
            UserRole.RESEARCHER,
            "08/06/2002",
            "CT050413",
            "CT5D",
            "CNTT"
        )
        val researcher2 = Researcher(
            2,
            name = "Hoang Van B",
            "sv2@gmail.com",
            "123456",
            UserRole.RESEARCHER,
            "08/06/2002",
            "CT050413",
            "CT5D",
            "CNTT"
        )

        val researcher3 = Researcher(
            3,
            name = "Hoang Van C",
            "sv3@gmail.com",
            "123456",
            UserRole.RESEARCHER,
            "08/06/2002",
            "CT050413",
            "CT5D",
            "CNTT"
        )


        researchers.add(researcher)
        researchers.add(researcher2)
        researchers.add(researcher3)

        val supervisor = Supervisor(
            3, name = "Tran Van V", "gv1@gmail.com", "", UserRole.SUPERVISOR,

            "08/06/2002", "CT050413", "Ts. ", "CNTT"
        )

        val supervisor2 = Supervisor(
            4, name = "Tran Van W", "gv2@gmail.com", "", UserRole.SUPERVISOR, "08/06/2002", "CT050413", "Ths. ", "CNTT"
        )

        val supervisor3 = Supervisor(
            4, name = "Nguyen The N", "gv3@gmail.com", "", UserRole.SUPERVISOR, "08/06/2002", "CT050413", "Ts. ", "CNTT"
        )

        supervisors.add(supervisor)
        supervisors.add(supervisor2)
        supervisors.add(supervisor3)

        val prj1Doc = Document(1, "proposal_file.pdf", "https://www.lipsum.com/")
        val prj2Doc = Document(2, "api_doc_file.pdf", "https://www.lipsum.com/")
        val prj3Doc = Document(3, "other_file.pdf", "https://www.lipsum.com/")
        documents.add(prj1Doc)
        documents.add(prj2Doc)
        documents.add(prj3Doc)

        val comment1 = SupervisorComment(1, "Báo cáo tốt", "Tran Van V")
        val comment2 = SupervisorComment(2, "Báo cáo tốt", "Nguyen The N")
        comments.add(comment1)
        comments.add(comment2)

        val report1 = ResearcherReport(
            1,
            "Báo cáo giai đoạn 1",
            Date(),
            content = "Em xin gửi báo cáo giai đoạn 1\nNội dung đã làm xong",
            file = prj1Doc,
            supervisorComments = listOf(comment1),
            reporter = researcher
        )
        val report2 = ResearcherReport(
            2,
            "Báo cáo giai đoạn 2",
            Date(),
            content = "Em xin gửi báo cáo giai đoạn 2\nNội dung đã làm xong",
            file = prj1Doc,
            supervisorComments = comments,
            reporter = researcher2
        )

        reports.add(report1)
        reports.add(report2)

        val prj1 = Project(
            1,
            title = "Nghiên cứu áp dụng mô hình Retrieval Argumented Generate AI trong bài toán tư vấn tự động của cửa hàng thời trang",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = listOf(researcher),
            supervisor = listOf(supervisor),
            state = ProjectState.IN_PROGRESS,
            documents = documents,
            reports = listOf(report1),
            score = null
        )

        val prj2 = Project(
            2,
            title = "Nghiên cứu áp dụng hệ thống Recommendation System content based",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = listOf(researcher2),
            supervisor = listOf(supervisor3, supervisor),
            state = ProjectState.UNDER_REVIEW,
            documents = documents,
            reports = reports,
            score = null
        )

        val prj3 = Project(
            3,
            title = "Nghiên cứu áp dụng hệ thống Recommendation System content based",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = listOf(researcher2),
            supervisor = listOf(supervisor, supervisor3),
            state = ProjectState.COMPLETED,
            documents = documents,
            reports = listOf(report2),
            score = 7.8
        )

        val prj4 = Project(
            4,
            title = "Nghiên cứu áp dụng hệ thống Recommendation System content based",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = emptyList(),
            supervisor = listOf(supervisor2),
            state = ProjectState.NEW,
            documents = emptyList(),
            reports = emptyList(),
            score = null
        )

        projects.add(prj1)
        projects.add(prj2)
        projects.add(prj3)
        projects.add(prj4)

        val map: MutableMap<String, Any> = mutableMapOf()
        map["researchers"] = researchers
        map["supervisors"] = supervisors
        map["documents"] = documents
        map["reports"] = reports
        map["comments"] = comments
        map["projects"] = projects
    }
}
