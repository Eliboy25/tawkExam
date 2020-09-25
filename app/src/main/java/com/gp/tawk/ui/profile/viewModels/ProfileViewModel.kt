package com.gp.tawk.ui.profile.viewModels

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

class ProfileViewModel @Inject constructor(
    private val apiClient: ApiClient,
    private val errorHelper: ErrorHelper,
    private val appDataBase: AppDataBase
) : ViewModel() {

    val getUserEvents = SingleLiveEvent<TaskStatus<GitUserEntity>>()

    val saveEvent = SingleLiveEvent<TaskStatus<Any>>()

    fun getUserProfle(username: String) {

        GlobalScope.launch(work = {
            apiClient.getUserProfile(username)

        }, onSuccess = {
            getUserEvents.value = TaskStatus.success(it!!)
        }, onError = {
            getUserEvents.value = TaskStatus.failure(errorHelper.getMessage(it))
        })

    }


    fun updateNote(id:Int,note: String) {

        GlobalScope.launch(work = {
            appDataBase.gitUserDao().updateNotes(id,note)

        }, onSuccess = {
            saveEvent.value = TaskStatus.success()
        }, onError = {
            saveEvent.value = TaskStatus.failure(errorHelper.getMessage(it))
        })

    }




}