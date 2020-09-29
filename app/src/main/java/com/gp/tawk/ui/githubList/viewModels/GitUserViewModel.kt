package com.gp.tawk.ui.githubList.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gp.tawk.core.coroutines.launch
import com.gp.tawk.core.network.TaskStatus
import com.gp.tawk.core.network.clients.ApiClient
import com.gp.tawk.core.network.helpers.ErrorHelper
import com.gp.tawk.core.room.AppDataBase
import com.gp.tawk.core.room.entities.GitUserEntity
import com.gp.tawk.core.utils.SingleLiveEvent
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject

class GitUserViewModel @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHelper: ErrorHelper,
    private val appDataBase: AppDataBase
) : ViewModel() {

    val getUserEvents = SingleLiveEvent<TaskStatus<MutableList<GitUserEntity>>>()
    val getLocalEvents = SingleLiveEvent<TaskStatus<MutableList<GitUserEntity>>>()
    val getSearchEvents = SingleLiveEvent<TaskStatus<MutableList<GitUserEntity>>>()
    val githubUsers = mutableListOf<GitUserEntity>()


    fun getUsers(since: Int) {

        GlobalScope.launch(work = {
            var response = apiClient.getUsers(since)
            for (i in response.indices) {
                if(i == 3){
                    response[i].isInverted = true
                    break
                }


            }


            githubUsers.addAll(response)
            appDataBase.gitUserDao().insert(githubUsers)

            Log.e("size", "" + githubUsers.size)
            githubUsers
        }, onSuccess = {
            getUserEvents.value = TaskStatus.success(it!!)
        }, onError = {
            getUserEvents.value = TaskStatus.failure(errorHelper.getMessage(it))
        })

    }


    fun getUsersFromDb() {

        GlobalScope.launch(work = {
             var response = appDataBase.gitUserDao().get
            githubUsers.addAll(response)
            githubUsers
        }, onSuccess = {
            getLocalEvents.value = TaskStatus.success(githubUsers)
        }, onError = {
            getLocalEvents.value = TaskStatus.failure(errorHelper.getMessage(it))
        })

    }

    fun getUsersFromDb(query:String) {

        GlobalScope.launch(work = {
            var response = appDataBase.gitUserDao().getUser(query)
            githubUsers.clear()
            githubUsers.addAll(response)
            githubUsers
        }, onSuccess = {
            getSearchEvents.value = TaskStatus.success(githubUsers)
        }, onError = {
            getSearchEvents.value = TaskStatus.failure(errorHelper.getMessage(it))
        })

    }


}