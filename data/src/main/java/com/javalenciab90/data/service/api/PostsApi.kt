package com.javalenciab90.data.service.api

import com.javalenciab90.data.service.models.PostDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {

    companion object {
        const val URL_GET_POSTS = "posts"
        const val URL_GET_POST = "posts/${Params.PATH_ID}"
    }

    object Params {
        const val PATH_ID = "{id}"
        const val ID = "id"
    }

    @GET(URL_GET_POSTS)
    suspend fun getPosts() : Response<List<PostDTO>>

    @GET(URL_GET_POST)
    suspend fun getPostDetail(
        @Path(Params.ID) id: Int
    ): Response<PostDTO>
}