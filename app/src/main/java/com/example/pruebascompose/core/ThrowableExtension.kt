package com.example.pruebascompose.core

import com.example.pruebascompose.core.exceptions.BusinessException


fun Throwable.parseException() : BusinessException =
    if (this is BusinessException) {
        this
    } else {
       // Timber.i(this)
        BusinessException(cause = this)
    }

