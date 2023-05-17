package com.example.androidudemycoupon.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.androidudemycoupon.model.Coupon
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CouponDaoTest {

    private lateinit var database: CouponDatabase
    private lateinit var couponDao: CouponDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), CouponDatabase::class.java
        ).allowMainThreadQueries().build()
        couponDao = database.couponDao()
    }


    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertCoupon() = runBlocking {
        val coupon = Coupon(
            courseId = 477958,
            author = "Paul Ashun",
            category = "Business",
            contentLength = 68,
            couponCode = "150523_FREE",
            couponUrl = "https://www.udemy.com/course/userstory/?couponCode=150523_FREE",
            description = "<p></p>",
            expiredDate = "2023-05-20 06:43:00+00:00",
            heading = "Complete overview of requirements, user story template, epics, splitting, acceptance criteria +business analyst examples",
            language = "English",
            title = "User Stories for Agile Scrum+Product Owner+Business Analysis",
            previewVideo = "/course/477958/preview/?startPreviewId=3608664",
            rating = 3.86484,
            reviews = 2324,
            students = 56740,
            subCategory = "Unknown",
            usesRemaining = 221,
            previewImage = "https://img-c.udemycdn.com/course/750x422/477958_ae48.jpg",
            level = "All Levels"
        )
        couponDao.insertCoupon(coupon)
        val allCoupons = couponDao.getAllCoupons()
        assert(allCoupons.contains(coupon))
    }

    @Test
    fun queryAllCoupons() = runBlocking {
        val coupon1 = Coupon(
            courseId = 477958,
            author = "Paul Ashun",
            category = "Business",
            contentLength = 68,
            couponCode = "150523_FREE",
            couponUrl = "https://www.udemy.com/course/userstory/?couponCode=150523_FREE",
            description = "<p></p>",
            expiredDate = "2023-05-20 06:43:00+00:00",
            heading = "Complete overview of requirements, user story template, epics, splitting, acceptance criteria +business analyst examples",
            language = "English",
            title = "User Stories for Agile Scrum+Product Owner+Business Analysis",
            previewVideo = "/course/477958/preview/?startPreviewId=3608664",
            rating = 3.86484,
            reviews = 2324,
            students = 56740,
            subCategory = "Unknown",
            usesRemaining = 221,
            previewImage = "https://img-c.udemycdn.com/course/750x422/477958_ae48.jpg",
            level = "All Levels"
        )
        val coupon2 = Coupon(
            courseId = 702338,
            author = "Yusuf Katı",
            category = "Teaching & Academics",
            contentLength = 1454,
            couponCode = "3B8340002D5EE81F15C6",
            couponUrl = "https://www.udemy.com/course/improve-english-by-movies/?couponCode=3B8340002D5EE81F15C6",
            description = "<p> </p>",
            expiredDate = "2023-05-20 09:54:00+00:00",
            heading = "Improve your English listening and speaking experience by watching and listening movies.",
            language = "English",
            title = "Improve English by Movies",
            previewVideo = "/course/702338/preview/?startPreviewId=4872434",
            rating = 4.24351,
            reviews = 2288,
            students = 110904,
            subCategory = "Unknown",
            usesRemaining = 12,
            previewImage = "https://img-b.udemycdn.com/course/750x422/702338_4e2b_5.jpg",
            level = "All Levels"
        )
        val coupon3 = Coupon(
            courseId = 1090722,
            author = "Srinidhi Ranganathan",
            category = "Marketing",
            contentLength = 83,
            couponCode = "E10BE06B9E1159766225",
            couponUrl = "https://www.udemy.com/course/viral-content-buzz-boost-your-blog-traffic-by-500/?couponCode=E10BE06B9E1159766225",
            description = "<p></p>",
            expiredDate = "2023-05-20 11:04:00+00:00",
            heading = "The tenth course in the RoboAuthor Series that focusses on 'Viral Content Buzz' to make your blog posts go viral",
            language = "English",
            title = "RoboAuthor: Content Writing Automation - Part 10",
            previewVideo = "/course/1090722/preview/?startPreviewId=25443591",
            rating = 4.0955,
            reviews = 408,
            students = 68643,
            subCategory = "Unknown",
            usesRemaining = 787,
            previewImage = "https://img-c.udemycdn.com/course/750x422/1090722_71e4_6.jpg",
            level = "Intermediate"
        )
        couponDao.insertCoupon(coupon1)
        couponDao.insertCoupon(coupon2)
        couponDao.insertCoupon(coupon3)
        val allCoupons = couponDao.getAllCoupons()
        assert(allCoupons.size == 3)
    }


    @Test
    fun searchCoupon_returnFalse() = runBlocking {
        val coupon = Coupon(
            courseId = 477958,
            author = "Paul Ashun",
            category = "Business",
            contentLength = 68,
            couponCode = "150523_FREE",
            couponUrl = "https://www.udemy.com/course/userstory/?couponCode=150523_FREE",
            description = "<p></p>",
            expiredDate = "2023-05-20 06:43:00+00:00",
            heading = "Complete overview of requirements, user story template, epics, splitting, acceptance criteria +business analyst examples",
            language = "English",
            title = "User Stories for Agile Scrum+Product Owner+Business Analysis",
            previewVideo = "/course/477958/preview/?startPreviewId=3608664",
            rating = 3.86484,
            reviews = 2324,
            students = 56740,
            subCategory = "Unknown",
            usesRemaining = 221,
            previewImage = "https://img-c.udemycdn.com/course/750x422/477958_ae48.jpg",
            level = "All Levels"
        )
        val queryKey = "Android"
        couponDao.insertCoupon(coupon)
        val foundCoupon = couponDao.queryCoupons(queryKey)
        assert(foundCoupon.isEmpty())
    }


    @Test
    fun searchCoupon_returnTrue() = runBlocking {
        val coupon = Coupon(
            courseId = 477958,
            author = "Paul Ashun",
            category = "Business",
            contentLength = 68,
            couponCode = "150523_FREE",
            couponUrl = "https://www.udemy.com/course/userstory/?couponCode=150523_FREE",
            description = "<p></p>",
            expiredDate = "2023-05-20 06:43:00+00:00",
            heading = "Complete overview of requirements, user story template, epics, splitting, acceptance criteria +business analyst examples",
            language = "English",
            title = "User Stories for Agile Scrum+Product Owner+Business Analysis",
            previewVideo = "/course/477958/preview/?startPreviewId=3608664",
            rating = 3.86484,
            reviews = 2324,
            students = 56740,
            subCategory = "Unknown",
            usesRemaining = 221,
            previewImage = "https://img-c.udemycdn.com/course/750x422/477958_ae48.jpg",
            level = "All Levels"
        )
        val queryKey = "Agile"
        couponDao.insertCoupon(coupon)
        val foundCoupon = couponDao.queryCoupons(queryKey)
        assert(foundCoupon.isNotEmpty())
    }

    @Test
    fun clearAllCoupons_returnTrue() = runBlocking {
        val coupon = Coupon(
            courseId = 477958,
            author = "Paul Ashun",
            category = "Business",
            contentLength = 68,
            couponCode = "150523_FREE",
            couponUrl = "https://www.udemy.com/course/userstory/?couponCode=150523_FREE",
            description = "<p></p>",
            expiredDate = "2023-05-20 06:43:00+00:00",
            heading = "Complete overview of requirements, user story template, epics, splitting, acceptance criteria +business analyst examples",
            language = "English",
            title = "User Stories for Agile Scrum+Product Owner+Business Analysis",
            previewVideo = "/course/477958/preview/?startPreviewId=3608664",
            rating = 3.86484,
            reviews = 2324,
            students = 56740,
            subCategory = "Unknown",
            usesRemaining = 221,
            previewImage = "https://img-c.udemycdn.com/course/750x422/477958_ae48.jpg",
            level = "All Levels"
        )
        couponDao.insertCoupon(coupon)
        couponDao.clearAllCoupons()
        val foundCoupon = couponDao.getAllCoupons()
        assert(foundCoupon.isEmpty())
    }
}