package com.example.androidudemycoupon.data

import com.example.androidudemycoupon.data.remote.CouponApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val couponApi: CouponApi) {
    suspend fun fetchAllCoupons() = couponApi.fetchAllCoupons()
    suspend fun searchCourseCoupon(query: String) = couponApi.searchCourseCoupon(query)
}