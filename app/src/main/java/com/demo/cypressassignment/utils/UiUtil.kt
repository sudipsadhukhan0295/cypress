package com.demo.cypressassignment.utils

import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.demo.cypressassignment.model.PhotoDetail

object UiUtil {

    @BindingAdapter("loadUrlImage")
    @JvmStatic
    fun loadUrlImage(view: ImageView, imageUrl: String?) {
        if (imageUrl != null) {
            view.load(imageUrl)
        }
    }

}