package com.example.androidudemycoupon.model

data class UdemyCouponCourse(
    val coupons: List<Coupon>,
    val ipAddress: String,
    val localTime: String
)