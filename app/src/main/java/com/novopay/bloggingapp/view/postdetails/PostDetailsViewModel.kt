package com.novopay.bloggingapp.view.postdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novopay.bloggingapp.model.Comments
import com.novopay.bloggingapp.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDetailsViewModel(postId: Int) : ViewModel() {

    val commentsList = MutableLiveData<List<Comments>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    init {
        getPostCommentsList(postId)
    }

    private fun getPostCommentsList(postId: Int){
        RetrofitService.get().getPostComments(postId).enqueue(object : Callback<List<Comments>> {
            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }

            override fun onResponse(call: Call<List<Comments>>, response: Response<List<Comments>>) {
                if (response.isSuccessful) {
                    commentsList.value = response.body()
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