package com.example.androidudemycoupon.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class GeneralRecyclerViewAdapter<T, B : ViewDataBinding> : RecyclerView.Adapter<GeneralRecyclerViewAdapter<T, B>.ViewHolder>() {

    var data: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<B>(layoutInflater, getItemLayoutId(viewType), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        bindViewHolder(holder.binding, item)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = data.size

    abstract fun getItemLayoutId(viewType: Int): Int

    abstract fun bindViewHolder(binding: B, item: T)

    inner class ViewHolder(val binding: B) : RecyclerView.ViewHolder(binding.root)
}