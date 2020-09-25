package com.gp.tawk.ui.githubList.views

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gp.tawk.R
import com.gp.tawk.core.extensions.gone
import com.gp.tawk.core.extensions.show
import com.gp.tawk.core.room.entities.GitUserEntity
import com.gp.tawk.ui.githubList.adapters.UserAdapter
import kotlinx.android.synthetic.main.layout_view_toolbar.view.*
import kotlinx.android.synthetic.main.view_users.view.*
import timber.log.Timber
import java.util.*
import kotlin.concurrent.schedule

interface UserDelegate {
    fun onItemClicked(user: GitUserEntity)
    fun loadMore(lastId:Int)
    fun onSearchAddress(query:String)
}

class GitUserView(context: Context) : FrameLayout(context) {

    val user_adapter = UserAdapter(mutableListOf()) {
        delegate?.onItemClicked(it)
    }

    var delegate: UserDelegate? = null

    init {
        inflate(context, R.layout.view_users, this)
        back.visibility = View.GONE
        shimmer_view_container.startShimmerAnimation()
        initSearch()

        with(rvUsers, {
            this.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
            this.adapter = this@GitUserView.user_adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (this@GitUserView.searchView.query.isNullOrEmpty()){ //if search has query , load more is disable
                        if (!rvUsers.canScrollVertically(1)) {


                            var count = this@GitUserView.user_adapter.itemCount

                            var pos = this@GitUserView.user_adapter.getItem(count-1)
                            Log.e("End of line", " " + pos.login)
                            delegate?.loadMore(pos.id)



                        }
                    }

                }
            })


        })



    }

    fun initSearch(){

        searchView.isFocusable = false;
        var timer = Timer()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                Timber.e("onQueryTextChange $newText")
                timer.cancel()
                val sleep = when (newText.length) {
                    1 -> 1000L
                    2, 3 -> 700L
                    4, 5 -> 500L
                    else -> 300L
                }
                timer = Timer()
                timer.schedule(sleep) {

                        delegate?.onSearchAddress(newText)
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                Timber.e("onQueryTextSubmit $query")
                timer.cancel()
                val sleep = when (query.length) {
                    1 -> 1000L
                    2, 3 -> 700L
                    4, 5 -> 500L
                    else -> 300L
                }
                timer = Timer()
                timer.schedule(sleep) {

                        delegate?.onSearchAddress(query)
                }
                return false
            }
        })

    }

    fun showInitLoading() {
        nestedScrollView.gone()
        shimmer_view_container.show()

    }

    fun hideInitLoading() {
        Handler().postDelayed({
            nestedScrollView.show()
            shimmer_view_container.gone()
        }, 2000)


    }


    fun showLoading() {
        loading_bg.show()


    }

    fun hideLoading() {
        loading_bg.gone()


    }


}