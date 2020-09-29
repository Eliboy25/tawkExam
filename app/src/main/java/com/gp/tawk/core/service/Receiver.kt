package com.gp.tawk.core.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.gp.tawk.MainApp
import com.gp.tawk.ui.githubList.controllers.GitUserController
import com.gp.tawk.ui.main.routers.GlobalRouter
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


open class Receiver: BroadcastReceiver() {


    @Inject lateinit var globalRouter: GlobalRouter



    override fun onReceive(context: Context?, intent: Intent?) {
        MainApp.appComponent.inject(this)

        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        if(activeNetwork?.isConnected == true){
            Log.e("test","connected")
            globalRouter.pushController(GitUserController())
        }

    }

}


