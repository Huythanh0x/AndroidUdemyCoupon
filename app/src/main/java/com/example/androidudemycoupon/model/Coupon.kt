package com.example.androidudemycoupon.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidudemycoupon.util.Constants
import kotlinx.parcelize.Parcelize


@Entity(Constants.COUPON_TABLE_NAME)
@Parcelize
data class Coupon(
    @PrimaryKey
    val courseId: Int,
    val author: String,
    val category: String,
    val contentLength: Int,
    val couponCode: String,
    val couponUrl: String,
    val description: String,
    val expiredDate: String,
    val heading: String,
    val language: String,
    val level: String,
    val previewImage: String,
    val previewVideo: String,
    val rating: Double,
    val reviews: Int,
    val students: Int,
    val subCategory: String,
    val title: String,
    val usesRemaining: Int
) : Parcelable