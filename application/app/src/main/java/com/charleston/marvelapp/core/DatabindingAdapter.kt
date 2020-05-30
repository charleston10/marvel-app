package com.charleston.marvelapp.core

import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

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
                view.text = "Showing $it item(s)"
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextWatcher"])
        fun bindTextWatcher(view: EditText, textWatcher: TextWatcher?) {
            textWatcher?.let {
                view.addTextChangedListener(it)
            }
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextResult"])
        fun bindTextResult(view: TextView, themeName: String?) {
            view.text = "List of items theme ${themeName?.toLowerCase()}"
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextLoading"])
        fun bindTextLoading(view: TextView, themeName: String?) {
            view.text = "Loading ${themeName?.toLowerCase()}"
        }
    }
}