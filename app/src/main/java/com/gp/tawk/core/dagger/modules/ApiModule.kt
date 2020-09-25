package com.gp.tawk.core.dagger.modules

import com.gp.tawk.core.network.clients.ApiClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesApiClient(retrofit: Retrofit) = retrofit.create(ApiClient::class.java)
}
