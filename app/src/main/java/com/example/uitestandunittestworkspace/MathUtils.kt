package com.example.uitestandunittestworkspace

/**
 * A simple utility class to demonstrate pure function unit testing.
 * These functions have no dependencies on Android framework.
 */
object MathUtils {

    /**
     * Adds two numbers.
     */
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    /**
     * Multiplies two numbers.
     */
    fun multiply(a: Int, b: Int): Int {
        return a * b
    }

    /**
     * Checks if a number is even.
     */
    fun isEven(n: Int): Boolean {
        return n % 2 == 0
    }
}
