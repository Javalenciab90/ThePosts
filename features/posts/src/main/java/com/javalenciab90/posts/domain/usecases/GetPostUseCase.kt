package com.javalenciab90.posts.domain.usecases

import com.javalenciab90.domain.models.Post
import com.javalenciab90.posts.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    operator fun invoke(
        query: String,
        posts: List<Post>
    ) : List<Post> {

        if (query.isBlank()) return emptyList()

        return posts.filter { post ->
            post.id.toString() == query ||
            post.title.contains(query, ignoreCase = true)
        }
    }
}