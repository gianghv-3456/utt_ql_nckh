package com.gianghv.android.views.main.project

import com.gianghv.android.domain.UserRole

enum class ProjectListFAB {
    NEW,
    PROPOSED
}

fun getProjectListFAB(role: UserRole): ProjectListFAB {
    return when (role) {
        UserRole.RESEARCHER -> {
            ProjectListFAB.PROPOSED
        }

        UserRole.SUPERVISOR -> {
            ProjectListFAB.PROPOSED
        }

        UserRole.ADMIN -> {
            ProjectListFAB.NEW
        }
    }
}
