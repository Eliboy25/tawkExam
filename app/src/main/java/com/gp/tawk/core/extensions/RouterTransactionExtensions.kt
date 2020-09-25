package com.gp.tawk.core.extensions

import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler

const val TRANSITION_DURATION = 200L

fun RouterTransaction.horizontalChangeHandler(
    onPushEnd: () -> Unit = {},
    onPopEnd: () -> Unit = {}
): RouterTransaction {
    this.pushChangeHandler(ConductorChangeHandler(TRANSITION_DURATION, onPushEnd))
    this.popChangeHandler(ConductorChangeHandler(TRANSITION_DURATION, onPopEnd))
    return this
}

fun RouterTransaction.verticalChangeHandler(): RouterTransaction {
    this.pushChangeHandler(VerticalChangeHandler(TRANSITION_DURATION))
    this.popChangeHandler(VerticalChangeHandler(TRANSITION_DURATION))
    return this
}

inline fun <reified T> Router.getController(): T? {
    return backstack.singleOrNull {
        it.controller as? T != null
    }?.controller as T?
}