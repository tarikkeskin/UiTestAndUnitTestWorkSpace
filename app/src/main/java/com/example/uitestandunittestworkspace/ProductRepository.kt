package com.example.uitestandunittestworkspace

data class Product(
    val id: Int,
    val name: String,
    val price: Double
)

/**
 * A real implementation of a Product Repository.
 * In a real app, this might fetch from a database or API,
 * but here it encapsulates logic that we want to test in integration.
 */
class ProductRepository {
    
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop", 1000.0),
            Product(2, "Mouse", 25.0),
            Product(3, "Keyboard", 75.0)
        )
    }

    /**
     * Logic that transforms data - good for integration testing.
     */
    fun calculateTotal(discountPercent: Int): Double {
        val total = getProducts().sumOf { it.price }
        return total * (1 - discountPercent / 100.0)
    }
}
