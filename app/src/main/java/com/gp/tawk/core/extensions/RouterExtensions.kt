package com.gp.tawk.core.extensions

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction

fun Router.pushControllerHorizontally(
    controller: Controller
) {
    pushController(RouterTransaction.with(controller).run {
        horizontalChangeHandler(
            onPopEnd = {},
            onPushEnd = {}
        )
    })
}

fun Router.pushControllerVertically(controller: Controller) {
    pushController(RouterTransaction.with(controller).verticalChangeHandler())
}
