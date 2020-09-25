package com.gp.tawk.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.bluelinelabs.conductor.archlifecycle.LifecycleController
import com.evernote.android.state.StateSaver
import com.gp.tawk.core.utils.ViewModelUtils
import com.gp.tawk.ui.main.routers.GlobalRouter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusException
import javax.inject.Inject

abstract class BaseLifeCycleController<T : ViewModel> : LifecycleController {

    @Inject lateinit var viewModel: T
    @Inject lateinit var eventBus: EventBus
    @Inject lateinit var globalRouter: GlobalRouter

    constructor(args:  Bundle): super(args)
    constructor(): super()

    abstract fun inject()
    abstract fun onCreateControllerView(inflater: LayoutInflater, container: ViewGroup): View
    abstract fun observeChanges()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        inject()
        viewModel = ViewModelProvider(
            ViewModelStore(),
            ViewModelUtils.createFor(viewModel)
        ).get(viewModel::class.java)
        return onCreateControllerView(
            inflater,
            container
        ).also {
            observeChanges()
        }

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
