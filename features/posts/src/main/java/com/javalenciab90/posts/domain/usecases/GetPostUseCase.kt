package com.javalenciab90.posts.domain.usecases

import com.javalenciab90.posts.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

}