package com.charleston.marvelapp.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun dpToPx(dp: Float): Int = (dp * Resources.getSystem().displayMetrics.density).toInt()

fun Context.divisorLastList(): DividerItemDecoration {
    return object : DividerItemDecoration(this, VERTICAL) {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position: Int = parent.getChildAdapterPosition(view)

            if (position == state.itemCount - 1) {
                outRect.bottom = dpToPx(70.0f).toInt()
            }
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {}

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {}
    }
}