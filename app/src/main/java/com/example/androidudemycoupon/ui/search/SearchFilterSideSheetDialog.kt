package com.example.androidudemycoupon.ui.search

import android.content.Context
import androidx.core.view.children
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.util.Constants
import com.example.androidudemycoupon.util.SearchFilter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.sidesheet.SideSheetDialog


class SearchFilterSideSheetDialog(context: Context) : SideSheetDialog(context) {
    private lateinit var languageChipGroup: ChipGroup
    private lateinit var levelChipGroup: ChipGroup
    private lateinit var ratingChipGroup: ChipGroup
    private lateinit var contentLengthChipGroup: ChipGroup

    private var currentFilterAreAllLanguages = true
    private val currentFilterLevelList = Constants.LEVEL.toMutableSet()
    private var currentFilterRating = "Above 4.5"
    private var currentContentLength = "0 - 2 Hours"

    fun initState() {
        this.setContentView(R.layout.filter_side_sheet_dialog)
        languageChipGroup = this.findViewById(R.id.language_filter_container)!!
        levelChipGroup = this.findViewById(R.id.level_filter_container)!!
        ratingChipGroup = this.findViewById(R.id.rating_filter_container)!!
        contentLengthChipGroup = this.findViewById(R.id.content_length_filter_container)!!

        this.findViewById<Chip>(R.id.chip_english)?.apply {
            isCheckable = false
        }
        this.findViewById<Chip>(R.id.chip_all_languages)?.apply {
            isChecked = true
        }
    }

    fun onChipCheckListener(callback: (searchFilter: SearchFilter) -> Unit) {
        this.findViewById<Chip>(R.id.chip_all_languages)?.apply {
            this.setOnCheckedChangeListener { _, isChecked ->
                currentFilterAreAllLanguages = isChecked
                callback(getCurrentSearchFilter())
            }
        }

        for (chip in levelChipGroup.children) {
            (chip as Chip).apply {
                setOnCheckedChangeListener { view, isChecked ->
                    if (isChecked) {
                        currentFilterLevelList.add(view.text.toString())
                        callback(getCurrentSearchFilter())
                    } else if (currentFilterLevelList.size > 1) {
                        currentFilterLevelList.remove(view.text.toString())
                        callback(getCurrentSearchFilter())
                    }
                }
            }
        }

        for (chip in ratingChipGroup.children) {
            (chip as Chip).apply {
                setOnCheckedChangeListener { view, isChecked ->
                    if (isChecked) {
                        currentFilterRating = view.text.toString()
                        callback(getCurrentSearchFilter())
                    }
                }
            }
        }

        for (chip in contentLengthChipGroup.children) {
            (chip as Chip).apply {
                setOnCheckedChangeListener { view, isChecked ->
                    if (isChecked) {
                        currentContentLength = view.text.toString()
                        callback(getCurrentSearchFilter())
                    }
                }
            }
        }
    }

    fun loadDefaultFilter(callback: (searchFilter: SearchFilter) -> Unit) {
        resetFilterValue()
        callback(getCurrentSearchFilter())
    }

    private fun resetFilterValue() {
        currentFilterAreAllLanguages = true
        currentFilterLevelList.clear()
        currentFilterLevelList.addAll(Constants.LEVEL.toMutableSet())
        currentFilterRating = "Above 4.5"
        currentContentLength = "0 - 2 Hours"
    }

    private fun getCurrentSearchFilter(): SearchFilter {
        return SearchFilter(
            currentFilterAreAllLanguages,
            currentFilterLevelList,
            currentFilterRating,
            currentContentLength
        )
    }
}