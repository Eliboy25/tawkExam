package com.gp.tawk.ui.profile.controllers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.gp.tawk.MainApp
import com.gp.tawk.base.BaseLifeCycleController
import com.gp.tawk.core.extensions.isNetworkAvailable
import com.gp.tawk.core.extensions.showError
import com.gp.tawk.core.network.TaskStatus
import com.gp.tawk.ui.githubList.controllers.GitUserController
import com.gp.tawk.ui.profile.viewModels.ProfileViewModel
import com.gp.tawk.ui.profile.views.ProfileDelegate
import com.gp.tawk.ui.profile.views.ProfileView


class ProfileController(bundle: Bundle) : BaseLifeCycleController<ProfileViewModel>(bundle), ProfileDelegate {

    private lateinit var contentView: ProfileView

    override fun inject() {
        MainApp.appComponent.inject(this)
    }

    override fun onCreateControllerView(inflater: LayoutInflater, container: ViewGroup): View {
        return ProfileView(container.context).also {
            this.contentView = it
            it.delegate = this

            var user = args.getString("user")
            if (contentView.context.isNetworkAvailable()) {

                contentView.showLoading()
                user?.let { it1 -> viewModel.getUserProfle(it1) }
            } else {
                contentView.showError("No internet connection!")
            }


        }
    }

    override fun observeChanges() {
        viewModel.getUserEvents.observe(this, Observer {
            when (it) {
                is TaskStatus.SuccessWithResult -> {
                    contentView.setDetails(it.result)
                }
                is TaskStatus.Failure -> {

                    contentView.hideLoading()
                    contentView.showError(it.errorMessage)
                    Log.e("error", it.errorMessage)
                }
            }
        })

        viewModel.saveEvent.observe(this, Observer {
            when (it) {
                is TaskStatus.Success -> {
                    dismissController()
                    globalRouter.pushController(GitUserController())
                }
                is TaskStatus.Failure -> {
                    contentView.showError(it.errorMessage)
                }
            }
        })

    }






    override fun onBackPressed() {
        dismissController()
    }

    override fun updateInfo(id: Int,note:String) {
        viewModel.updateNote(id,note)
    }
}