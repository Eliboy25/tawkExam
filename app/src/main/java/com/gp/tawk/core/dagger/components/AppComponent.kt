package com.gp.tawk.core.dagger.components

import android.content.BroadcastReceiver
import com.gp.tawk.MainApp
import com.gp.tawk.core.dagger.modules.ApiModule
import com.gp.tawk.core.dagger.modules.AppModule
import com.gp.tawk.core.dagger.modules.NetworkModule
import com.gp.tawk.core.events.initializer.EventBusInitializer
import com.gp.tawk.core.service.Receiver
import com.gp.tawk.ui.githubList.controllers.GitUserController
import com.gp.tawk.ui.main.activities.MainActivity
import com.gp.tawk.ui.profile.controllers.ProfileController
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    ApiModule::class
])
@Singleton
interface AppComponent {

    fun inject(mainApp: MainApp)
    fun inject(eventBusInitializer: EventBusInitializer)

    fun inject(receiver: Receiver)

    fun inject(mainActivity: MainActivity)

    // controllers
    fun inject(gitUserController: GitUserController)
    fun inject(profileController: ProfileController)


}