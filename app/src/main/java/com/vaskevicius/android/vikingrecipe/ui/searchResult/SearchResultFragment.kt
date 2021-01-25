package com.vaskevicius.android.vikingrecipe.ui.searchResult

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.base.BaseFragment
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.ui.browser.BrowseAdapter
import com.vaskevicius.android.vikingrecipe.ui.home.HomeActivity
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPInteractor
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultFragment(
    private val searchQuery: String
) : BaseFragment(), SearchResultMVPView {

    companion object {
        const val TAG = "fragment.searchResultFragment"
        fun newInstance(searchQuery: String): SearchResultFragment =
            SearchResultFragment(searchQuery)
    }

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager

    @Inject
    internal lateinit var adapter: BrowseAdapter

    @Inject
    internal lateinit var presenter: SearchResultPresenter<SearchResultMVPView, HomeMVPInteractor>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search_results, container, false)

    override fun setUp() {
        searchQueryTitle.text = "Results for '$searchQuery'"
        searchImage.setImageResource(R.drawable.default_category_image)
        collapsing_toolbar_layout.background =
            getGradientColor(requireActivity().window.statusBarColor)

        setupAdapter()
        setupBackButton()

        presenter.search(searchQuery, object : SearchResultPresenter.OnFindSuggestListener {
            override fun onSearchSuggestResults(recipes: List<Recipe>?) {
                adapter.setItems(recipes!!)
            }
        })
    }

    override fun showSearchNoResults() {
        noResults.visibility = View.VISIBLE
        searchResultRecyclerView.visibility = View.GONE
    }

    private fun setupAdapter() {
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        searchResultRecyclerView.layoutManager = layoutManager
        adapter.setOnItemClickListener(object : BrowseAdapter.ItemClickListener {
            override fun onRecipeClick(recipe: Recipe) {
                (activity as HomeActivity).openPreviewFragment(recipe)
            }
        })

        searchResultRecyclerView.adapter = adapter
    }

    private fun getGradientColor(color: Int?): GradientDrawable {
        val colors = intArrayOf(color!!, Color.parseColor("#ffffff"))
        val gd = GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
        gd.cornerRadius = 0f
        return gd
    }

    private fun setupBackButton() {
        back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun handleNetworkError() {
    }

    override fun handleNetworkLoss() {
    }

    override fun handleNetworkReconnection() {
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }
}