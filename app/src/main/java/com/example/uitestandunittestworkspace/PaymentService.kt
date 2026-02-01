package com.example.uitestandunittestworkspace

/**
 * Interface representing an Analytics system.
 * We will mock this to verify calls.
 */
interface Analytics {
    fun trackEvent(name: String, properties: Map<String, Any>)
}

/**
 * Simple processor that handles payments and logs events.
 * Demonstrates a scenario where we want to test side-effects (analytics).
 */
class PaymentProcessor(private val analytics: Analytics) {

    fun processPayment(amount: Double, currency: String): Boolean {
        if (amount <= 0) {
            analytics.trackEvent("payment_failed", mapOf("reason" to "invalid_amount"))
            return false
        }

        // Logic for successful payment...
        
        analytics.trackEvent("payment_success", mapOf(
            "amount" to amount,
            "currency" to currency
        ))
        
        return true
    }
}
