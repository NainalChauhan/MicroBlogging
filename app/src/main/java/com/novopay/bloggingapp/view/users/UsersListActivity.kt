package com.novopay.bloggingapp.view.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.novopay.bloggingapp.R
import com.novopay.bloggingapp.databinding.ActivityUsersListBinding
import com.novopay.bloggingapp.model.User
import com.novopay.bloggingapp.util.Constants
import com.novopay.bloggingapp.util.ItemClickListener
import com.novopay.bloggingapp.util.Utils
import com.novopay.bloggingapp.view.userposts.UserPostsActivity
import kotlinx.android.synthetic.main.activity_users_list.*

class UsersListActivity : AppCompatActivity() , ItemClickListener {

    private val progressVisibility = ObservableInt(View.VISIBLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityUsersListBinding = DataBindingUtil.setContentView(this,R.layout.activity_users_list)
        mBinding.progressVisibility = progressVisibility

        //set recyclerView
        val usersListAdapter = UsersListAdapter(this)
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
                if(!TextUtils.isEmpty(it)) {
                    progressVisibility.set(View.GONE)
                    Toast.makeText(
                        this@UsersListActivity,
                        R.string.something_went_wrong,
                        Toast.LENGTH_LONG
                    ).show()
                }
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

    override fun onItemClick(modelClass: Any) {
        val userObj = modelClass as User
        startActivity(Intent(this,UserPostsActivity::class.java).
                putExtra(Constants.id, userObj.id).
                putExtra(Constants.name, userObj.name).
                putExtra(Constants.email, userObj.email)
        )
    }
}
