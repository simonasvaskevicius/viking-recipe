package com.vaskevicius.android.vikingrecipe.ui.searchResult

import com.vaskevicius.android.vikingrecipe.base.BasePresenter
import com.vaskevicius.android.vikingrecipe.data.models.Recipe
import com.vaskevicius.android.vikingrecipe.ui.home.HomeMVPInteractor
import com.vaskevicius.android.vikingrecipe.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SearchResultPresenter<V : SearchResultMVPView, I : HomeMVPInteractor> @Inject constructor(
    interactor: I,
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable
) : BasePresenter<V, I>(
    interactor = interactor,
    schedulerProvider = schedulerProvider,
    compositeDisposable = compositeDisposable
), SearchResultMVPPresenter<V, I> {

    override fun search(keyword: String, listener: OnFindSuggestListener) {
        getView()?.showProgress()
        interactor?.let { it ->
            it.getSearchApiCall(keyword).compose(schedulerProvider.ioToMainObservableScheduler())
                .subscribe({ searchResponse ->
                    getView()?.hideProgress()
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