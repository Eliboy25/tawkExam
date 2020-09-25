package com.gp.tawk.ui.main.activities

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.*
import com.gp.tawk.MainApp
import com.gp.tawk.core.extensions.getController
import com.gp.tawk.core.extensions.pushControllerHorizontally
import com.gp.tawk.ui.main.routers.GlobalRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import com.gp.tawk.R
import com.gp.tawk.ui.githubList.controllers.GitUserController
import javax.inject.Inject



class MainActivity : AppCompatActivity() {

    private lateinit var mainRouter: Router

    @Inject lateinit var globalRouter: GlobalRouter
    @Inject lateinit var eventBus: EventBus

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainApp.appComponent.inject(this)
        setContentView(R.layout.activity_main)


        mainRouter = Conductor.attachRouter(
            this,
            controllerContainer,
            savedInstanceState
        )

        if (!mainRouter.hasRootController()) {
            mainRouter.setRoot(
                RouterTransaction.with(
                    GitUserController()

                )
            )
        }

        initMainRouterChangeListener()
        initNavigationRouters()
    }


    override fun onBackPressed() {
        if (mainRouter.backstackSize > 1) {
            if (mainRouter.getController<GitUserController>() == null) {
                disposable.clear()
                super.onBackPressed()
            } else {
                mainRouter.handleBack()
            }
        } else {
            disposable.clear()
            super.onBackPressed()
        }
    }


    private fun initNavigationRouters() {
        disposable.clear()
        disposable.add(
            globalRouter
                .getRouterObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy({
                    mainRouter.pushControllerHorizontally(it)
                }, {
                    Timber.e(it)
                })
        )
    }

    private fun initMainRouterChangeListener() {
        mainRouter.addChangeListener(object : ControllerChangeHandler.ControllerChangeListener {
            override fun onChangeStarted(
                to: Controller?,
                from: Controller?,
                isPush: Boolean,
                container: ViewGroup,
                handler: ControllerChangeHandler
            ) {
            }

            @SuppressLint("SourceLockedOrientationActivity")
            override fun onChangeCompleted(
                to: Controller?,
                from: Controller?,
                isPush: Boolean,
                container: ViewGroup,
                handler: ControllerChangeHandler
            ) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT



            }
        })
    }


}
