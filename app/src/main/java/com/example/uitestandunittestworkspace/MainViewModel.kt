package com.example.uitestandunittestworkspace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A simple ViewModel to demonstrate state testing.
 */
class MainViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    /**
     * Increments the counter.
     */
    fun increment() {
        _count.value++
    }

    /**
     * Decrements the counter, but not below 0.
     */
    fun decrement() {
        if (_count.value > 0) {
            _count.value--
        }
    }
}
