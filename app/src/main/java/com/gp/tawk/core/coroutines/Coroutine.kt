package com.gp.tawk.core.coroutines

import kotlinx.coroutines.*

fun <T : Any> CoroutineScope.launch(
    work: suspend CoroutineScope.() -> T?,
    onSuccess: ((T?) -> Unit),
    onError: ((error: Throwable) -> Unit)
): Job {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        CoroutineScope(Dispatchers.Main).launch {
            onError(throwable)
        }
    }

    return this@launch.launch(Dispatchers.Main + errorHandler) {
        val data = async(Dispatchers.IO) rt@{ return@rt work() }.await()
        onSuccess(data)
    }
}

fun CoroutineScope.launchIgnoringExceptions(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    work: suspend CoroutineScope.() -> Unit
): Job {
    return launch(dispatcher + CoroutineExceptionHandler { _, _ -> }) {
        work()
    }
}