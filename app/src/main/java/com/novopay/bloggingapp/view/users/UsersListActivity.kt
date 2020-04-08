package com.novopay.bloggingapp.view.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.novopay.bloggingapp.R
import com.novopay.bloggingapp.databinding.ActivityUsersListBinding
import com.novopay.bloggingapp.util.Utils
import kotlinx.android.synthetic.main.activity_users_list.*

class UsersListActivity : AppCompatActivity() {

    private val progressVisibility = ObservableInt(View.VISIBLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityUsersListBinding = DataBindingUtil.setContentView(this,R.layout.activity_users_list)
        mBinding.progressVisibility = progressVisibility

        //set recyclerView
        val usersListAdapter = UsersListAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = usersListAdapter

        if (Utils.isNetworkAvailable(this)) {
            //register ViewModel and observe LiveData
            val viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
            viewModel.usersList.observe(this, Observer {
                usersListAdapter.addItems(it)
                progressVisibility.set(View.GONE)
            })
            viewModel.error.observe(this, Observer {
                progressVisibility.set(View.GONE)
                Toast.makeText(
                    this@UsersListActivity,
                    R.string.something_went_wrong,
                    Toast.LENGTH_LONG
                ).show()
            })
            viewModel.loading.observe(this, Observer {
                progressVisibility.set(View.GONE)
            })
        }
        else{
            progressVisibility.set(View.GONE)
            Toast.makeText(
                this@UsersListActivity,
                R.string.no_internet,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
