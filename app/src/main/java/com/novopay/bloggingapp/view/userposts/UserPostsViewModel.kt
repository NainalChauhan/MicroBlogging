package com.novopay.bloggingapp.view.userposts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novopay.bloggingapp.model.Posts
import com.novopay.bloggingapp.model.User
import com.novopay.bloggingapp.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPostsViewModel(val userId: Int) : ViewModel() {

    val postsList = MutableLiveData<List<Posts>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    init {
        getUserPostsList(userId)
    }

    private fun getUserPostsList(userId: Int){
        RetrofitService.get().getUserPosts(userId).enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    postsList.value = response.body()
                    error.value = null
                    loading.value = false
                } else {
                    error.value = response.message()
                    loading.value = false
                }
            }


        })
    }
}