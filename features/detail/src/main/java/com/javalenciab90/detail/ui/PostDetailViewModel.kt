package com.javalenciab90.detail.ui

import androidx.lifecycle.SavedStateHandle
import com.javalenciab90.detail.domain.usecases.AddNewCommentUseCase
import com.javalenciab90.detail.domain.usecases.GetPostCommentsUseCase
import com.javalenciab90.domain.models.PostComment
import com.javalenciab90.platform.base.CoroutineContextProvider
import com.javalenciab90.platform.base.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val addNewCommentUseCase: AddNewCommentUseCase,
    private val getPostCommentsUseCase: GetPostCommentsUseCase,
    private val coroutineContext: CoroutineContextProvider,
    savedStateHandle: SavedStateHandle
) :  MviViewModel<PostDetailContract.PostsDetailState, PostDetailContract.Intent, PostDetailContract.Effect>(coroutineContext) {

    private val postId = savedStateHandle.get<Int>("postId") ?: 0

    override fun initialState(): PostDetailContract.PostsDetailState {
        return PostDetailContract.PostsDetailState(status = Status.Loading)
    }

    init {
        getPostComments()
    }

    private fun getPostComments() {
        launch(
            coroutineContext.backgroundContext
        ) {
            getPostCommentsUseCase.invoke(postId).collect { comments ->
                if (comments.isNotEmpty()) {
                    updateState {
                        copy(status = Status.Success(data = comments))
                    }
                } else {
                    updateState {
                        copy(status = Status.Empty)
                    }
                }
            }
        }
    }

    override fun handleIntent(intent: PostDetailContract.Intent) {
        when (intent) {
            is PostDetailContract.Intent.ShowDialogComment -> {
                updateState {
                    copy(showDialogComment = true)
                }
            }
            is PostDetailContract.Intent.DismissDialogComment -> {
                updateState {
                    copy(showDialogComment = false)
                }
            }
            is PostDetailContract.Intent.AddNewComment -> {
                addNewComment(intent.text)
            }
        }
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        updateState {
            copy(status = Status.Error(exception.message.toString()))
        }
    }

    private fun addNewComment(text: String) {
        launch(coroutineContext = coroutineContext.backgroundContext) {
            addNewCommentUseCase.invoke(
                PostComment(
                    postId = postId,
                    comment = text
                )
            )
            updateState {
                copy(showDialogComment = false)
            }
        }
    }
}