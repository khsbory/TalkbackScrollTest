package com.example.talkbackscrolltest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Before
import org.junit.Test

class AppNavigationTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setupContent() {
        composeRule.setContent {
            TalkbackScrollApp()
        }
    }

    @Test
    fun buttons_are_shown_on_main_screen() {
        composeRule.onNodeWithTag(TestTags.MAIN_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 1-100").assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 101-200").assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 201-300").assertIsDisplayed()
    }

    @Test
    fun navigate_to_first_webview_screen_and_back() {
        composeRule.onNodeWithText("Numbers 1-100").performClick()

        composeRule.onNodeWithTag(TestTags.WEBVIEW_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 1-100").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Back").performClick()

        composeRule.onNodeWithTag(TestTags.MAIN_SCREEN).assertIsDisplayed()
    }

    @Test
    fun navigate_to_second_webview_screen_and_back() {
        composeRule.onNodeWithText("Numbers 101-200").performClick()

        composeRule.onNodeWithTag(TestTags.WEBVIEW_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 101-200").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Back").performClick()

        composeRule.onNodeWithTag(TestTags.MAIN_SCREEN).assertIsDisplayed()
    }

    @Test
    fun navigate_to_third_webview_screen_and_back() {
        composeRule.onNodeWithText("Numbers 201-300").performClick()

        composeRule.onNodeWithTag(TestTags.WEBVIEW_SCREEN).assertIsDisplayed()
        composeRule.onNodeWithText("Numbers 201-300").assertIsDisplayed()
        composeRule.onNodeWithContentDescription("Back").performClick()

        composeRule.onNodeWithTag(TestTags.MAIN_SCREEN).assertIsDisplayed()
    }
}
