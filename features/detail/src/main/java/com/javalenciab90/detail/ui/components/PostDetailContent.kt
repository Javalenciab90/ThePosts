package com.javalenciab90.detail.ui.components

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
import com.javalenciab90.detail.ui.PostDetailContract
import com.javalenciab90.detail.ui.Status
import com.javalenciab90.domain.models.PostComment

@Composable
fun PostDetailContent(
    modifier: Modifier = Modifier,
    uiState: PostDetailContract.PostsDetailState,
    onHandleIntent: (PostDetailContract.Intent) -> Unit
) {
    if (uiState.showDialogComment) {
        AddCommentDialog(
            onDismiss = {
                onHandleIntent(PostDetailContract.Intent.DismissDialogComment)
            },
            onConfirm = { text ->
                onHandleIntent(PostDetailContract.Intent.AddNewComment(text))
            }
        )
    }

    when (uiState.status) {
        is Status.Loading -> {
            LoadingScreen()
        }
        is Status.Success -> {
            PostCommentSuccess(
                modifier = modifier,
                comments = uiState.status.data
            )
        }
        is Status.Empty -> {
            EmptyScreen(
                title = "Este Post no tiene comentario aún",
                content = ""
            )
        }
        is Status.Error -> {
            ErrorScreen(uiState.status.errorMessage)
        }
    }
}

@Composable
fun PostCommentSuccess(
    modifier: Modifier = Modifier,
    comments: List<PostComment>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = comments
        ) { comment ->
            CommentCard(
                comment = comment
            )
        }
    }
}