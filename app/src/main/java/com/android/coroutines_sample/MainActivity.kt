package com.android.coroutines_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.android.coroutines_sample.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG: String = "TAG"
    private val viewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getPosts()
        viewModel.postList.observe(this) {
            when (it) {
                is Resource.Failed -> {
                    Log.i(TAG, "Failed: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.i(TAG, "Loading:")
                }
                is Resource.Success -> {
                    Log.i(TAG, "Success: ${it.data}")
                }
            }
        }
    }
}