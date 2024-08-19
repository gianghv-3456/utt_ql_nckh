package com.gianghv.android.repository.api

import com.gianghv.android.domain.Document
import com.gianghv.android.domain.Project
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.Researcher
import com.gianghv.android.domain.ResearcherReport
import com.gianghv.android.domain.Supervisor
import com.gianghv.android.domain.SupervisorComment
import com.gianghv.android.domain.UserRole
import com.gianghv.android.repository.FakeData
import com.gianghv.android.util.ext.toDateZ
import com.google.firebase.database.DataSnapshot
import java.util.Date

fun DataSnapshot.getResearcher(): Researcher? {
    try {
        val id = this.child("id").value as Long
        val name = this.child("name").value as String
        val email = this.child("email").value as String
        val password = this.child("password").value as String
        val dob = this.child("dob").value as String
        val studentCode = this.child("studentId").value as String
        val className = this.child("class").value as String
        val major = this.child("major").value as String
        return Researcher(
            id = id,
            name = name,
            email = email,
            password = password,
            dob = dob,
            studentCode = studentCode,
            className = className,
            major = major,
            role = UserRole.RESEARCHER
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun DataSnapshot.getSupervisor(): Supervisor? {
    try {
        val id = this.child("id").value as Long
        val name = this.child("name").value as String
        val email = this.child("email").value as String
        val password = this.child("password").value as String
        val dob = this.child("dob").value as String
        val teacherCode = this.child("facultyId").value as String
        val title = this.child("title").value as String
        val major = this.child("department").value as String
        return Supervisor(
            id = id,
            name = name,
            email = email,
            password = password,
            dob = dob,
            teacherCode = teacherCode,
            title = title,
            major = major,
            role = UserRole.SUPERVISOR
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun DataSnapshot.getDocument(): Document? {
    try {
        val id = this.child("id").value as Long
        val title = this.child("filename").value as String
        val url = this.child("url").value as String
        return Document(
            id = id,
            title = title,
            url = url
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun DataSnapshot.getReportComment(): SupervisorComment? {
    try {
        val id = this.child("id").value as Long
        val content = this.child("comment").value as String
        val userName = this.child("supervisorName").value as String
        return SupervisorComment(
            id = id,
            content = content,
            userName = userName
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun DataSnapshot.getReport(): ResearcherReport? {
    try {
        val id = this.child("id").value as Long
        val title = this.child("title").value as String

        val dateStr = this.child("date").value as String
        val parsed = dateStr.toDateZ()
        val date = parsed ?: Date()

        val content = this.child("content").value as String

        val researcherId = this.child("reporter").value as Long
        val researcher = FakeData.researchers.find { it1 -> it1.id == researcherId }
        if (researcher == null) return null

        val comments = mutableListOf<SupervisorComment>()
        this.child("supervisorComments").children.forEach {
            val comment = it.getReportComment()
            if (comment != null) {
                comments.add(comment)
            }
        }

        val document = this.child("file").getDocument() ?: return null


        return ResearcherReport(
            id = id,
            title = title,
            date = date,
            content = content,
            reporter = researcher,
            file = document,
            supervisorComments = comments,
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}

fun DataSnapshot.getProject(): Project? {
    try {
        val id = this.child("id").value as Long
        val title = this.child("title").value as String
        val description = this.child("description").value as String
        val stateStr = this.child("state").value as String
        val state = ProjectState.valueOf(stateStr)

        val documents = mutableListOf<Document>()
        this.child("documents").children.forEach {
            val document = it.getDocument()
            if (document != null) {
                documents.add(document)
            }
        }

        val reports = mutableListOf<ResearcherReport>()
        this.child("reports").children.forEach {
            val report = it.getReport()
            if (report != null) {
                reports.add(report)
            }
        }

        val researchers = mutableListOf<Researcher>()
        this.child("researcher").children.forEach {
            val researcherId = it.value as Long
            val result = FakeData.researchers.find { it1 -> it1.id == researcherId }
            if (result != null) {
                researchers.add(result)
            }
        }

        val supervisors = mutableListOf<Supervisor>()
        this.child("supervisor").children.forEach {
            val supervisorId = it.value as Long
            val result = FakeData.supervisors.find { it1 -> it1.id == supervisorId }
            if (result != null) {
                supervisors.add(result)
            }
        }

        val score = this.child("score").value as Double?

        Timber.d("project $this")

        return Project(
            id = id,
            title = title,
            description = description,
            state = state,
            researcher = researchers,
            supervisor = supervisors,
            documents = documents,
            reports = reports,
            score = score
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}



