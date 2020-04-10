package com.novopay.bloggingapp.view.postdetails

import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.databinding.AdapterCommentsBinding
import com.novopay.bloggingapp.model.Comments

class CommentsViewHolder(itemView: AdapterCommentsBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterCommentsBinding = itemView

    fun bindView(comments: Comments){
        mBinding.comments = comments
    }
}