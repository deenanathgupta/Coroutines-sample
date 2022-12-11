package com.android.coroutines_sample.data

import com.android.coroutines_sample.network.PostService
import com.android.coroutines_sample.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val postService: PostService) {

    fun getPost() = flow {
        emit(Resource.loading())
        val posts = postService.getPosts()
        emit(Resource.success(posts))
    }.catch {
        emit(Resource.failed(it.message ?: "Something went wrong"))
    }.flowOn(Dispatchers.IO)
}