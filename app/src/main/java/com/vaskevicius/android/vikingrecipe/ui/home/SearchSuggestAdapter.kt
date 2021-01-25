package com.vaskevicius.android.vikingrecipe.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.data.models.Recipe

class SearchSuggestAdapter(private var items: MutableList<Recipe>) :
    RecyclerView.Adapter<SearchSuggestAdapter.SearchSuggestViewHolder>() {

    internal fun setItems(items: List<Recipe>) {
        clearItems()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun clearItems() {
        this.items.clear()
    }

    private var listener: ItemClickListener? = null

    private val limit: Int = 3

    override fun getItemCount(): Int = if (items.size > limit) limit
    else items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchSuggestViewHolder {
        return SearchSuggestViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.li_search_suggest, parent, false)
        )
    }

    inner class SearchSuggestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onBindViewHolder(holder: SearchSuggestViewHolder, position: Int) {
        if (items.isNotEmpty()) {
            val item = items[position]
            holder.title.text = item.mealTitle
            holder.itemView.setOnClickListener {
                listener?.onSuggestionClick(item)
            }
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    interface ItemClickListener {
        fun onSuggestionClick(recipe: Recipe)
    }
}