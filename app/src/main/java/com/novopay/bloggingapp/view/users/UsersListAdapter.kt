package com.novopay.bloggingapp.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.R
import com.novopay.bloggingapp.model.User

class UsersListAdapter: RecyclerView.Adapter<UsersViewHolder>() {

    private var list: MutableList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_users_list,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun addItems(list: List<User>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}