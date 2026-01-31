package com.example.uitestandunittestworkspace

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

/**
 * UI Tests for [MainActivity] using Compose Test Rule.
 */
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initialState_showsZeroCount() {
        // Assert that the text "Count: 0" exists
        composeTestRule.onNodeWithTag("counter_text")
            .assertTextEquals("Count: 0")
    }

    @Test
    fun clickingIncrement_increasesCount() {
        // Find the button and click it
        composeTestRule.onNodeWithTag("increment_button")
            .performClick()

        // Assert that the count text is updated to "Count: 1"
        composeTestRule.onNodeWithTag("counter_text")
            .assertTextEquals("Count: 1")
    }

    @Test
    fun clickingIncrementMultipleTimes_increasesCountAccordingly() {
        // Click the button 3 times
        val incrementButton = composeTestRule.onNodeWithTag("increment_button")
        repeat(3) {
            incrementButton.performClick()
        }

        // Assert count is 3
        composeTestRule.onNodeWithTag("counter_text")
            .assertTextEquals("Count: 3")
    }
}
