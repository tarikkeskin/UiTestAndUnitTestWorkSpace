package com.example.uitestandunittestworkspace

data class User(
    val id: Int,
    val name: String,
    val email: String
)

/**
 * Interface representing user data operations.
 * Demonstrates the Repository pattern for testing.
 */
interface UserRepository {
    suspend fun getUsers(): List<User>
}
