package com.example.uitestandunittestworkspace

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel demonstrating state persistence using [SavedStateHandle].
 * This allows the state to survive system-initiated process death.
 */
class SearchViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_QUERY = "search_query"
    }

    /**
     * Expose the query as a StateFlow. 
     * [getStateFlow] automatically manages reading and writing to the handle.
     */
    val query: StateFlow<String> = savedStateHandle.getStateFlow(KEY_QUERY, "")

    /**
     * Update the query. This automatically updates [query] and saves it in [SavedStateHandle].
     */
    fun onQueryChanged(newQuery: String) {
        savedStateHandle[KEY_QUERY] = newQuery
    }
}
