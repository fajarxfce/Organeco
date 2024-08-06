package com.srp.adapter

import com.srp.model.User

class UserDetailsAdapter(private val user: User) : UserDetails {
    override fun getUserName(): String = user.userName
    override fun getEmail(): String = user.email
    override fun getTokenKey(): String = user.tokenKey
    override fun getUserId(): String = user.userId
    override fun getOnBoardStatus(): Boolean = user.onBoardStatus
}