package com.vaskevicius.android.vikingrecipe.ui.browser

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.data.models.Recipe

class BrowseAdapter(private var items: MutableList<Recipe>) : RecyclerView.Adapter<BrowseAdapter.BrowseViewHolder>() {

    internal fun setItems(items: List<Recipe>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private var listener: ItemClickListener? = null

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrowseViewHolder {
        return BrowseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.li_browse_recipe, parent, false))
    }

    inner class BrowseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
    }

    override fun onBindViewHolder(holder: BrowseViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.mealTitle
        Picasso.get().load(item.mealThumb).into(holder.image)
        holder.itemView.setOnClickListener {
            listener?.onRecipeClick(item)
        }
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    interface ItemClickListener {
        fun onRecipeClick(recipe: Recipe)
    }
}