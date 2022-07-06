package com.beettechnologies.loyds.account.di

import com.beettechnologies.loyds.account.login.data.api.LoginApi
import com.beettechnologies.loyds.account.login.data.repository.LoginRepositoryImpl
import com.beettechnologies.loyds.account.login.domain.repository.LoginRepository
import com.beettechnologies.loyds.account.password.forgot.data.api.ForgotPasswordApi
import com.beettechnologies.loyds.account.password.forgot.data.repository.ForgotPasswordRepositoryImpl
import com.beettechnologies.loyds.account.password.forgot.domain.repository.ForgotPasswordRepository
import com.beettechnologies.loyds.account.signup.data.api.SignUpApi
import com.beettechnologies.loyds.account.signup.data.repository.SignUpRepositoryImpl
import com.beettechnologies.loyds.account.signup.domain.repository.SignUpRepository
import dagger.Binds
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
    fun provideSignUpApi(retrofit: Retrofit): SignUpApi {
        return retrofit.create(SignUpApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideForgotPasswordApi(retrofit: Retrofit): ForgotPasswordApi {
        return retrofit.create(ForgotPasswordApi::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindForgotPasswordRepository(forgotPasswordRepositoryImpl: ForgotPasswordRepositoryImpl): ForgotPasswordRepository

    @Binds
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}
