package com.novopay.bloggingapp.view.userposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserPostViewModelFactory (private val userId: Int) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserPostsViewModel(userId ) as T
        }
}