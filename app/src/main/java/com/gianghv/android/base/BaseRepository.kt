package com.gianghv.android.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    protected suspend fun <R> flowContext(request: suspend () -> DataResult<R>): Flow<R>
    = flow {
        when (val result = request()) {
            is DataResult.Success -> emit(result.data)
            is DataResult.Error -> throw result.exception
        }
    }

}
