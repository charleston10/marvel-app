package com.charleston.marvelapp.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.charleston.marvelapp.R

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

fun RecyclerView.animateFallDown() {
    layoutAnimation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down)
    adapter?.notifyDataSetChanged()
    scheduleLayoutAnimation()
}