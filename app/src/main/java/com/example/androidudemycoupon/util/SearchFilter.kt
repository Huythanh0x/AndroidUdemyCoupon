package com.example.androidudemycoupon.util

data class SearchFilter(
    val languageFilter: Boolean,
    val levelFilter: MutableSet<String>,
    val ratingFilter: String,
    val contentLengthFilter: String
)