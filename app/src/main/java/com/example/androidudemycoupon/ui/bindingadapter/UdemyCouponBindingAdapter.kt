package com.example.androidudemycoupon.ui.bindingadapter

import android.os.Build
import android.text.Html
import android.widget.RatingBar
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import coil.load
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.util.TimeLeft

object UdemyCouponBindingAdapter {
    @BindingAdapter("loadImageFromUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: androidx.appcompat.widget.AppCompatImageView, url: String) {
        imageView.load(url) {
            crossfade(600)
            error(R.color.black)
        }
    }

    @BindingAdapter("displayNumberOfStudents")
    @JvmStatic
    fun displayNumberOfStudents(
        textView: androidx.appcompat.widget.AppCompatTextView,
        students: Int
    ) {
        if (students <= 1) {
            textView.text = "$students student"
        } else if (students > 1000) {
            textView.text = "${students / 1000}K students"
        } else {
            textView.text = "$students students"
        }
    }

    @BindingAdapter("displayNumberOfReviews")
    @JvmStatic
    fun displayNumberOfReviews(
        textView: androidx.appcompat.widget.AppCompatTextView,
        students: Int
    ) {
        if (students <= 1) {
            textView.text = "$students review"
        } else if (students > 1000) {
            textView.text = "${students / 1000}K reviews"
        } else {
            textView.text = "$students reviews"
        }
    }

    @BindingAdapter("displayRating")
    @JvmStatic
    fun displayRating(ratingBar: RatingBar, rating: Double) {
        ratingBar.rating = rating.toFloat()
    }

    @BindingAdapter("parseHTML")
    @JvmStatic
    fun parseHTML(textView: androidx.appcompat.widget.AppCompatTextView, htmlString: String) {
        textView.text = Html.fromHtml(htmlString)
    }

    @BindingAdapter("contentLength")
    @JvmStatic
    fun contentLength(textView: androidx.appcompat.widget.AppCompatTextView, contentLength: Int) {
        textView.text = when (contentLength) {
            in 2..60 -> "$contentLength minutes"
            in 0..1 -> "$contentLength minute"
            in 60..Int.MAX_VALUE -> "${"%.1f".format(contentLength.toFloat() / 60)} hours"
            else -> "undefined"
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    @BindingAdapter("timeLeft")
    @JvmStatic
    fun timeLeft(textView: androidx.appcompat.widget.AppCompatTextView, expiredDate: String) {
        textView.text = when (TimeLeft.getDurationFromNow(expiredDate)) {
            in 0..1 -> "${TimeLeft.getDurationFromNow(expiredDate)} minute"
            in 2..2 * 60 -> "${TimeLeft.getDurationFromNow(expiredDate)} minutes"
            in 2 * 60..2 * 60 * 24 -> "${TimeLeft.getDurationFromNow(expiredDate) / 60} hours"
            in 2 * 60 * 24..30 * 60 * 24 -> "${TimeLeft.getDurationFromNow(expiredDate) / 60 / 24} days"
            else -> "${TimeLeft.getDurationFromNow(expiredDate)} minutes"
        }
    }
}