package com.javalenciab90.detail.ui

import com.javalenciab90.domain.models.PostComment

class PostDetailContract {

    data class PostsDetailState(
        val showDialogComment: Boolean = false,
        val status: Status
    )

    sealed interface Effect {
        data object EditNewComment : Effect
    }

    sealed interface Intent {
        data object ShowDialogComment : Intent
        data object DismissDialogComment : Intent
        data class AddNewComment(val text: String) : Intent
    }
}

sealed interface Status {
    data object Loading : Status
    data class Success(
        val data: List<PostComment>
    ) : Status
    data class Error(val errorMessage: String) : Status
    data object Empty : Status
}