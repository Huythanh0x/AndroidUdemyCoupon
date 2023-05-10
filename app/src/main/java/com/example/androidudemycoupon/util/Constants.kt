package com.example.androidudemycoupon.util

class Constants {
    companion object {
        const val COUPON_TABLE_NAME = "coupon_table_name"
        const val COUPON_DATABASE_NAME = "coupon_database_name"
        const val BASE_URL_API = "http://huythanh0x.ddns.net:8080"

        const val DATA_STORE_NAME = "data_store_name"
        const val PREFERENCES_FETCHED_DATE_TIME = "preferences_fetched_date_time"
        const val PREFERENCES_DARK_MODE = "preferences_dark_mode"
        const val PREFERENCES_COUPON_NOTIFICATION = "preferences_coupon_notification"
        const val PREFERENCES_PREFER_CATEGORIES = "preferences_categories"
        const val PREFERENCES_PREFER_KEYWORDS = "preferences_keywords"
        const val PREFERENCES_NOTIFICATION_BY_KEYWORDS = "preferences_notification_by_keywords"
        const val PREFERENCES_NOTIFICATION_BY_CATEGORIES = "preferences_notification_by_categories"
        val DEFAULT_PREFER_KEYWORDS = setOf(
            "android", "kotlin", " python "
        )
        val DEFAULT_PREFER_CATEGORIES = setOf(
            "Development", "IT & Software", "Design"
        )
        val CATEGORIES = listOf(
            "Finance & Accounting",
            "Design",
            "Office Productivity",
            "Music",
            "Personal Development",
            "Business",
            "Teaching & Academics",
            "Language",
            "Photography & Video",
            "Marketing",
            "Health & Fitness",
            "Lifestyle",
            "Development",
            "IT & Software",
            "Test Preparation"
        )
        val LEVEL = listOf("All Levels", "Beginner", "Intermediate", "Advanced")
        val HASHMAP_RATING = mapOf(
            "No rating" to Pair(0f, 0f),
            "Above 3.5" to Pair(3.5f, 5f),
            "Above 4.0" to Pair(4f, 5f),
            "Above 4.5" to Pair(4.5f, 5f)
        )
        val HASHMAP_CONTENT_LENGTH = mapOf(
            "0 - 2 Hours" to Pair(0, 2),
            "3 - 6 Hours" to Pair(3, 6),
            "7 - 16 Hours" to Pair(7, 16),
            "17+ Hours" to Pair(17, 100)
        )
    }
}