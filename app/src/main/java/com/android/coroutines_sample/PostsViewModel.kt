package com.android.coroutines_sample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.coroutines_sample.data.PostRepository
import com.android.coroutines_sample.model.Post
import com.android.coroutines_sample.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    private var _postList: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val postList: LiveData<Resource<List<Post>>>
        get() = _postList

    fun getPosts() = viewModelScope.launch {
        repository.getPost().collect {
            _postList.value = it
        }
    }
}