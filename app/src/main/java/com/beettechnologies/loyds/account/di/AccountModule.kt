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

    @Singleton
    @Provides
    fun provideLoginRepository(loginApi: LoginApi): LoginRepository {
        return LoginRepositoryImpl(loginApi)
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideForgotPasswordRepository(forgotPasswordApi: ForgotPasswordApi): ForgotPasswordRepository {
        return ForgotPasswordRepositoryImpl(forgotPasswordApi)
    }

    @Singleton
    @Provides
    fun provideForgotPasswordApi(retrofit: Retrofit): ForgotPasswordApi {
        return retrofit.create(ForgotPasswordApi::class.java)
    }
}