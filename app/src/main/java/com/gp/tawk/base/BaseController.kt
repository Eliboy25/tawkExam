package com.gp.tawk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.evernote.android.state.StateSaver
import com.gp.tawk.ui.main.routers.GlobalRouter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusException
import javax.inject.Inject

abstract class BaseController : Controller() {

    @Inject
    lateinit var eventBus: EventBus
    @Inject
    lateinit var globalRouter: GlobalRouter

    abstract fun inject()

    abstract fun onCreateControllerView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        inject()
        return onCreateControllerView(inflater, container)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)

        if (!eventBus.isRegistered(this)) {
            try {
                eventBus.register(this)
            } catch (e: EventBusException) {
                // intended to handle controllers w/o @Subscribe annotation
            }
        }
    }

    override fun onDetach(view: View) {
        super.onDetach(view)

        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this)
        }
    }

    fun dismissController() {
        router.popCurrentController()
    }
}
