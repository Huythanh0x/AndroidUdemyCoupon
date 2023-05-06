package com.example.androidudemycoupon.ui.adapter

import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.databinding.CouponItemRowBinding
import com.example.androidudemycoupon.model.Coupon

class CouponRecyclerAdapter(val onClickItem: (coupon: Coupon) -> Unit) :
    GeneralRecyclerViewAdapter<Coupon, CouponItemRowBinding>() {

    override fun getItemLayoutId(viewType: Int): Int = R.layout.coupon_item_row

    override fun bindViewHolder(binding: CouponItemRowBinding, item: Coupon) {
        binding.coupon = item
        binding.root.setOnClickListener{
            onClickItem(item)
        }
    }
}