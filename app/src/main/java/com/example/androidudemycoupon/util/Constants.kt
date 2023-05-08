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
        val CATEGORIES = listOf(
            "Development",
            "Business",
            "Finance & Accounting",
            "IT & Software",
            "Office Productivity",
            "Personal Development",
            "Design",
            "Marketing",
            "Lifestyle",
            "Photography & Video",
            "Health & Fitness",
            "Music",
            "Teaching & Academics",
            "Language",
            "Test Preparation"
        )
    }
}