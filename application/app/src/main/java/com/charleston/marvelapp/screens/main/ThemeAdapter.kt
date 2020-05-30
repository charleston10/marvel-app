package com.charleston.marvelapp.screens.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ThemeModel
import com.charleston.marvelapp.R
import com.charleston.marvelapp.databinding.ItemThemeBinding

class ThemeAdapter(
    private val  listener: Listener
) : RecyclerView.Adapter<ThemeAdapter.ViewHolder>() {

    private val items: ArrayList<ThemeModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_theme,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun loadItems(items: List<ThemeModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val viewDataBinding: ItemThemeBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(data: ThemeModel) {
            viewDataBinding.run {
                model = data
                root.setOnClickListener { listener.onClickListener(data) }
                executePendingBindings()
            }
        }
    }

    interface Listener {
        fun onClickListener(themeModel: ThemeModel)
    }
}