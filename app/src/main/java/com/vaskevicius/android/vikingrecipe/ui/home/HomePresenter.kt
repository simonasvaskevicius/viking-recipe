package com.vaskevicius.android.vikingrecipe.ui.home

import com.vaskevicius.android.vikingrecipe.base.BasePresenter
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomePresenter<V : HomeMVPView, I : HomeMVPInteractor> @Inject constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), HomeMVPPresenter<V, I> {


    override fun onViewPrepared() {
        loadRandomPick()
        loadCategories()
    }

    override fun refresh() {
        onViewPrepared()
    }

    private fun loadRandomPick() {
        getView()?.showProgress()
        interactor?.let { it ->
            it.getRandomRecipe().compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ recipeResponse ->
                    getView()?.let {
                        it.hideProgress()
                        it.displayRandomRecipe(recipeResponse.data?.get(0))
                    }
                }, {
                    getView()?.handleNetworkError()
                })
        }
    }

    private fun loadCategories() {
        getView()?.showProgress()
        interactor?.let { it ->
            it.getCategoriesApiCall().compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ categoryResponse ->
                    getView()?.let {
                        it.hideProgress()
                        it.displayCategoriesList(categoryResponse.data!!)
                    }
                }, {
                    getView()?.handleNetworkError()
                })
        }
    }

    override fun search(keyword: String, listener: OnFindSuggestListener) {
        getView()?.showSuggestProgress(true)
        interactor?.let { it ->
            it.getSearchApiCall(keyword).compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ searchResponse ->
                    getView()?.showSuggestProgress(false)
                    listener.onSearchSuggestResults(searchResponse.data)
                }, {
                    getView()?.showSearchNoResults()
                })
        }
    }

    interface OnFindSuggestListener {
        fun onSearchSuggestResults(recipes: List<Recipe>?)
    }

}