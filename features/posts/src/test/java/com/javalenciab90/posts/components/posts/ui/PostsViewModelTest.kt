package com.javalenciab90.posts.components.posts.ui

import app.cash.turbine.test
import com.javalenciab90.domain.models.Post
import com.javalenciab90.posts.CoroutineTestRule
import com.javalenciab90.posts.domain.usecases.GetAllPostsUseCase
import com.javalenciab90.posts.domain.usecases.GetPostUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class PostsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineTestRule()

    @MockK
    private lateinit var getAllPostsUseCase: GetAllPostsUseCase

    @MockK
    private lateinit var getPostUseCase: GetPostUseCase

    private lateinit var viewModel: PostsViewModel

    private val samplePosts = listOf(
        Post(id = 1, userId = 1, title = "First Post", body = "Body 1"),
        Post(id = 2, userId = 2, title = "Second Post", body = "Body 2")
    )

    init {
        MockKAnnotations.init(this)
    }

    @Before
    fun setup() {
        coEvery { getAllPostsUseCase.invoke() } returns flowOf(samplePosts)
        coEvery { getPostUseCase.invoke(any(), any()) } answers { firstArg<List<Post>>() }

        viewModel = PostsViewModel(
            getAllPostsUseCase = getAllPostsUseCase,
            getPostUseCase = getPostUseCase,
            coroutineContext = coroutineRule.coroutineContextProvider
        )
    }

    @Test
    fun `initial load success with all posts`() = runTest {
        // then
        viewModel.uiState.test {
            skipItems(1) // Skip the status initial Loading
            val state = awaitItem()
            assert(state.status is Status.Success)
            assertEquals(samplePosts, (state.status as Status.Success).data)
        }
    }

    @Test
    fun `given the query when search with matching title then returns filtered posts`() = runTest {
        // given
        val query = "First"
        coEvery { getPostUseCase.invoke(query, samplePosts) } returns listOf(samplePosts[0])

        // when
        viewModel.handleIntent(PostListContract.Intent.Search(query))
        advanceUntilIdle() // para esperar al debounce

        // then
        coVerify { getPostUseCase.invoke(query, samplePosts) }

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Success)
            val posts = (state.status as Status.Success).data
            assertEquals(listOf(samplePosts[0]), posts)
        }
        confirmVerified(getPostUseCase)
    }

    @Test
    fun `given query when search with no matching title then returns empty status`() = runTest {
        // given
        val query = "NonExisting"
        coEvery { getPostUseCase.invoke(query, samplePosts) } returns emptyList()

        // when
        viewModel.handleIntent(PostListContract.Intent.Search(query))
        advanceUntilIdle()

        // then
        coVerify { getPostUseCase.invoke(query, samplePosts) }

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.status is Status.Empty)
            cancelAndIgnoreRemainingEvents()
        }
        confirmVerified(getPostUseCase)
    }

    @Test
    fun `when clear search then resets searchText`() = runTest {
        // when
        viewModel.handleIntent(PostListContract.Intent.ClearSearch)

        // then
        viewModel.uiState.test {
            val state = awaitItem()
            assert(state.searchText.isEmpty())
        }
    }

    @Test
    fun `when open a post then intent sends effect`() = runTest {
        // given
        val postId = 1

        viewModel.effects.test {
            // when
            viewModel.handleIntent(PostListContract.Intent.OnPostDetail(postId))

            // then
            val effect = awaitItem()
            assert(effect is PostListContract.Effect.OpenPostDetail)
            assert((effect as PostListContract.Effect.OpenPostDetail).postId == postId)
        }
    }
}
