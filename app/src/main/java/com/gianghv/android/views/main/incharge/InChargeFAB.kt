package com.gianghv.android.views.main.incharge

import com.gianghv.android.R
import com.gianghv.android.domain.ProjectState
import com.gianghv.android.domain.UserRole

enum class InChargeFAB {
    NEW_REPORT,
    PAUSE,
    RESUME,
    CANCEL,
    REVIEW,
    MARK_FINISH
}

fun getInChargeFAB(state: ProjectState, role: UserRole): InChargeFAB? {
    when (state) {
        ProjectState.NEW -> {
            if (role == UserRole.RESEARCHER) {
                return null
            } else if (role == UserRole.SUPERVISOR) {
                return null
            }
        }

        ProjectState.PROPOSED -> {
            if (role == UserRole.RESEARCHER) {
                return InChargeFAB.CANCEL
            } else if (role == UserRole.SUPERVISOR) {
                return null
            }
        }

        ProjectState.IN_PROGRESS -> {
            if (role == UserRole.RESEARCHER) {
                return InChargeFAB.NEW_REPORT
            } else if (role == UserRole.SUPERVISOR) {
                return InChargeFAB.MARK_FINISH
            }
        }

        ProjectState.UNDER_REVIEW -> {
            return null
        }

        ProjectState.PAUSED -> {
            if (role == UserRole.RESEARCHER) {
                return InChargeFAB.RESUME
            } else if (role == UserRole.SUPERVISOR) {
                return InChargeFAB.RESUME
            }
        }

        ProjectState.COMPLETED -> {
            return null
        }

        ProjectState.CANCELLED -> {
            return null
        }
    }
    return null
}

fun InChargeFAB.getIcon() = when (this) {
    InChargeFAB.NEW_REPORT -> R.drawable.ic_add
    InChargeFAB.PAUSE -> R.drawable.ic_pause
    InChargeFAB.RESUME -> R.drawable.ic_resume
    InChargeFAB.CANCEL -> R.drawable.ic_cancel
    InChargeFAB.REVIEW -> R.drawable.ic_review
    InChargeFAB.MARK_FINISH -> R.drawable.ic_done
}

fun InChargeFAB.getBackgroundColor() = when (this) {
    InChargeFAB.NEW_REPORT -> R.color.pending_tag
    InChargeFAB.PAUSE -> R.color.inprogress_tag
    InChargeFAB.RESUME -> R.color.pending_tag
    InChargeFAB.CANCEL -> R.color.action_color
    InChargeFAB.REVIEW -> R.color.complete_tag
    InChargeFAB.MARK_FINISH -> R.color.complete_tag
}

fun InChargeFAB.getTextColor() = when (this) {
    InChargeFAB.NEW_REPORT -> R.color.text_dark_color
    InChargeFAB.PAUSE -> R.color.text_dark_color
    InChargeFAB.RESUME -> R.color.text_dark_color
    InChargeFAB.CANCEL -> R.color.white
    InChargeFAB.REVIEW -> R.color.text_dark_color
    InChargeFAB.MARK_FINISH -> R.color.text_dark_color
}

fun InChargeFAB.getText() = when (this) {
    InChargeFAB.NEW_REPORT -> "Báo cáo"
    InChargeFAB.PAUSE -> "Tạm dừng"
    InChargeFAB.RESUME -> "Tiếp tục"
    InChargeFAB.CANCEL -> "Hủy"
    InChargeFAB.REVIEW -> "Đánh giá"
    InChargeFAB.MARK_FINISH -> "Kết thúc"
}
