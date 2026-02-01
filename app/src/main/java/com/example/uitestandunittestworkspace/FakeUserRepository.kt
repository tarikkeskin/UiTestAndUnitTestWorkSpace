package com.example.uitestandunittestworkspace

import kotlinx.coroutines.delay

/**
 * A fake implementation of [UserRepository] for testing and demonstration.
 * Simulates network latency and allows for error injection.
 */
class FakeUserRepository : UserRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getUsers(): List<User> {
        // Simulate network delay
        delay(1000)

        if (shouldReturnError) {
            throw Exception("Network Error")
        }

        return listOf(
            User(1, "Alice", "alice@example.com"),
            User(2, "Bob", "bob@example.com"),
            User(3, "Charlie", "charlie@example.com")
        )
    }
}
