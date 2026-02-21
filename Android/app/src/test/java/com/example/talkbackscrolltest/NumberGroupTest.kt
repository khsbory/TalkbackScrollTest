package com.example.talkbackscrolltest

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class NumberGroupTest {

    @Test
    fun `number groups expose expected url mapping`() {
        assertEquals(
            "https://khsruru.com/android_scroll_test/",
            NumberGroup.ONE_TO_HUNDRED.url
        )
        assertEquals(
            "https://khsruru.com/android_scroll_test/#tab2",
            NumberGroup.ONE_HUNDRED_ONE_TO_TWO_HUNDRED.url
        )
        assertEquals(
            "https://khsruru.com/android_scroll_test/#tab3",
            NumberGroup.TWO_HUNDRED_ONE_TO_THREE_HUNDRED.url
        )
    }

    @Test
    fun `route key lookup resolves all groups`() {
        NumberGroup.entries.forEach { group ->
            val parsed = NumberGroup.fromRouteKey(group.routeKey)
            assertNotNull(parsed)
            assertEquals(group, parsed)
        }
    }
}
