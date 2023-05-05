package com.example.androidudemycoupon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidudemycoupon.model.Coupon

@Database(entities = [Coupon::class], version = 1, exportSchema = false)
abstract class CouponDatabase : RoomDatabase() {
    abstract fun couponDao(): CouponDao
}