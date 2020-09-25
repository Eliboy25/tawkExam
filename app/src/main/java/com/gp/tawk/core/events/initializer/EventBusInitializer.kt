package com.gp.tawk.core.events.initializer

import com.gp.tawk.MainApp
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventBusInitializer @Inject constructor() {

    @Inject lateinit var eventBus: EventBus

    fun initialize() {
        MainApp.appComponent.inject(this)
    }
}