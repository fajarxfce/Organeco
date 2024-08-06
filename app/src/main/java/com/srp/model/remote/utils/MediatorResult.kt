package com.srp.model.remote.utils

sealed class MediatorResult<out R> private constructor() {
    data class Success<out T>(val data: T) : MediatorResult<T>()
    data class Error(val error: String) : MediatorResult<Nothing>()
    object Loading : MediatorResult<Nothing>()
}