package com.novopay.bloggingapp.model

class Comments (
    val id: Int,
    val postId: Int,
    val name: String,
    val body: String,
    val email: String
)