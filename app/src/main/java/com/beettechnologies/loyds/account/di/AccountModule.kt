package com.beettechnologies.loyds.account.di

import com.beettechnologies.loyds.account.signup.data.api.SignUpApi
import com.beettechnologies.loyds.account.signup.data.repository.SignUpRepositoryImpl
import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {

    @Singleton
    @Provides
    fun provideSignUpRepository(signUpApi: SignUpApi): SignUpRepository {
        return SignUpRepositoryImpl(signUpApi)
    }

    @Singleton
    @Provides
    fun provideSignUpApi(retrofit: Retrofit): SignUpApi {
        return retrofit.create(SignUpApi::class.java)
    }
}