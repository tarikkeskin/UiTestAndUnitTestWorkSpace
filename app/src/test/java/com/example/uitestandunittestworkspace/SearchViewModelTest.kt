package com.example.uitestandunittestworkspace

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @Test
    fun initialState_shouldBeEmpty() = runTest {
        // Given - empty handle
        val handle = SavedStateHandle()
        val viewModel = SearchViewModel(handle)

        // Then
        assertEquals("", viewModel.query.value)
    }

    @Test
    fun onQueryChanged_shouldUpdateHandle() = runTest {
        // Given
        val handle = SavedStateHandle()
        val viewModel = SearchViewModel(handle)

        // When
        viewModel.onQueryChanged("Android")

        // Then
        assertEquals("Android", viewModel.query.value)
        assertEquals("Android", handle.get<String>("search_query"))
    }

    @Test
    fun restoredState_shouldInitializeCorrectly() = runTest {
        // Given - handle with existing data (simulating state restoration)
        val handle = SavedStateHandle(mapOf("search_query" to "Kotlin"))
        val viewModel = SearchViewModel(handle)

        // Then
        assertEquals("Kotlin", viewModel.query.value)
    }
}
