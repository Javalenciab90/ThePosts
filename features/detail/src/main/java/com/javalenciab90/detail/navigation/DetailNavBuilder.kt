package com.javalenciab90.detail.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.javalenciab90.detail.ui.PostDetailContract
import com.javalenciab90.detail.ui.PostDetailViewModel
import com.javalenciab90.detail.ui.components.PostDetailContent
import com.javalenciab90.detail.ui.components.PostDetailScreen
import com.javalenciab90.domain.routes.PostsRoutes
import com.javalenciab90.platform.effect.ObserveEffects

fun NavGraphBuilder.buildDetailScreen(
    navController: NavController
) {
    composable<PostsRoutes.DetailScreen> {

        val viewModel: PostDetailViewModel = hiltViewModel()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        ObserveEffects(flow = viewModel.effects) { effect ->
            when (effect) {
                is PostDetailContract.Effect.EditNewComment -> {
                    // Open Dialog to input text comment
                }
            }
        }

        PostDetailScreen(
            onHandleIntent = viewModel::handleIntent,
            onBack = { navController.popBackStack() }
        ) { paddingValues ->
            PostDetailContent(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onHandleIntent = viewModel::handleIntent
            )
        }
    }
}