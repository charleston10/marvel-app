package com.charleston.marvelapp.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.ItemModel
import com.charleston.marvelapp.R
import com.charleston.marvelapp.databinding.ItemListBinding

class ListAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private val items: ArrayList<ItemModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_list,
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

    fun loadItems(items: List<ItemModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val viewDataBinding: ItemListBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(data: ItemModel) {
            viewDataBinding.run {
                model = data
                root.setOnClickListener { listener.onClickListener(data) }
                executePendingBindings()
            }
        }
    }

    interface Listener {
        fun onClickListener(model: ItemModel)
    }
}