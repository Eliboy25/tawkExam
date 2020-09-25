package com.gp.tawk.core.dagger.modules

import com.google.gson.GsonBuilder
import com.gp.tawk.core.converters.LocalDateDeserializer
import com.gp.tawk.core.converters.LocalDateSerializer
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import org.joda.time.LocalDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(
    private val apiUrl: String,
    private val interceptorLevel: HttpLoggingInterceptor.Level,
    private val cache: Cache
) {

    @Provides
    @Singleton
    fun providesOkHttpClient(eventBus: EventBus): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(interceptorLevel)



        return OkHttpClient
            .Builder()
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .cache(cache)
            .addInterceptor(
                HttpLoggingInterceptor()
                    .also {
                        it.setLevel(interceptorLevel)
                    }
            ).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create(createGson()))
            .client(okHttpClient)
            .build()
    }

    private fun createGson() = GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateDeserializer()
        ).registerTypeAdapter(
            LocalDateTime::class.java,
            LocalDateSerializer()
        ).create()
}
