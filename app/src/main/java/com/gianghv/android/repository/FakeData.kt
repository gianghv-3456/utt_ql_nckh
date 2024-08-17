package com.gianghv.android.repository

import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.Document
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.domain.SupervisorComment
import com.gianghv.android.domain.UserRole
import java.util.Date

object FakeData {
    val projects = mutableListOf<Project>()
    val researchers = mutableListOf<Researcher>()
    val supervisors = mutableListOf<Supervisor>()
    val documents = mutableListOf<Document>()
    val reports = mutableListOf<ResearcherReport>()
    val comments = mutableListOf<SupervisorComment>()

    fun fakeData() {
        AppState.userId = 1

        val researcher = Researcher(
            1,
            name = "Hoang Van A",
            "hoangvana@gmail.com",
            UserRole.RESEARCHER.toString().lowercase(),
            "08/06/2002",
            "CT050413",
            "CT5D",
            "CNTT"
        )
        val researcher2 = Researcher(
            2,
            name = "Hoang Van B",
            "hoangvana@gmail.com",
            UserRole.RESEARCHER.toString().lowercase(),
            "08/06/2002",
            "CT050413",
            "CT5D",
            "CNTT"
        )
        researchers.add(researcher)
        researchers.add(researcher2)

        val supervisor = Supervisor(
            3,
            name = "Tran Van V",
            "hoangvana@gmail.com",
            UserRole.SUPERVISOR.toString().lowercase(),
            "08/06/2002",
            "CT050413",
            "PhD",
            "CNTT"
        )
        val supervisor2 = Supervisor(
            4,
            name = "Tran Van W",
            "hoangvana@gmail.com",
            UserRole.SUPERVISOR.toString().lowercase(),
            "08/06/2002",
            "CT050413",
            "PhD",
            "CNTT"
        )
        supervisors.add(supervisor)
        supervisors.add(supervisor2)

        val prj1Doc = Document(1, "proposal_file.pdf", "https://www.lipsum.com/")
        val prj2Doc = Document(2, "proposal_file.pdf", "https://www.lipsum.com/")
        val prj3Doc = Document(3, "proposal_file.pdf", "https://www.lipsum.com/")
        documents.add(prj1Doc)
        documents.add(prj2Doc)
        documents.add(prj3Doc)

        val comment1 = SupervisorComment(1, "Báo cáo tốt", "Hoang Van A")
        val comment2 = SupervisorComment(2, "Báo cáo tốt", "Hoang Van B")
        comments.add(comment1)
        comments.add(comment2)

        val report1 = ResearcherReport(
            1,
            "Báo cáo giai đoạn 1",
            Date(),
            content = "Em xin gửi báo cáo giai đoạn 1\nNội dung đã làm xong",
            file = prj1Doc,
            supervisorComments = comments,
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
            researcher = researchers,
            supervisor = supervisors,
            state = ProjectState.PAUSED,
            documents = documents,
            reports = reports,
            score = null
        )

        val prj2 = Project(
            2,
            title = "Nghiên cứu áp dụng hệ thống Recommendation System content based",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = listOf(researcher),
            supervisor = listOf(supervisor2),
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
            supervisor = listOf(supervisor),
            state = ProjectState.COMPLETED,
            documents = documents,
            reports = reports,
            score = 7.8f
        )

        val prj4 = Project(
            4,
            title = "Nghiên cứu áp dụng hệ thống Recommendation System content based",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum",
            researcher = listOf(researcher),
            supervisor = listOf(supervisor),
            state = ProjectState.COMPLETED,
            documents = documents,
            reports = reports,
            score = 9f
        )

        projects.add(prj1)
        projects.add(prj2)
        projects.add(prj3)
        projects.add(prj4)
    }
}
