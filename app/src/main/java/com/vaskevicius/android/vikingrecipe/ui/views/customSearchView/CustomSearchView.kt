package com.vaskevicius.android.vikingrecipe.ui.views.customSearchView

import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vaskevicius.android.vikingrecipe.R
import com.vaskevicius.android.vikingrecipe.ui.home.SearchSuggestAdapter
import kotlinx.android.synthetic.main.layout_search.view.*


class CustomSearchView : LinearLayout {

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    var listener: OnSearchListener? = null

    private fun init(context: Context?) {

        inflate(context, R.layout.layout_search, this)

        searchEditText.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_SEARCH)) || (actionId == EditorInfo.IME_ACTION_SEARCH)) {
                if (searchEditText.text.toString().trim().isNotEmpty()) listener!!.onSearchClick(
                    searchEditText.text.toString()
                )

            }
            false
        }

        back.setOnClickListener {
            exitSearch()
        }

        searchEditText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                back.visibility = VISIBLE
                searchEditText.setTextIsSelectable(true);
            } else exitSearch()
        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Handler().postDelayed({
                    if (searchEditText.text!!.isEmpty()) suggestion_recycler_view.visibility =
                        View.GONE
                    else if (suggestion_recycler_view.visibility == View.GONE) suggestion_recycler_view.visibility =
                        View.VISIBLE
                    noResults.visibility = View.GONE
                    listener?.onQueryChange(searchEditText.text.toString())
                }, 500)
            }

        })

    }

    fun exitSearch() {
        suggestion_recycler_view.visibility = View.GONE
        searchEditText.clearFocus()
        hideKeyboard()
        searchEditText.text = Editable.Factory.getInstance().newEditable("")
        searchInputLayout.hint = "Split Pea Soup..."
        back.visibility = View.GONE
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager? =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    fun setLayoutManager(linearLayoutManager: LinearLayoutManager) {
        suggestion_recycler_view.layoutManager = linearLayoutManager
    }

    fun setSuggestAdapter(adapter: RecyclerView.Adapter<SearchSuggestAdapter.SearchSuggestViewHolder>) {
        suggestion_recycler_view.adapter = adapter
        suggestion_recycler_view.visibility = View.VISIBLE
    }

    fun showNoResults() {
        Handler().postDelayed({
            noResults.visibility = View.VISIBLE
            noResults.text = "No results for '${searchEditText.text}'"
        }, 500)
    }

    fun showProgress(show: Boolean) {
        var progressBar: ProgressBar = pb_suggestion_loading
        progressBar.isIndeterminate = true
        if (show) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    fun setOnSearchListener(listener: OnSearchListener) {
        this.listener = listener
    }

    interface OnSearchListener {
        fun onSearchClick(query: String?)

        fun onQueryChange(newQuery: String?)
    }

}