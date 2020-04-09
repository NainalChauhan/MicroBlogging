package com.novopay.bloggingapp.view.users

import androidx.recyclerview.widget.RecyclerView
import com.novopay.bloggingapp.databinding.AdapterUsersListBinding
import com.novopay.bloggingapp.model.User
import com.novopay.bloggingapp.util.ItemClickListener

class UsersViewHolder(itemView: AdapterUsersListBinding) : RecyclerView.ViewHolder(itemView.root) {

    private val mBinding : AdapterUsersListBinding = itemView

    fun bindView(user: User, clickListener: ItemClickListener?){
        mBinding.user = user
        itemView.setOnClickListener{
            clickListener?.onItemClick(user)
        }
    }
}