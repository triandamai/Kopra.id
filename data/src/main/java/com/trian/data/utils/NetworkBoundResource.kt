package com.trian.data.utils

import com.trian.common.utils.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
/**
 * for synchronized between local and network
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 01/09/2021
 */

@ExperimentalCoroutinesApi
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline clearData: suspend () -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline shouldClear: (RequestType, ResultType) -> Boolean = { requestType: RequestType, resultType: ResultType -> false }
) = flow {
    val dbData = query().first()
    val flow = if (shouldFetch(dbData)) {
        try {
            val fetchData = fetch()
            if (shouldClear(fetchData, dbData)) {
                clearData()
            }
            saveFetchResult(fetchData)
            query().map { NetworkStatus.Success(it) }

        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { NetworkStatus.Error(throwable.message, it) }
        }

    } else {
        query().map { NetworkStatus.Success(it) }
    }
    emitAll(flow)
}