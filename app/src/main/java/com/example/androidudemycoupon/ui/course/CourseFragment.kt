package com.example.androidudemycoupon.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.ui.MainActivity

class CourseFragment : Fragment() {
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        return inflater.inflate(R.layout.fragment_course, container, false)
    }
}