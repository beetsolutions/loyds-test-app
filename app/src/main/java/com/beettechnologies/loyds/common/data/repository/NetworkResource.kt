package com.beettechnologies.loyds.common.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.beettechnologies.loyds.common.data.model.ApiResponse
import com.beettechnologies.loyds.common.data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkResource<ResultType, RequestType> {

    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading())

        when (val apiResponse = createCall()) {
            is ApiResponse.ApiSuccessResponse -> {
                val flow = loadResults(processResponse(apiResponse))
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiEmptyResponse -> {
                val flow = loadResults()
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiErrorResponse -> {
                emit(Resource.error(apiResponse.errorMessage))
            }
        }
    }

    @WorkerThread
    protected open fun processResponse(r: ApiResponse.ApiSuccessResponse<RequestType>) =  r.body

    @WorkerThread
    protected abstract suspend fun loadResults(item: RequestType? = null): Flow<ResultType>

    @MainThread
    protected abstract suspend fun createCall(): ApiResponse<RequestType>
}