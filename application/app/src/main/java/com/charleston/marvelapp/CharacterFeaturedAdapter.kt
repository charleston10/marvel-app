package com.charleston.marvelapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charleston.domain.model.CharacterModel
import com.charleston.marvelapp.databinding.ItemCharacterFeaturedBinding

class CharacterFeaturedAdapter : RecyclerView.Adapter<CharacterFeaturedAdapter.ViewHolder>() {

    private val items: ArrayList<CharacterModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_character_featured,
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

    fun loadItems(items: List<CharacterModel>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val viewDataBinding: ItemCharacterFeaturedBinding
    ) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(data: CharacterModel) {
            viewDataBinding.run {
                model = data
                executePendingBindings()
            }
        }
    }
}