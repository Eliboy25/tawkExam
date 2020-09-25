package com.gp.tawk.ui.main.routers

import com.bluelinelabs.conductor.Controller
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalRouter @Inject constructor() {

    private val routerSubject = PublishSubject.create<Controller>()

    fun getRouterObservable() : Observable<Controller> = routerSubject.hide()

    fun pushController(controller: Controller) = routerSubject.onNext(controller)
}
