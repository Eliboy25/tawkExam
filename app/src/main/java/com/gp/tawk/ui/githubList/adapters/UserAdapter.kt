package com.gp.tawk.ui.githubList.adapters

import android.graphics.*
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gp.tawk.R
import com.gp.tawk.core.extensions.inflate
import com.gp.tawk.core.extensions.setDebounceClickListener
import com.gp.tawk.core.room.entities.GitUserEntity
import com.gp.tawk.core.utils.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.view_user_list.view.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL


class UserAdapter(
    override var items: MutableList<GitUserEntity>,
    private val onItemClick: (GitUserEntity) -> Unit
) :
    BaseRecyclerViewAdapter<GitUserEntity>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_user_list))
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

//    override fun getlastId(pos: Int): GitUserEntity {
//        return super.getlastId(pos)
//    }

    override fun onBindViewHolder(view: View, item: GitUserEntity, position: Int) {
        with(view) {
            Log.e("username", item.login + " Email : " + item.email + "  Notes :  " + item.notes)
            tvUserName.text = item.login
            tvDetails.text = item.html_url
            if (item.notes.isNullOrEmpty()){
                ivNote.visibility = View.GONE
            }else{
                ivNote.visibility = View.VISIBLE
            }


            val isDivisibleBy20 = (position + 1) % 4 === 0
            if (isDivisibleBy20) {
                var bitmap : Bitmap? = null
                val urlImage = URL(item.avatar_url)

                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }
                GlobalScope.launch(Dispatchers.Main) {
                    // get the downloaded bitmap
                    bitmap  = result.await()
                    bitmap?.apply {
                        // set inverted colors bitmap to second image view
                        invertColors()?.apply {
                            Glide.with(context!!)
                                .load(this)
                                .placeholder(R.drawable.ic_person)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(profile_image)
                        }
                    }
                }


            }else{
                Glide.with(context!!).load(item.avatar_url).into(profile_image)
            }


            setDebounceClickListener {

                onItemClick(item)
            }
        }
    }

    fun setNewItems(recipients: MutableList<GitUserEntity>) {
        this.items.clear()
        this.items.addAll(recipients)
        notifyDataSetChanged()
    }

    fun getItem(pos : Int) : GitUserEntity{
        return this.items[pos]
    }






    // extension function to get / download bitmap from url
    fun URL.toBitmap(): Bitmap?{
        return try {
            BitmapFactory.decodeStream(openStream())
        }catch (e:IOException){
            null
        }
    }


    // extension function to invert bitmap colors
    fun Bitmap.invertColors(): Bitmap? {
        val bitmap = Bitmap.createBitmap(
            width,
            height,
            Bitmap.Config.ARGB_8888
        )

        val matrixInvert = ColorMatrix().apply {
            set(
                floatArrayOf(
                    -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
                    0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
                    0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
                    0.0f, 0.0f, 0.0f, 1.0f, 0.0f
                )
            )
        }

        val paint = Paint()
        ColorMatrixColorFilter(matrixInvert).apply {
            paint.colorFilter = this
        }

        Canvas(bitmap).drawBitmap(this, 0f, 0f, paint)
        return bitmap
    }


}




