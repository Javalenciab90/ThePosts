package com.javalenciab90.posts.components.posts.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javalenciab90.design_system.components.EmptyScreen
import com.javalenciab90.design_system.components.ErrorScreen
import com.javalenciab90.design_system.components.LoadingScreen
import com.javalenciab90.domain.models.Post
import com.javalenciab90.posts.components.posts.ui.PostListContract
import com.javalenciab90.posts.components.posts.ui.Status

@Composable
fun PostListContent(
    modifier: Modifier = Modifier,
    uiState: PostListContract.PostsState,
    onHandleIntent: (PostListContract.Intent) -> Unit
) {
    when (uiState.status) {
        is Status.Loading -> {
            LoadingScreen()
        }
        is Status.Success -> {
            PostListSuccess(
                modifier = modifier,
                posts = uiState.status.data
            ) { postId ->
                onHandleIntent(PostListContract.Intent.OnPostDetail(postId))
            }
        }
        is Status.Empty -> {
            EmptyScreen(
                title = "No existe el POST",
                content = "Por favor!\nIngresa un nombre ó ID válido"
            )
        }
        is Status.Error -> {
            ErrorScreen(uiState.status.errorMessage)
        }
    }
}

@Composable
fun PostListSuccess(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onPostClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { post ->
            PostCard(
                post = post
            ) {
                onPostClick(it)
            }
        }
    }
}