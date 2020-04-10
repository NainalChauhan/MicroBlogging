package com.novopay.bloggingapp.view.userposts

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
import com.novopay.bloggingapp.databinding.ActivityUserPostsBinding
import com.novopay.bloggingapp.model.Posts
import com.novopay.bloggingapp.model.User
import com.novopay.bloggingapp.util.Constants
import com.novopay.bloggingapp.util.ItemClickListener
import com.novopay.bloggingapp.util.Utils
import com.novopay.bloggingapp.view.postdetails.PostDetailsActivity
import kotlinx.android.synthetic.main.activity_user_posts.*

class UserPostsActivity : AppCompatActivity(), ItemClickListener {

    private val progressVisibility = ObservableInt(View.VISIBLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding : ActivityUserPostsBinding = DataBindingUtil.setContentView(this,R.layout.activity_user_posts)
        mBinding.progressVisibility = progressVisibility

        //get user data from intent
        val id = intent.getIntExtra(Constants.id,0)
        val name = intent.getStringExtra(Constants.name)
        val email = intent.getStringExtra(Constants.email)

        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email))
            finish()

        mBinding.user = User(id,name!!,"",email!!)

        //set recyclerview
        val postsAdapter = UserPostsAdapter(this)
        recyclerViewPosts.layoutManager = LinearLayoutManager(this)
        recyclerViewPosts.adapter = postsAdapter

        if (Utils.isNetworkAvailable(this)) {
            //register ViewModel and observe LiveData
            val viewModel = ViewModelProvider(this,UserPostViewModelFactory(id)).get(UserPostsViewModel::class.java)
            viewModel.postsList.observe(this, Observer {
                postsAdapter.addItems(it)
                progressVisibility.set(View.GONE)
            })
            viewModel.error.observe(this, Observer {
                if(!TextUtils.isEmpty(it)) {
                    progressVisibility.set(View.GONE)
                    Toast.makeText(this@UserPostsActivity, it, Toast.LENGTH_LONG).show()
                }
            })
            viewModel.loading.observe(this, Observer {
                progressVisibility.set(View.GONE)
            })
        }
        else{
            progressVisibility.set(View.GONE)
            Toast.makeText(this@UserPostsActivity, R.string.no_internet, Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemClick(modelClass: Any) {
        val postObj = modelClass as Posts
        startActivity(
            Intent(this,PostDetailsActivity::class.java)
                .putExtra(Constants.post,postObj)
        )
    }
}
