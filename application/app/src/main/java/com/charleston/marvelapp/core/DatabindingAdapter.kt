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
            view.text = "Showing $total item(s) result"
        }

        @JvmStatic
        @BindingAdapter(value = ["bindTextWatcher"])
        fun bindTextWatcher(view: EditText, textWatcher: TextWatcher) {
            view.addTextChangedListener(textWatcher)
        }
    }
}