package com.example.androidudemycoupon.ui.bindingadapter

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
    fun displayNumberOfStudents(textView: androidx.appcompat.widget.AppCompatTextView, students: Int) {
        if(students <= 1){
            textView.text = "$students student"
        }else if(students > 1000){
            textView.text = "${students/1000}K students"
        }else{
            textView.text = "$students students"
        }
    }
    @BindingAdapter("displayRating")
    @JvmStatic
    fun displayRating(ratingBar: RatingBar, rating: Double) {
        ratingBar.rating = rating.toFloat()
    }

}