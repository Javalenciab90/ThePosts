package com.javalenciab90.data.mapper

import com.javalenciab90.data.service.models.PostDTO
import com.javalenciab90.domain.Post
import com.javalenciab90.domain.mapper.DataMapper
import javax.inject.Inject

class NetworkDataMapper @Inject constructor() : DataMapper<List<PostDTO>, List<Post>> {

    override fun map(input: List<PostDTO>): List<Post> {
        return input.map {
            Post(
                userId = it.userId,
                id = it.id,
                title = it.title,
                body = it.body
            )
        }
    }
}