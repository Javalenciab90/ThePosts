package com.javalenciab90.posts.components.posts.ui

class PostListContract {

    data class PostsState(
        val searchText: String = "",
        val status: Status
    )

    sealed interface Effect {
        data class OpenPostDetail(val postId: String) : Effect
    }

    sealed interface Intent {
        data class Search(val text: String) : Intent
        data object ClearSearch: Intent
        data class OnPostDetail(val postId: String) : Intent
    }
}

sealed interface Status {
    data object Loading : Status
    data class Success(val data: String) : Status // create modelUi
    data class Error(val errorUi: String) : Status // errorUi
    data object Empty : Status
}
