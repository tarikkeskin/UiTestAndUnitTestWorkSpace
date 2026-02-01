package com.example.uitestandunittestworkspace

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

/**
 * Demonstrates advanced MockK features: verification, argument capture, and strict checking.
 */
class PaymentProcessorTest {

    private val analytics: Analytics = mockk(relaxed = true)
    private val processor = PaymentProcessor(analytics)

    @Test
    fun processPayment_success_verifiesAnalyticsCall() {
        // When
        val result = processor.processPayment(100.0, "USD")

        // Then
        assertTrue(result)
        
        // Verifying that trackEvent was called with specific parameters
        verify(exactly = 1) { 
            analytics.trackEvent("payment_success", any()) 
        }
    }

    @Test
    fun processPayment_invalidAmount_capturesArgument() {
        // Given
        val eventNameSlot = slot<String>()
        val propertiesSlot = slot<Map<String, Any>>()

        // When
        val result = processor.processPayment(-10.0, "USD")

        // Then
        assertFalse(result)
        
        // Capturing the arguments sent to the mock
        verify { analytics.trackEvent(capture(eventNameSlot), capture(propertiesSlot)) }
        
        assertEquals("payment_failed", eventNameSlot.captured)
        assertEquals("invalid_amount", propertiesSlot.captured["reason"])
    }

    @Test
    fun processPayment_success_ensuresNoOtherCalls() {
        // When
        processor.processPayment(50.0, "EUR")

        // Then
        verify { analytics.trackEvent(any(), any()) }
        
        // Ensures that no other methods on the mock were called
        confirmVerified(analytics)
    }
}
