package com.vaskevicius.android.vikingrecipe.ui.preview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vaskevicius.android.vikingrecipe.R

class IngredientsAdapter(
    private val ingredients: MutableList<String>,
    private val measures: MutableList<String>
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        return IngredientsViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.li_ingredients, parent, false)
        )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        if (ingredients[position] != null && measures[position] != null) {
            val ingredient = ingredients[position]
            val measure = measures[position]
            if (ingredient.isNotEmpty() && measure.isNotEmpty()) {
                holder.ingredient.text = ingredient
                holder.measure.text = measure
            }
        }
    }

    inner class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ingredient: TextView = itemView.findViewById(R.id.ingredient)
        var measure: TextView = itemView.findViewById(R.id.measure)
    }

    override fun getItemCount(): Int = ingredients.size
}