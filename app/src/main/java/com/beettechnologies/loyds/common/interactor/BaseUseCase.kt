package com.beettechnologies.loyds.common.interactor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<ParamsType, ReturnType> {

    open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    abstract suspend fun buildUseCase(params: ParamsType): ReturnType

    suspend operator fun invoke(params: ParamsType) = withContext(dispatcher) {
        buildUseCase(params)
    }
}
