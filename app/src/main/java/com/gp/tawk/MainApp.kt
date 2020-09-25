package com.gp.tawk

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.gp.tawk.core.dagger.components.AppComponent
import com.gp.tawk.core.dagger.components.DaggerAppComponent
import com.gp.tawk.core.dagger.modules.ApiModule
import com.gp.tawk.core.dagger.modules.AppModule
import com.gp.tawk.core.dagger.modules.NetworkModule
import com.gp.tawk.core.events.initializer.EventBusInitializer
import net.danlew.android.joda.JodaTimeAndroid
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import javax.inject.Inject

class MainApp : Application() {

    @Inject
    lateinit var eventBusInitializer: EventBusInitializer
    @Inject
    lateinit var eventBus: EventBus

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this);
        Timber.plant(Timber.DebugTree())

        /** initialize okhttp client  */
        var cacheDir = externalCacheDir
        if (cacheDir == null) {
            cacheDir = getCacheDir()
        }

        val cache = cacheDir?.let { Cache(it, (10 * 1024 * 1024).toLong()) }



        try {
            appContext = applicationContext
            appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .networkModule(
                    cache?.let {
                        NetworkModule(
                            apiUrl = BuildConfig.SERVER_URL,
                            interceptorLevel = HttpLoggingInterceptor.Level.BODY,
                            cache = it
                        )
                    }
                ).apiModule(ApiModule())
                .build()

            appComponent.inject(this)

            registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityDestroyed(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
                override fun onActivityResumed(activity: Activity) {

                }
            })

            eventBusInitializer.initialize()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}