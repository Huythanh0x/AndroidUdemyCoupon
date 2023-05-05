package com.example.androidudemycoupon.data

import com.example.androidudemycoupon.data.local.CouponDao
import com.example.androidudemycoupon.model.Coupon
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val couponDao: CouponDao) {
    fun getAllCoupons() = couponDao.getAllCoupons()
    suspend fun insertCoupons(coupons: List<Coupon>) = couponDao.insertCoupons(coupons)
    suspend fun insertCoupon(coupon: Coupon) = couponDao.insertCoupon(coupon)
    suspend fun clearAllCoupons() = couponDao.clearAllCoupons()
}