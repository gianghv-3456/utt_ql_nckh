package com.gianghv.android.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val state: ProjectState,
    val researcher: List<Researcher>,
    val supervisor: List<Supervisor>,
    val documents: List<Document>,
    val reports: List<ResearcherReport>,
    val score: Float?
) : Parcelable
