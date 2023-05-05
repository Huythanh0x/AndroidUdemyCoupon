package com.example.androidudemycoupon.data.remote

import com.example.androidudemycoupon.model.UdemyCouponCourse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CouponApi {
    @GET("/fetch/all")
    suspend fun fetchAllCoupons(): Response<UdemyCouponCourse>

    @GET("/search/{query}")
    suspend fun searchCourseCoupon(@Path("query") query: String): Response<UdemyCouponCourse>
}