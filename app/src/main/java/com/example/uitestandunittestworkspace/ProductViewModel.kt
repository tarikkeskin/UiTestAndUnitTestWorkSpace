package com.example.uitestandunittestworkspace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ProductUiState(
    val products: List<Product> = emptyList(),
    val totalPrice: Double = 0.0
)

/**
 * ViewModel that depends on ProductRepository.
 * We will test this together with the real repository in our integration test.
 */
class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    init {
        refreshData(discount = 0)
    }

    fun refreshData(discount: Int) {
        val products = repository.getProducts()
        val total = repository.calculateTotal(discount)
        
        _uiState.value = ProductUiState(
            products = products,
            totalPrice = total
        )
    }
}
