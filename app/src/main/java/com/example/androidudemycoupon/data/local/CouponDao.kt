package com.example.androidudemycoupon.data.local

import androidx.lifecycle.LiveData
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
    fun getAllCoupons(): LiveData<List<Coupon>>

    @Query("SELECT * FROM ${Constants.COUPON_TABLE_NAME} WHERE LOWER(title) LIKE '%' || :searchQuery || '%'")
    suspend fun queryCoupons(searchQuery: String): List<Coupon>

    @Query("DELETE FROM ${Constants.COUPON_TABLE_NAME}")
    suspend fun clearAllCoupons()
}