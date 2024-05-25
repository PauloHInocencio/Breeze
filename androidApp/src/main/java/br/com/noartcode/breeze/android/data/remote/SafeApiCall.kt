package br.com.noartcode.breeze.android.data.remote

import br.com.noartcode.breeze.android.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T) : Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(data = apiCall.invoke())
        } catch (throwable: Throwable) {
            when(throwable){
                is NoInternetException -> {
                    Resource.Error(message = throwable.message, exception = throwable)
                }
                else -> {
                    Resource.Error(message = "Something went wrong!", exception = throwable)
                }
            }
        }
    }
}