package com.vaskevicius.android.vikingrecipe.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.base.BaseActivity
import com.vaskevicius.android.vikingrecipe.data.models.Category
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.ui.browser.BrowseFragment
import com.vaskevicius.android.vikingrecipe.ui.views.customSearchView.CustomSearchView
import com.vaskevicius.android.vikingrecipe.ui.preview.PreviewFragment
import com.vaskevicius.android.vikingrecipe.ui.searchResult.SearchResultFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import javax.inject.Inject


class HomeActivity : BaseActivity(), HomeMVPView, HasSupportFragmentInjector {

    companion object {
        fun newInstance(): HomeActivity {
            return HomeActivity()
        }
    }

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    internal lateinit var homeAdapter: HomeAdapter

    @Inject
    internal lateinit var searchSuggestAdapter: SearchSuggestAdapter

    @Inject
    internal lateinit var layoutManager: LinearLayoutManager

    @Inject
    internal lateinit var presenter: HomeMVPPresenter<HomeMVPView, HomeMVPInteractor>

    var canShowSnackbar: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.onAttach(this)
        setupCategoryRecyclerView()
        setupSearch()
        setupAdapter()
        presenter.onViewPrepared()
    }

    override fun onFragmentAttached() {}
    override fun onFragmentDetached(tag: String) {}


    /*
    DISPLAY DATA FROM PRESENTER
     */
    override fun displayRandomRecipe(recipe: Recipe?): Unit? = recipe?.let {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        try_title.text = it.mealTitle
        Picasso.get().load(it.mealThumb)
            .resize(displayMetrics.widthPixels, displayMetrics.heightPixels / 2)
            .into(try_image)
        try_location.text = it.area
        getBitmapFromUrl(it.mealThumb)
        try_layout.setOnClickListener {
            openPreviewFragment(recipe)
        }
        canShowSnackbar = true
    }

    override fun displayCategoriesList(categories: List<Category>): Unit? = categories.let {
        homeAdapter.setItems(it)
        //adapter click listener
        homeAdapter.setOnItemClickListener(object : HomeAdapter.ItemClickListener {
            override fun onCategoryClick(category: Category) {
                openBrowseFragment(category)
            }
        })
        canShowSnackbar = true
    }


    /*
    OPEN FRAGMENTS
     */
    //open PreviewFragment from BrowseFragment, when clicked on recipe list item
    override fun openPreviewFragment(recipe: Recipe?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter,
            R.anim.exit,
            R.anim.pop_enter,
            R.anim.pop_exit
        ).add(R.id.container, PreviewFragment.newInstance(recipe!!), PreviewFragment.TAG)
            .addToBackStack(
                "backtrace"
            )
            .commit()
        val imm: InputMethodManager? =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm?.hideSoftInputFromWindow(container.windowToken, 0);


    }

    //open BrowseFragment for categories from PreviewFragment, when clicked on recipe tags
    override fun openBrowseFragment(category: String, methodId: Long) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter,
            R.anim.exit,
            R.anim.pop_enter,
            R.anim.pop_exit
        ).add(R.id.container, BrowseFragment.newInstance(category, methodId), BrowseFragment.TAG)
            .addToBackStack(
                "backtrace"
            )
            .commit()
    }

    //open BrowseFragment for categories from HomeActivity, when clicked on category list item
    private fun openBrowseFragment(category: Category) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter,
            R.anim.exit,
            R.anim.pop_enter,
            R.anim.pop_exit
        ).replace(R.id.container, BrowseFragment.newInstance(category), BrowseFragment.TAG)
            .addToBackStack(
                null
            )
            .commit()
    }

    private fun openSearchResultFragment(searchQuery: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter,
            R.anim.exit,
            R.anim.pop_enter,
            R.anim.pop_exit
        )
            .replace(
                R.id.container,
                SearchResultFragment.newInstance(searchQuery),
                SearchResultFragment.TAG
            ).addToBackStack(null)
            .commit()
    }


    /*
    LAYOUT SETUP
     */
    private fun setupAdapter() {
        category_recycler_view.layoutManager = LinearLayoutManager(this)
        category_recycler_view.adapter = homeAdapter
    }

    private fun setupSearch() {
        customSearch.setSuggestAdapter(searchSuggestAdapter)
        customSearch.setLayoutManager(LinearLayoutManager(this))
        KeyboardVisibilityEvent.setEventListener(
            this,
            object : KeyboardVisibilityEventListener {
                override fun onVisibilityChanged(isOpen: Boolean) {
                    if (!isOpen) customSearch.exitSearch()
                }
            })

        coordinator.setOnTouchListener { v, event ->
            customSearch.exitSearch()
            false
        }

        customSearch.setOnSearchListener(object : CustomSearchView.OnSearchListener {
            override fun onSearchClick(query: String?) {
                presenter.search(query!!, object : HomePresenter.OnFindSuggestListener {
                    override fun onSearchSuggestResults(recipes: List<Recipe>?) {
                        openSearchResultFragment(query)
                    }

                })
            }

            override fun onQueryChange(newQuery: String?) {
                if (newQuery!!.trim().isNotEmpty())
                    presenter.search(newQuery!!, object : HomePresenter.OnFindSuggestListener {
                        override fun onSearchSuggestResults(recipes: List<Recipe>?) {
                            searchSuggestAdapter.setItems(recipes!!)
                            searchSuggestAdapter.setOnItemClickListener(object :
                                SearchSuggestAdapter.ItemClickListener {
                                override fun onSuggestionClick(recipe: Recipe) {
                                    openPreviewFragment(recipe)
                                }

                            })
                        }

                    })
            }

        })

    }

    override fun showSuggestProgress(show: Boolean) {
        customSearch.showProgress(show)
    }

    override fun showSearchNoResults() {
        searchSuggestAdapter.setItems(mutableListOf())
        customSearch.showNoResults()
    }


    private fun setupCategoryRecyclerView() {
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        category_recycler_view.layoutManager = layoutManager
        category_recycler_view.itemAnimator = DefaultItemAnimator()
        category_recycler_view.adapter = homeAdapter
    }


    /*
    SETUP ADAPTIVE STATUS BAR COLOR
    */
    //checks if try_image is dark if so adjusts activity's and try_layout colors to it
    private fun setAdaptiveTextColor(bitmap: Bitmap) {
        Palette.from(bitmap).generate {
            var swatch = it?.vibrantSwatch
            if (swatch == null && it?.swatches?.size!! > 0) {
                swatch = it.swatches[0]
            }
            var titleTextColor: Int? = Color.WHITE
            var backgroundLayoutColor: Int? = Color.BLUE
            var backgroundColor: Int? = resources.getColor(R.color.material_gray_300)
            if (swatch != null) {
                backgroundColor = it?.getMutedColor(resources.getColor(R.color.material_gray_500))
                if (!isBitmapDark(bitmap)) {
                    backgroundLayoutColor =
                        it?.getMutedColor(resources.getColor(R.color.colorAccent))
                    backgroundLayoutColor =
                        ColorUtils.setAlphaComponent(backgroundLayoutColor!!, 135)
                    try_text_layout.setBackgroundColor(backgroundLayoutColor)
                } else try_text_layout.setBackgroundColor(0x00000000)
            }

            setStatusBarColor(backgroundColor)
            try_title.setTextColor(titleTextColor!!)
            try_static_text.setTextColor(titleTextColor)
            try_location.setTextColor(titleTextColor)
        }
    }

    //set status bar color to try_image's muted color
    private fun setStatusBarColor(backgroundColor: Int?) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = backgroundColor!!
    }

    private fun isBitmapDark(bitmap: Bitmap): Boolean {
        var dark = false
        val darkThreshold: Float = bitmap.width * bitmap.height * 0.45f
        var darkPixels = 0
        val pixels = IntArray(bitmap.width * bitmap.height)
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height);

        for (pixel in pixels) {
            val r = Color.red(pixel)
            val g = Color.green(pixel)
            val b = Color.blue(pixel)
            val luminance = 0.299 * r + 0.0f + 0.587 * g + 0.0f + 0.114 * b + 0.0f
            if (luminance < 140) {
                darkPixels++
            }
        }

        if (darkPixels >= darkThreshold) {
            dark = true;
        }
        return dark
    }

    //mealThumbnail to bitmap for color adaptation
    private fun getBitmapFromUrl(src: String?) {
        Picasso.get().load(src).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                setAdaptiveTextColor(bitmap!!)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }
        })
    }


    /*
    NETWORK STATUS/ERROR HANDLING
     */
    override fun handleNetworkError() {
        showNetworkErrorSnackbar()
    }

    override fun handleNetworkLoss() {
        showNetworkLossSnackbar()
    }

    override fun handleNetworkReconnection() {
        if (canShowSnackbar) {
            //notify's fragments about network status change
            val previewFragment: Fragment? = supportFragmentManager.findFragmentByTag(
                PreviewFragment.TAG
            )
            val browseFragment: Fragment? =
                supportFragmentManager.findFragmentByTag(BrowseFragment.TAG)
            if (previewFragment != null) (previewFragment as PreviewFragment).handleNetworkReconnection()
            if (browseFragment != null) (browseFragment as BrowseFragment).handleNetworkReconnection()

            presenter.refresh()
            showNetworkReconnectedSnackbar()
        }
    }


    /*
    DEPENDENCY INJECTION
    only if activity has fragments
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }


}
