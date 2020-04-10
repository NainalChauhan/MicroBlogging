package com.novopay.bloggingapp.view.postdetails

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
import com.novopay.bloggingapp.databinding.ActivityPostDetailsBinding
import com.novopay.bloggingapp.model.Posts
import com.novopay.bloggingapp.util.Constants
import com.novopay.bloggingapp.util.Utils
import kotlinx.android.synthetic.main.activity_post_details.*

class PostDetailsActivity : AppCompatActivity() {

    private val progressVisibility = ObservableInt(View.VISIBLE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mBinding: ActivityPostDetailsBinding = DataBindingUtil.setContentView(this,R.layout.activity_post_details)
        mBinding.progressVisibility = progressVisibility

        //get user data from intent
        val post = intent.getParcelableExtra<Posts>(Constants.post)
        mBinding.post = post

        //set recyclerview
        val commentsAdapter = CommentsAdapter()
        recyclerViewComments.layoutManager = LinearLayoutManager(this)
        recyclerViewComments.adapter = commentsAdapter

        if (Utils.isNetworkAvailable(this)) {

            post?.let { postDetail ->
                //register ViewModel and observe LiveData
                val viewModel = ViewModelProvider(this,PostDetailsViewModelFactory(postDetail.id)).get(PostDetailsViewModel::class.java)
                viewModel.commentsList.observe(this, Observer {
                    commentsAdapter.addItems(it)
                    progressVisibility.set(View.GONE)
                })
                viewModel.error.observe(this, Observer {
                    if(!TextUtils.isEmpty(it)) {
                        progressVisibility.set(View.GONE)
                        Toast.makeText(this@PostDetailsActivity, R.string.something_went_wrong, Toast.LENGTH_LONG).show()
                    }
                })
                viewModel.loading.observe(this, Observer {
                    progressVisibility.set(View.GONE)
                })
            }

        }
        else{
            progressVisibility.set(View.GONE)
            Toast.makeText(this@PostDetailsActivity, R.string.no_internet, Toast.LENGTH_LONG).show()
        }
    }
}
