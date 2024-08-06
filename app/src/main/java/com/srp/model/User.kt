package com.srp.model

data class User(
    val userName: String,
    val email: String,
    val tokenKey: String,
    val userId: String,
    val onBoardStatus: Boolean
)