package com.jmfs.locations.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object DataBindingAdapter {

    @BindingAdapter("url")
    @JvmStatic
    fun loadIcon(imageView: ImageView, idProfile: String?) {
            Glide.with(imageView)
                .load("https://graph.facebook.com/$idProfile/picture?type=small")
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
    }
}