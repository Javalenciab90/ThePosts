package com.javalenciab90.posts.components.posts.ui

import com.javalenciab90.domain.Constants
import com.javalenciab90.platform.base.CoroutineContextProvider
import com.javalenciab90.platform.base.MviViewModel
import com.javalenciab90.posts.domain.usecases.GetAllPostsUseCase
import com.javalenciab90.posts.domain.usecases.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val getPostUseCase: GetPostUseCase,
    coroutineContext: CoroutineContextProvider
) :  MviViewModel<PostListContract.PostsState, PostListContract.Intent, PostListContract.Effect>(coroutineContext) {

    override fun initialState(): PostListContract.PostsState {
        return PostListContract.PostsState(status = Status.Loading)
    }

    init {
        launch(coroutineContext = coroutineContext.default) {
            uiState.debounce(Constants.MILLI_SECONDS_500)
                .map { it.searchText }
                .distinctUntilChanged()
                .collect { query ->
                    getAllPosts(query)
                }
        }
    }

    private suspend fun getAllPosts(query: String) {
        getAllPostsUseCase.invoke().collect { posts ->
            if (query.isBlank()) {
                updateState {
                    copy(searchText = query, status = Status.Success(posts))
                }
            } else {
                val postsFounded = getPostUseCase.invoke(query, posts)
                if (postsFounded.isNotEmpty()) {
                    updateState {
                        copy(searchText = query, status = Status.Success(postsFounded))
                    }
                } else {
                    updateState {
                        copy(searchText = query, status = Status.Empty)
                    }
                }
            }
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

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        updateState {
            copy(searchText = currentUiState.searchText, status = Status.Error(exception.message.toString()))
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