package com.example.androidudemycoupon.di

import android.content.Context
import androidx.room.Room
import com.example.androidudemycoupon.data.local.CouponDatabase
import com.example.androidudemycoupon.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDao(couponDatabase: CouponDatabase) = couponDatabase.couponDao()

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, CouponDatabase::class.java, Constants.COUPON_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
}
