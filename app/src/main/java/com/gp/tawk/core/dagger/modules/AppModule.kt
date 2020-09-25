package com.gp.tawk.core.dagger.modules

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.gp.tawk.core.room.AppDataBase
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesEventBus() = EventBus()

    @Provides
    @Singleton
    fun providesRoomDatabase() = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "sign-docu-db"
    ).build()

    @Provides
    @Singleton
    fun providesWorkerManager() = WorkManager.getInstance(context)
}
