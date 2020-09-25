package com.gp.tawk.core.extensions

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.bluelinelabs.conductor.changehandler.AnimatorChangeHandler

class ConductorChangeHandler : AnimatorChangeHandler {

    private var onEndAction: (() -> Unit)? = null

    constructor() : super()
    constructor(removesFromViewOnPush: Boolean) : super(removesFromViewOnPush)
    constructor(duration: Long, onEndAction: (() -> Unit)? = null) : super(duration) {
        this.onEndAction = onEndAction
    }

    constructor(
        duration: Long,
        removesFromViewOnPush: Boolean,
        onEndAction: (() -> Unit)? = null
    ) : super(
        duration,
        removesFromViewOnPush
    ) {
        this.onEndAction = onEndAction
    }

    override fun getAnimator(
        container: ViewGroup,
        from: View?,
        to: View?,
        isPush: Boolean,
        toAddedToContainer: Boolean
    ): Animator {
        val animatorSet = AnimatorSet()

        if (isPush) {
            if (to != null) {
                val startPosition = to.width.toFloat() * (0.3f)
                val endPosition = 0f
                val translationXAnimation =
                    ObjectAnimator.ofFloat(to, View.TRANSLATION_X, startPosition, endPosition)

                val endAlpha = 1.0f
                val alphaAnimation = ObjectAnimator.ofFloat(to, View.ALPHA, 0.0f, endAlpha)
                alphaAnimation.interpolator = FastOutSlowInInterpolator()
                translationXAnimation.interpolator = FastOutSlowInInterpolator()

                animatorSet.play(translationXAnimation)
                animatorSet.play(alphaAnimation)
            }
        } else {
            if (from != null) {
                val startPosition = 0f
                val endPosition = from.width.toFloat()
                val translationXAnimation = ObjectAnimator.ofFloat<View>(
                    from,
                    View.TRANSLATION_X,
                    startPosition,
                    endPosition
                )

                val exitStartAlpha = 1f
                val endAlpha = 0.4f
                val alphaAnimation =
                    ObjectAnimator.ofFloat(from, View.ALPHA, exitStartAlpha, endAlpha)

                alphaAnimation.interpolator = FastOutSlowInInterpolator()
                translationXAnimation.interpolator = FastOutSlowInInterpolator()
                animatorSet.playTogether(translationXAnimation)
            }
        }

        animatorSet.doOnEnd {
            onEndAction?.invoke()
        }

        return animatorSet
    }

    override fun resetFromView(from: View) {
        from.translationX = 0.0f
    }
}
