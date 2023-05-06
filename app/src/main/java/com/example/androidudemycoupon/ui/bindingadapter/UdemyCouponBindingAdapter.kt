package com.example.androidudemycoupon.ui.bindingadapter

import android.text.Html
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import coil.load
import com.example.androidudemycoupon.R

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


}