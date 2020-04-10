package com.novopay.bloggingapp.view.postdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.R
import com.novopay.bloggingapp.model.Comments

class CommentsAdapter: RecyclerView.Adapter<CommentsViewHolder>() {

    private var list: MutableList<Comments> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_comments,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun addItems(list: List<Comments>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}