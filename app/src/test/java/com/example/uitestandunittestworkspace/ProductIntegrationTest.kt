package com.example.uitestandunittestworkspace

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Integration Test example.
 * Note: We are using REAL implementations of both ViewModel and Repository.
 * No mocks are used here.
 */
class ProductIntegrationTest {

    @Test
    fun productFlow_calculatesCorrectTotal_whenDiscountApplied() {
        // Given - Real repository and real ViewModel
        val repository = ProductRepository()
        val viewModel = ProductViewModel(repository)

        // When - We apply a 10% discount through the ViewModel
        viewModel.refreshData(discount = 10)

        // Then - We verify the interaction between layers
        // Total price should be (1000 + 25 + 75) * 0.9 = 990.0
        val state = viewModel.uiState.value
        assertEquals(3, state.products.size)
        assertEquals(990.0, state.totalPrice, 0.001)
    }

    @Test
    fun productFlow_showsInitialData_correctly() {
        val repository = ProductRepository()
        val viewModel = ProductViewModel(repository)

        val state = viewModel.uiState.value
        assertEquals(3, state.products.size)
        assertEquals(1100.0, state.totalPrice, 0.001)
    }
}
