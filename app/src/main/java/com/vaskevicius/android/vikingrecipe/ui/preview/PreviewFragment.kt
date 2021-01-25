package com.vaskevicius.android.vikingrecipe.ui.preview

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.base.BaseFragment
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.ui.browser.BrowseFragment
import com.vaskevicius.android.vikingrecipe.ui.home.HomeActivity
import kotlinx.android.synthetic.main.fragment_preview.*
import javax.inject.Inject


class PreviewFragment(private val recipe: Recipe) : BaseFragment(), PreviewMVPView {
    companion object {
        const val TAG = "fragment.previewFragment"
        fun newInstance(recipe: Recipe): PreviewFragment = PreviewFragment(recipe)
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager

    private var ingredientList = mutableListOf<String>()
    private var measuresList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_preview, container, false)

    override fun setUp() {
        Picasso.get().load(recipe.mealThumb).into(image)
        meal_title.text = recipe.mealTitle
        instructions.text = recipe.instructions
        location.text = recipe.area
        category.text = recipe.category

        setupCategoryClick()
        setupTags()
        setupBackButton()
        setupSources()
        mapIngredients()
        setupIngredientsList()
    }

    private fun setupTags() {
        if (recipe.tags != null && recipe.tags!!.isNotEmpty()) {
            //adds space after ','
            val recipeTags: String = recipe.tags!!.replace(",".toRegex(), "$0 ")
            tags.text = recipeTags
        } else {
            tag_icon.visibility = View.GONE
            tags.visibility = View.GONE
        }
    }

    private fun setupCategoryClick() {
        category.setOnClickListener {
            (activity as HomeActivity).openBrowseFragment(
                recipe.category!!,
                BrowseFragment.ID_CATEGORY_STRING
            )
        }
        location.setOnClickListener {
            (activity as HomeActivity).openBrowseFragment(
                recipe.area!!,
                BrowseFragment.ID_AREA_STRING
            )
        }
    }


    private fun setupIngredientsList() {
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        ingredients.layoutManager = layoutManager
        ingredients.itemAnimator = DefaultItemAnimator()
        ingredients.adapter = IngredientsAdapter(ingredientList!!, measuresList!!)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
    }

    //toolbar back button setup
    private fun setupBackButton() {
        back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupSources() {

        //opens recipe in browser
        source.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.sourceLink))
            startActivity(browserIntent)
        }

        //if device has Youtube's app installed -> opens it
        //if not opens link in browser
        youtube.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(recipe.youtubeLink)
            )
            intent.component = ComponentName(
                "com.google.android.youtube",
                "com.google.android.youtube.PlayerActivity"
            )
            val manager = context!!.packageManager
            val infos = manager.queryIntentActivities(intent, 0)
            if (infos.size > 0) {
                context!!.startActivity(intent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.youtubeLink))
                startActivity(browserIntent)
            }
        }
    }

    //maps ingredients and measures variables into two separate collections
    private fun mapIngredients() {
        if (recipe.strIngredient1 != null) ingredientList?.add(recipe.strIngredient1!!)
        if (recipe.strIngredient2 != null) ingredientList?.add(recipe.strIngredient2!!)
        if (recipe.strIngredient3 != null) ingredientList?.add(recipe.strIngredient3!!)
        if (recipe.strIngredient4 != null) ingredientList?.add(recipe.strIngredient4!!)
        if (recipe.strIngredient5 != null) ingredientList?.add(recipe.strIngredient5!!)
        if (recipe.strIngredient6 != null) ingredientList?.add(recipe.strIngredient6!!)
        if (recipe.strIngredient7 != null) ingredientList?.add(recipe.strIngredient7!!)
        if (recipe.strIngredient8 != null) ingredientList?.add(recipe.strIngredient8!!)
        if (recipe.strIngredient9 != null) ingredientList?.add(recipe.strIngredient9!!)
        if (recipe.strIngredient10 != null) ingredientList?.add(recipe.strIngredient10!!)
        if (recipe.strIngredient11 != null) ingredientList?.add(recipe.strIngredient11!!)
        if (recipe.strIngredient12 != null) ingredientList?.add(recipe.strIngredient12!!)
        if (recipe.strIngredient13 != null) ingredientList?.add(recipe.strIngredient13!!)
        if (recipe.strIngredient14 != null) ingredientList?.add(recipe.strIngredient14!!)
        if (recipe.strIngredient15 != null) ingredientList?.add(recipe.strIngredient15!!)
        if (recipe.strIngredient16 != null) ingredientList?.add(recipe.strIngredient16!!)
        if (recipe.strIngredient17 != null) ingredientList?.add(recipe.strIngredient17!!)
        if (recipe.strIngredient18 != null) ingredientList?.add(recipe.strIngredient18!!)
        if (recipe.strIngredient19 != null) ingredientList?.add(recipe.strIngredient19!!)
        if (recipe.strIngredient20 != null) ingredientList?.add(recipe.strIngredient20!!)

        if (recipe.strMeasure1 != null) measuresList?.add(recipe.strMeasure1!!)
        if (recipe.strMeasure2 != null) measuresList?.add(recipe.strMeasure2!!)
        if (recipe.strMeasure3 != null) measuresList?.add(recipe.strMeasure3!!)
        if (recipe.strMeasure4 != null) measuresList?.add(recipe.strMeasure4!!)
        if (recipe.strMeasure5 != null) measuresList?.add(recipe.strMeasure5!!)
        if (recipe.strMeasure6 != null) measuresList?.add(recipe.strMeasure6!!)
        if (recipe.strMeasure7 != null) measuresList?.add(recipe.strMeasure7!!)
        if (recipe.strMeasure8 != null) measuresList?.add(recipe.strMeasure8!!)
        if (recipe.strMeasure9 != null) measuresList?.add(recipe.strMeasure9!!)
        if (recipe.strMeasure10 != null) measuresList?.add(recipe.strMeasure10!!)
        if (recipe.strMeasure11 != null) measuresList?.add(recipe.strMeasure11!!)
        if (recipe.strMeasure12 != null) measuresList?.add(recipe.strMeasure12!!)
        if (recipe.strMeasure13 != null) measuresList?.add(recipe.strMeasure13!!)
        if (recipe.strMeasure14 != null) measuresList?.add(recipe.strMeasure14!!)
        if (recipe.strMeasure15 != null) measuresList?.add(recipe.strMeasure15!!)
        if (recipe.strMeasure16 != null) measuresList?.add(recipe.strMeasure16!!)
        if (recipe.strMeasure17 != null) measuresList?.add(recipe.strMeasure17!!)
        if (recipe.strMeasure18 != null) measuresList?.add(recipe.strMeasure18!!)
        if (recipe.strMeasure19 != null) measuresList?.add(recipe.strMeasure19!!)
        if (recipe.strMeasure20 != null) measuresList?.add(recipe.strMeasure20!!)

        //removes empty variables from collections
        val ingredientIterator = ingredientList.iterator()
        while (ingredientIterator.hasNext()) {
            if (ingredientIterator.next().isEmpty()) {
                ingredientIterator.remove()
            }
        }

        //removes empty variables from collections
        val measureIterator = ingredientList.iterator()
        while (measureIterator.hasNext()) {
            if (measureIterator.next().isEmpty()) {
                measureIterator.remove()
            }
        }

    }

    override fun handleNetworkError() {
    }

    override fun handleNetworkLoss() {
    }

    override fun handleNetworkReconnection() {
    }
}