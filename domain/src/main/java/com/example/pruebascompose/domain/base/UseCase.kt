package com.example.pruebascompose.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

abstract class UseCase<I, O> {

    suspend fun execute(input: I): O {
        return withContext(dispatcher) { useCaseFunction(input) }
    }

    fun executeSyncInDispatcher(input: I): O {
        return runBlocking { withContext(dispatcher) { useCaseFunction(input) } }
    }

    fun executeSyncInCurrentThread(input: I): O {
        return runBlocking { useCaseFunction(input) }
    }

    protected abstract suspend fun useCaseFunction(input: I): O

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO
}
