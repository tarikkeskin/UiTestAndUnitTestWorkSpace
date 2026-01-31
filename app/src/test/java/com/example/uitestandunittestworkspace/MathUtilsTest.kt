package com.example.uitestandunittestworkspace

import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for [MathUtils].
 * These tests run on the JVM and are very fast.
 */
class MathUtilsTest {

    @Test
    fun add_isCorrect() {
        val result = MathUtils.add(2, 3)
        assertEquals("2 + 3 should be 5", 5, result)
    }

    @Test
    fun multiply_isCorrect() {
        val result = MathUtils.multiply(4, 5)
        assertEquals("4 * 5 should be 20", 20, result)
    }

    @Test
    fun isEven_withEvenNumber_returnsTrue() {
        assertTrue("4 should be even", MathUtils.isEven(4))
    }

    @Test
    fun isEven_withOddNumber_returnsFalse() {
        assertFalse("5 should not be even", MathUtils.isEven(5))
    }
}
