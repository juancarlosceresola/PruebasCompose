package com.example.pruebascompose.core.extensions

import com.example.pruebascompose.core.exceptions.BusinessException
import com.example.pruebascompose.core.exceptions.ErrorType
import retrofit2.Response

fun <T> Response<T>.parseResponse(): T {
    val body = this.body()
    if (this.isSuccessful && body != null) {
        return body
    } else {
        throw BusinessException(getErrorType(this.code()),"$this.code() $this.message()")
    }
}

private fun getErrorType(code: Int): ErrorType {
    return when(code){
        401 -> ErrorType.UNAUTHORIZED
        else -> ErrorType.UNEXPECTED
    }
}