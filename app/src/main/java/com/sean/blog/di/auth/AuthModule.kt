package com.sean.blog.di.auth

import com.sean.blog.api.auth.ApiAuthService
import com.sean.blog.persistence.AccountPropertiesDao
import com.sean.blog.persistence.AuthTokenDao
import com.sean.blog.repository.auth.AuthRepository
import com.sean.blog.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule{

    @AuthScope
    @Provides
    fun provideFakeApiService(retrofitBuilder: Retrofit.Builder): ApiAuthService {
        return retrofitBuilder
            .build()
            .create(ApiAuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        sessionManager: SessionManager,
        authTokenDao: AuthTokenDao,
        accountPropertiesDao: AccountPropertiesDao,
        apiAuthService: ApiAuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountPropertiesDao,
            apiAuthService,
            sessionManager
        )
    }

}