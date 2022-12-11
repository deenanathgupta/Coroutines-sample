package com.android.coroutines_sample.network

import com.android.coroutines_sample.model.Post
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getPosts():List<Post>
}