package com.gp.tawk.ui.profile.views

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gp.tawk.R
import com.gp.tawk.core.extensions.gone
import com.gp.tawk.core.extensions.setDebounceClickListener
import com.gp.tawk.core.extensions.show
import com.gp.tawk.core.room.entities.GitUserEntity
import kotlinx.android.synthetic.main.layout_view_toolbar.view.*
import kotlinx.android.synthetic.main.view_profile.view.*
import kotlinx.android.synthetic.main.view_profile.view.profile_image
import kotlinx.android.synthetic.main.view_user_list.view.*

interface ProfileDelegate {
    fun onBackPressed()
    fun updateInfo(id: Int,note:String)
}

class ProfileView(context: Context) : FrameLayout(context) {



    var delegate: ProfileDelegate? = null
    var userEntity: GitUserEntity? = null

    init {
        inflate(context, R.layout.view_profile, this)
        back.visibility = View.VISIBLE
        searchView.visibility = View.GONE
        toolbarTitle.visibility = View.VISIBLE



        back.setDebounceClickListener {
            delegate?.onBackPressed()
        }

        btnSave.setDebounceClickListener {
            delegate?.updateInfo(
                this.userEntity!!.id,etNote.text.toString()
            )
        }

    }

    fun setDetails(userEntity: GitUserEntity){
        this.userEntity = userEntity
        toolbarTitle.text = userEntity.login
        tvName.text = userEntity.name
        tvCompany.text = userEntity.company
        tvBlog.text = userEntity.blog
        tvFollowers.text = userEntity.followers.toString()
        tvFollowing.text = userEntity.following.toString()


        Glide.with(context!!).load(userEntity.avatar_url)
            .fitCenter()
            .into(profile_image)
        hideLoading()


    }

    fun showLoading() {
        loading_bg.show()
    }

    fun hideLoading() {
        loading_bg.gone()
    }


}