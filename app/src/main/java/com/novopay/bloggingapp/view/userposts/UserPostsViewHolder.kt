package com.novopay.bloggingapp.view.userposts

import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.databinding.AdapterUserPostsBinding
import com.novopay.bloggingapp.model.Posts
import com.novopay.bloggingapp.util.ItemClickListener

class UserPostsViewHolder(itemView: AdapterUserPostsBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterUserPostsBinding = itemView

    fun bindView(posts: Posts, clickListener: ItemClickListener?){
        mBinding.post = posts
        itemView.setOnClickListener{
            clickListener?.onItemClick(posts)
        }
    }
}