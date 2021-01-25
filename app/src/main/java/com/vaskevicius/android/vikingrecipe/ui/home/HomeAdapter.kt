package com.vaskevicius.android.vikingrecipe.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.data.models.Category


class HomeAdapter(private val items: MutableList<Category>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    internal fun setItems(items: List<Category>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.li_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = items?.get(position)
        holder.title.text = item!!.title
        Picasso.get().load(item.categoryThumb).into(holder.image)
        holder.itemView.setOnClickListener {
            listener?.onCategoryClick(item)
        }
    }

    override fun getItemCount(): Int = items!!.size

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var title: TextView = itemView.findViewById(R.id.title)
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }

    interface ItemClickListener {
        fun onCategoryClick(category: Category)
    }
}