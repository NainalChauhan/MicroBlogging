package com.novopay.bloggingapp.view.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostDetailsViewModelFactory (private val postId: Int) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PostDetailsViewModel(postId ) as T
        }
}