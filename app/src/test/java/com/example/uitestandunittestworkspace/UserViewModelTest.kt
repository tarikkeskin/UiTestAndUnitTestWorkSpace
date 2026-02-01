package com.example.uitestandunittestworkspace

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    private val repository: UserRepository = mockk()
    private lateinit var viewModel: UserViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initialState_isLoading() = runTest {
        // We don't initialize the viewModel yet to capture the initial state
        coEvery { repository.getUsers() } coAnswers {
            testDispatcher.scheduler.advanceTimeBy(1000)
            emptyList()
        }

        viewModel = UserViewModel(repository)

        viewModel.uiState.test {
            assertEquals(UserUiState.Loading, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loadUsers_success_updatesUiStateToSuccess() = runTest {
        val users = listOf(User(1, "Alice", "alice@example.com"))
        coEvery { repository.getUsers() } returns users

        viewModel = UserViewModel(repository)

        viewModel.uiState.test {
            // Skip initial loading
            assertEquals(UserUiState.Loading, awaitItem())
            assertEquals(UserUiState.Success(users), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun loadUsers_error_updatesUiStateToError() = runTest {
        val errorMessage = "Network Error"
        coEvery { repository.getUsers() } throws Exception(errorMessage)

        viewModel = UserViewModel(repository)

        viewModel.uiState.test {
            assertEquals(UserUiState.Loading, awaitItem())
            assertEquals(UserUiState.Error(errorMessage), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
