package com.javalenciab90.posts.domain.usecases

import com.javalenciab90.domain.models.Post
import com.javalenciab90.posts.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke() : Flow<List<Post>> {
        return postsRepository.getAllPosts()
    }
}