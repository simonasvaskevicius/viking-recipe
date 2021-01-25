package com.vaskevicius.android.vikingrecipe.ui.browser

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.base.BaseFragment
import com.vaskevicius.android.vikingrecipe.data.models.Category
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_browser.*
import kotlinx.android.synthetic.main.fragment_browser.back
import kotlinx.android.synthetic.main.fragment_browser.colapsing_toolbar_layout
import kotlinx.android.synthetic.main.fragment_preview.*
import javax.inject.Inject

class BrowseFragment(private val category: Category) : BaseFragment(), BrowseMVPView {

    companion object {
        const val TAG = "fragment.browseFragment"
        const val ID_AREA_STRING: Long = -10
        const val ID_CATEGORY_STRING: Long = -11

        fun newInstance(category: Category): BrowseFragment {
            return BrowseFragment(category)
        }

        //if called from previewFragment
        //methodId defines if its area or category string
        fun newInstance(categoryTitle: String, methodId: Long): BrowseFragment {
            return BrowseFragment(Category(methodId, categoryTitle, null, null))
        }
    }

    @Inject
    internal lateinit var browseAdapter: BrowseAdapter

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager

    @Inject
    internal lateinit var presenter: BrowseMVPPresenter<BrowseMVPView, BrowseMVPInteractor>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browser, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity!!.supportFragmentManager.beginTransaction().setCustomAnimations(
                        R.anim.enter,
                        R.anim.exit,
                        R.anim.pop_enter,
                        R.anim.pop_exit
                ).remove(this@BrowseFragment).commit()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setUp() {
        colapsing_toolbar_layout.background = getGradientColor(requireActivity().window.statusBarColor)
        val linearLayoutManager = LinearLayoutManager(context)
        val picasso: Picasso = Picasso.get()
        recipe_recycler_view.layoutManager = linearLayoutManager
        category_title.text = category.title
        if (category.categoryThumb != null) picasso.load(category.categoryThumb).into(category_image)
        else category_image.setImageResource(R.drawable.default_photo)
        setupBackButton()
        recipe_recycler_view.adapter = browseAdapter
        presenter.onViewPrepared(category.title!!, category.id!!)
    }

    private fun setupBackButton() {
        back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getGradientColor(color: Int?): GradientDrawable {
        val colors = intArrayOf(color!!, Color.parseColor("#ffffff"))
        val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        gd.cornerRadius = 0f
        return gd
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    override fun displayRecipeList(recipes: List<Recipe>): Unit? = recipes.let {
        browseAdapter.setItems(it)
        browseAdapter.setOnItemClickListener(object : BrowseAdapter.ItemClickListener {
            override fun onRecipeClick(recipe: Recipe) {
                presenter.getRecipeById(recipe.id!!)
            }
        })
    }

    override fun onRecipeByIdLoaded(recipe: Recipe?): Unit? = recipe.let {
        if (it != null) {
            (activity as HomeActivity).openPreviewFragment(it)
        }
    }

    override fun handleNetworkError() {
    }

    override fun handleNetworkLoss() {
    }

    override fun handleNetworkReconnection() {
        presenter.refresh()
    }

}