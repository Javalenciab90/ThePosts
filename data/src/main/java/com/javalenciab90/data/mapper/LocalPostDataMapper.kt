package com.javalenciab90.data.mapper

import com.javalenciab90.data.room.entities.PostEntity
import com.javalenciab90.domain.models.Post
import com.javalenciab90.domain.mapper.DataMapper
import javax.inject.Inject


class LocalPostDataMapper @Inject constructor(): DataMapper<List<Post>, List<PostEntity>>{

    override fun map(input: List<Post>): List<PostEntity> {
        return input.map {
            PostEntity(
                id = it.id,
                userId = it.userId,
                title = it.title,
                body = it.body
            )
        }
    }
}