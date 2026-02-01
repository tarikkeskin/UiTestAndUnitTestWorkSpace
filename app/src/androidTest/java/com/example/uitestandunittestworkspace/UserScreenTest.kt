package com.example.uitestandunittestworkspace

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

class UserScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingState_showsCircularProgress() {
        // Given a repository that delays indefinitely
        val fakeRepository = FakeUserRepository()
        val viewModel = UserViewModel(fakeRepository)

        composeTestRule.setContent {
            UserScreen(viewModel = viewModel)
        }

        // Then loading indicator should be visible
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }

    @Test
    fun successState_showsUserList() {
        // Given a repository that returns success immediately (using small delay or advance time)
        val fakeRepository = FakeUserRepository()
        val viewModel = UserViewModel(fakeRepository)

        composeTestRule.setContent {
            UserScreen(viewModel = viewModel)
        }

        // Wait for Loading to finish (FakeUserRepository has 1s delay)
        composeTestRule.waitUntil(2000) {
            composeTestRule.onAllNodesWithTag("user_list").fetchSemanticsNodes().isNotEmpty()
        }

        // Then user list should be displayed
        composeTestRule.onNodeWithTag("user_list").assertIsDisplayed()
    }

    @Test
    fun errorState_showsErrorMessage() {
        // Given a repository that fails
        val fakeRepository = FakeUserRepository().apply { setReturnError(true) }
        val viewModel = UserViewModel(fakeRepository)

        composeTestRule.setContent {
            UserScreen(viewModel = viewModel)
        }

        // Wait for Loading to finish
        composeTestRule.waitUntil(5000) {
            composeTestRule.onAllNodesWithTag("error_container").fetchSemanticsNodes().isNotEmpty()
        }

        // Then error container and "Retry" button should be visible
        composeTestRule.onNodeWithTag("error_container").assertIsDisplayed()
        composeTestRule.onNodeWithText("Retry").assertIsDisplayed()
    }
}
