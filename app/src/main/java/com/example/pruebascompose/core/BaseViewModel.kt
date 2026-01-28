package com.example.pruebascompose.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebascompose.core.exceptions.BusinessException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected fun executeUseCase(
        action: suspend CoroutineScope.() -> Unit,
        exceptionHandler: suspend (BusinessException) -> Unit,
        finallyHandler: (suspend () -> Unit)? = null,
    ) {
        viewModelScope.launch {
            try {
                action.invoke(this)
            } catch (e: Throwable) {
                ensureActive()
                exceptionHandler.invoke(e.parseException())
            } finally {
                finallyHandler?.invoke()
            }
        }
    }
}