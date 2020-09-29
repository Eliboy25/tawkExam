package com.gp.tawk.ui.githubList.controllers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gp.tawk.MainApp
import com.gp.tawk.base.BaseLifeCycleController
import com.gp.tawk.core.extensions.*
import com.gp.tawk.core.network.TaskStatus
import com.gp.tawk.core.room.entities.GitUserEntity
import com.gp.tawk.core.service.Receiver
import com.gp.tawk.ui.githubList.viewModels.GitUserViewModel
import com.gp.tawk.ui.githubList.views.GitUserView
import com.gp.tawk.ui.githubList.views.UserDelegate
import com.gp.tawk.ui.profile.controllers.ProfileController
import kotlinx.android.synthetic.main.view_users.view.*
import java.lang.reflect.Type


class GitUserController : BaseLifeCycleController<GitUserViewModel>(), UserDelegate {

    private lateinit var contentView: GitUserView

    override fun inject() {
        MainApp.appComponent.inject(this)
    }

    override fun onCreateControllerView(inflater: LayoutInflater, container: ViewGroup): View {
        return GitUserView(container.context).also {
            this.contentView = it
            it.delegate = this

            initCall()


        }
    }

    fun initCall(){
        contentView.showInitLoading()
        viewModel.getUsersFromDb()

    }

    override fun observeChanges() {
        viewModel.getUserEvents.observe(this, Observer {
            when (it) {
                is TaskStatus.SuccessWithResult -> {

                    Log.e("size", " " + it.result.size)
                    contentView.hideLoading()
                    contentView.user_adapter.setNewItems(it.result)
                }
                is TaskStatus.Failure -> {

                    contentView.hideLoading()
                    contentView.showError(it.errorMessage)
                    Log.e("error", it.errorMessage)
                }
            }
        })

        viewModel.getSearchEvents.observe(this, Observer {
            when (it) {
                is TaskStatus.SuccessWithResult -> {

                    Log.e("size", " " + it.result.size)
                    contentView.hideLoading()
                    contentView.user_adapter.setNewItems(it.result)
                }
                is TaskStatus.Failure -> {

                    contentView.hideLoading()
                    contentView.showError(it.errorMessage)
                    Log.e("error", it.errorMessage)
                }
            }
        })


        viewModel.getLocalEvents.observe(this, Observer {
            when (it) {
                is TaskStatus.SuccessWithResult -> {

                    contentView.hideInitLoading()

                    if (it.result.size !=0){
                        Log.e("size", "fromDB")
                        contentView.user_adapter.setNewItems(it.result)
                    }else{
                        if (contentView.context.isNetworkAvailable()) {
                            contentView.showLoading()
                            viewModel.getUsers(0)
                        } else {
                            contentView.showError("No internet connection!")
                            contentView.iv_no_wifi.show()
                            contentView.rvUsers.gone()
                        }
                    }

                }
                is TaskStatus.Failure -> {

                    contentView.hideInitLoading()
                    contentView.showError(it.errorMessage)
                    Log.e("error", it.errorMessage)
                }
            }
        })

    }


    override fun onSearchAddress(query:String) {
       viewModel.getUsersFromDb("%$query%")


    }


    override fun onItemClicked(user: GitUserEntity) {
        Log.e("CLICK"," " + user.login)
        globalRouter.pushController(ProfileController(bundleOf("user" to user.login)))
    }

    override fun loadMore(lastId : Int) {
        if (contentView.context.isNetworkAvailable()) {
            contentView.showLoading()
            viewModel.getUsers(lastId)
        } else {
            contentView.showError("No internet connection!")
        }
    }
}