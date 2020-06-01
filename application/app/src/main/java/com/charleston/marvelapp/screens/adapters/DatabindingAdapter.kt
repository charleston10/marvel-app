package com.charleston.marvelapp.screens.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.charleston.marvelapp.R
import java.util.*

class DataBindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter(value = ["bindImageUrl"], requireAll = false)
        fun bindImageUrl(view: ImageView, url: String?) {
            url?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .apply(
                        RequestOptions()
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .override(400, 400)
                            .fitCenter()
                    )
                    .into(view)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["bindShowing"])
        fun bindShowing(view: TextView, total: Int?) {
            total?.let {
                val text = view.context.getString(R.string.label_showing)
                view.text = String.format(text, it)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextResult"])
        fun bindTextResult(view: TextView, themeName: String?) {
            val text = view.context.getString(R.string.label_list_theme_selected)
            view.text = String.format(text, themeName?.toLowerCase(Locale.getDefault()))
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextLoading"])
        fun bindTextLoading(view: TextView, themeName: String?) {
            val text = view.context.getString(R.string.label_list_loading)
            view.text = String.format(text, themeName?.toLowerCase(Locale.getDefault()))
        }
    }
}