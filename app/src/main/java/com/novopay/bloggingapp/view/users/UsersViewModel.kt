package com.novopay.bloggingapp.view.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.novopay.bloggingapp.model.User
import com.novopay.bloggingapp.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersViewModel : ViewModel() {

    val usersList = MutableLiveData<List<User>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    init {
        getUsersList()
    }

    private fun getUsersList(){
        RetrofitService.get().getUserList().enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    usersList.value = response.body()
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