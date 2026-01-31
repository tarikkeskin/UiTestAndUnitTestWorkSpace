package com.example.uitestandunittestworkspace

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for [MainViewModel].
 * Demonstrates testing state within a ViewModel.
 */
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun initialState_isZero() {
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun increment_increasesCount() {
        viewModel.increment()
        assertEquals(1, viewModel.count.value)
    }

    @Test
    fun decrement_decreasesCount() {
        viewModel.increment() // 1
        viewModel.decrement() // 0
        assertEquals(0, viewModel.count.value)
    }

    @Test
    fun decrement_doesNotGoBelowZero() {
        viewModel.decrement()
        assertEquals(0, viewModel.count.value)
    }
}
