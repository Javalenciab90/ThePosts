package com.javalenciab90.posts.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.javalenciab90.domain.routes.PostsRoutes
import com.javalenciab90.platform.effect.ObserveEffects
import com.javalenciab90.posts.components.posts.ui.PostListContract
import com.javalenciab90.posts.components.posts.ui.components.PostsScreen
import com.javalenciab90.posts.components.posts.ui.PostsViewModel
import com.javalenciab90.posts.components.posts.ui.components.PostListContent

fun NavGraphBuilder.buildPostsScreen(
    navController: NavController
) {
    composable<PostsRoutes.PostsScreen> {

        val viewModel: PostsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ObserveEffects(flow = viewModel.effects) { effect ->
            when (effect) {
                is PostListContract.Effect.OpenPostDetail -> {
                    navController.navigateToPostDetail(effect.postId)
                }
            }
        }

        PostsScreen(
            text = uiState.searchText,
            onHandleIntent = viewModel::handleIntent
        ) { paddingValues ->
            PostListContent(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onHandleIntent = viewModel::handleIntent
            )
        }
    }
}

fun NavController.navigateToPostDetail(postId: Int) {
    navigate(PostsRoutes.DetailScreen(postId))
}