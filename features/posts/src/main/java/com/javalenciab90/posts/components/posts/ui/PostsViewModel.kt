package com.javalenciab90.posts.components.posts.ui

import androidx.lifecycle.viewModelScope
import com.javalenciab90.domain.Constants
import com.javalenciab90.platform.base.CoroutineContextProvider
import com.javalenciab90.platform.base.MviViewModel
import com.javalenciab90.posts.domain.usecases.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    coroutineContext: CoroutineContextProvider
) :  MviViewModel<PostListContract.PostsState, PostListContract.Intent, PostListContract.Effect>() {

    override fun initialState(): PostListContract.PostsState {
        return PostListContract.PostsState(status = Status.Loading)
    }

    init {
        viewModelScope.launch {
            uiState.debounce(Constants.MILLI_SECONDS_500)
                .map { it.searchText }
                .distinctUntilChanged()
                .flowOn(coroutineContext.default)
                .collect { query ->
                    getAllPosts(query)
                }
        }
    }

    private suspend fun getAllPosts(query: String) {
        if (query.isBlank()) {
            getAllPostsUseCase.invoke().collect { posts ->
                updateState {
                    copy(searchText = query, status = Status.Success(posts))
                }
            }
        } else {

        }
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

    private fun openPostDetail(postId: Int) {
        sendEffect(effect = PostListContract.Effect.OpenPostDetail(postId))
    }


}