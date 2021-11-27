package com.example.stattrack.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

inline fun <T, R> Flow<Iterable<T>>.mapIterable(crossinline trans: suspend (value: T) -> R): Flow<List<R>> = transform { iterable ->
    return@transform emit(iterable.map { trans(it) })
}