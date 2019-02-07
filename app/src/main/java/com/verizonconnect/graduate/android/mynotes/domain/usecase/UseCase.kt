package com.verizonconnect.graduate.android.mynotes.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out T, in Params> {

    internal abstract fun run(params: Params): T

    fun execute(params: Params, onResult: (T) -> Unit, onError: (java.lang.Exception) -> Unit) {
        val result = GlobalScope.async { run(params) }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                onResult(result.await())
            } catch (e: Exception) {
                e.printStackTrace()
                onError(e)
            }
        }
    }

    class None
}