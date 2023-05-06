package com.example.androidudemycoupon.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.ui.MainActivity

class SettingFragment : Fragment() {

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

}