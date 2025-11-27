package com.javalenciab90.detail.ui

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.javalenciab90.detail.CoroutineTestRule
import com.javalenciab90.detail.domain.usecases.AddNewCommentUseCase
import com.javalenciab90.detail.domain.usecases.GetPostCommentsUseCase
import com.javalenciab90.domain.models.PostComment
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PostDetailViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @MockK
    private lateinit var addNewCommentUseCase: AddNewCommentUseCase

    @MockK
    private lateinit var getPostCommentsUseCase: GetPostCommentsUseCase

    private lateinit var viewModel: PostDetailViewModel
    private val fakePostId = 10

    init {
        MockKAnnotations.init(this)
    }

    @Before
    fun setup() {

        coEvery { getPostCommentsUseCase.invoke(fakePostId) } returns flowOf( emptyList())

        val savedStateHandle = SavedStateHandle(mapOf("postId" to fakePostId))

        viewModel = PostDetailViewModel(
            addNewCommentUseCase = addNewCommentUseCase,
            getPostCommentsUseCase = getPostCommentsUseCase,
            coroutineContext = coroutineRule.coroutineContextProvider,
            savedStateHandle = savedStateHandle
        )
    }

    @Test
    fun `when initial load data with Empty status`() = runTest {
        // then
        coVerify { getPostCommentsUseCase.invoke(fakePostId) }

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Empty)
        }

        confirmVerified(getPostCommentsUseCase)
    }

    @Test
    fun `when show dialog action then show dialog`() = runTest {
        // When
        viewModel.handleIntent(PostDetailContract.Intent.ShowDialogComment)

        // then
        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Empty)
            assertTrue(state.showDialogComment)
        }
    }

    @Test
    fun `when dismiss dialog action then dismiss dialog`() = runTest {
        // When
        viewModel.handleIntent(PostDetailContract.Intent.DismissDialogComment)

        // then
        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Empty)
            assertFalse(state.showDialogComment)
        }
    }

    @Test
    fun `given a new comment when add new comment action then calls use cases and refreshes comments`() = runTest {
        // given
        val newCommentText = "Test comment"
        val newComments = listOf(PostComment(fakePostId, newCommentText))

        coEvery { addNewCommentUseCase.invoke(any()) } returns Unit
        coEvery { getPostCommentsUseCase.invoke(fakePostId) } returns flowOf(newComments)

        // when
        viewModel.handleIntent(PostDetailContract.Intent.AddNewComment(newCommentText))

        // then
        coVerify { addNewCommentUseCase.invoke(any()) }
        coVerify { getPostCommentsUseCase.invoke(fakePostId) }

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Success)
            assertEquals(newComments, (state.status as Status.Success).data)
        }

        confirmVerified(addNewCommentUseCase)
        confirmVerified(getPostCommentsUseCase)
    }
}

