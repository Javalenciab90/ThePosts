package com.javalenciab90.posts.components.posts.ui

import com.javalenciab90.domain.models.Post

class PostListContract {

    data class PostsState(
        val searchText: String = "",
        val status: Status
    )

    sealed interface Effect {
        data class OpenPostDetail(val postId: Int) : Effect
    }

    sealed interface Intent {
        data class Search(val text: String) : Intent
        data object ClearSearch: Intent
        data class OnPostDetail(val postId: Int) : Intent
    }
}

sealed interface Status {
    data object Loading : Status
    data class Success(val data: List<Post>) : Status
    data class Error(val errorUi: String) : Status
    data object Empty : Status
}
