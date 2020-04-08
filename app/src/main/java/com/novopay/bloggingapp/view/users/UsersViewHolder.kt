package com.novopay.bloggingapp.view.users

import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.databinding.AdapterUsersListBinding
import com.novopay.bloggingapp.model.User

class UsersViewHolder(itemView: AdapterUsersListBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterUsersListBinding = itemView

    fun bindView(user: User){
        mBinding.user = user
    }
}