package com.novopay.bloggingapp.view.userposts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.R
import com.novopay.bloggingapp.model.Posts
import com.novopay.bloggingapp.util.ItemClickListener

class UserPostsAdapter(private val clickListener: ItemClickListener?): RecyclerView.Adapter<UserPostsViewHolder>() {

    private var list: MutableList<Posts> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserPostsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_user_posts,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UserPostsViewHolder, position: Int) {
        holder.bindView(list[position], clickListener)
    }

    fun addItems(list: List<Posts>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}