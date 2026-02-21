package com.example.talkbackscrolltest

enum class NumberGroup(
    val routeKey: String,
    val title: String,
    val url: String
) {
    ONE_TO_HUNDRED(
        routeKey = "group1",
        title = "1 to 100",
        url = "https://khsruru.com/android_scroll_test/"
    ),
    ONE_HUNDRED_ONE_TO_TWO_HUNDRED(
        routeKey = "group2",
        title = "101 to 200",
        url = "https://khsruru.com/android_scroll_test/#tab2"
    ),
    TWO_HUNDRED_ONE_TO_THREE_HUNDRED(
        routeKey = "group3",
        title = "201 to 300",
        url = "https://khsruru.com/android_scroll_test/#tab3"
    );

    companion object {
        fun fromRouteKey(routeKey: String): NumberGroup? = entries.firstOrNull { it.routeKey == routeKey }
    }
}
