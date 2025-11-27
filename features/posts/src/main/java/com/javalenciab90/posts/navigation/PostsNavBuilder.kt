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
import com.javalenciab90.posts.components.posts.ui.PostsScreen
import com.javalenciab90.posts.components.posts.ui.PostsViewModel

fun NavGraphBuilder.buildPostsScreen(
    navController: NavController
) {
    composable<PostsRoutes.PostsScreen> {

        val viewModel: PostsViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        PostsScreen(
            text = uiState.searchText,
            onHandleIntent = viewModel::handleIntent
        ) { paddingValues ->
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "TODO: Post list implementation"
                )
        }
    }
}