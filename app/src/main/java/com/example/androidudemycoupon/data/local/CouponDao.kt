package com.example.androidudemycoupon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidudemycoupon.model.Coupon
import com.example.androidudemycoupon.util.Constants

@Dao
interface CouponDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoupon(coupon: Coupon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoupons(coupons: List<Coupon>)

    @Query("SELECT * FROM ${Constants.COUPON_TABLE_NAME}")
    suspend fun getAllCoupons(): List<Coupon>

    @Query("SELECT * FROM ${Constants.COUPON_TABLE_NAME} WHERE LOWER(title) LIKE '%' || :searchQuery || '%'")
    suspend fun queryCoupons(searchQuery: String): List<Coupon>

    @Query("DELETE FROM ${Constants.COUPON_TABLE_NAME}")
    suspend fun clearAllCoupons()

    @Query("SELECT * FROM ${Constants.COUPON_TABLE_NAME} WHERE language LIKE '%' || :language || '%' AND level IN (:level) AND rating <= :upperRating AND rating >= :lowerRating AND contentLength >= :lowerContentLength AND contentLength <= :upperContentLength")
    suspend fun getFilteredCoupons(
        language: String,
        level: List<String>,
        lowerRating: Double,
        upperRating: Double,
        lowerContentLength: Int,
        upperContentLength: Int
    ): List<Coupon>
}