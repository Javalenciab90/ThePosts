package com.javalenciab90.posts.components.posts.ui

import com.javalenciab90.platform.base.CoroutineContextProvider
import com.javalenciab90.platform.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    coroutineContext: CoroutineContextProvider
) :  MviViewModel<PostListContract.PostsState, PostListContract.Intent, PostListContract.Effect>() {

    override fun initialState(): PostListContract.PostsState {
        return PostListContract.PostsState(status = Status.Loading)
    }

    override fun handleIntent(intent: PostListContract.Intent) {
        when (intent) {
            is PostListContract.Intent.Search -> {
                updateSearchText(intent.text)
            }
            is PostListContract.Intent.ClearSearch -> {
                clearSearchText()
            }
            is PostListContract.Intent.OnPostDetail -> {
                openPostDetail(intent.postId)
            }
        }
    }

    private fun updateSearchText(text: String) {
        updateState {
            copy(
                searchText = text
            )
        }
    }

    private fun clearSearchText() {
        updateState {
            copy(
                searchText = ""
            )
        }
    }

    private fun openPostDetail(postId: String) {
        sendEffect(effect = PostListContract.Effect.OpenPostDetail(postId))
    }


}