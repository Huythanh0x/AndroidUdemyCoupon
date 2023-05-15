package com.example.androidudemycoupon.ui.setting

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.androidudemycoupon.R
import com.example.androidudemycoupon.ui.MainActivity
import com.example.androidudemycoupon.util.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : Fragment() {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).showBottomNavigation(true)
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val couponNotificationSwitch =
            view.findViewById<SwitchCompat>(R.id.new_coupon_notification_switch)
        val darkModeSwitch = view.findViewById<SwitchCompat>(R.id.dark_mode_switch)
        val notificationByCategoriesSwitch =
            view.findViewById<SwitchCompat>(R.id.new_prefer_category_coupon_notification_switch)
        val notificationByKeywordsSwitch =
            view.findViewById<SwitchCompat>(R.id.new_prefer_keyword_coupon_notification_switch)
        settingViewModel.isCouponNotification.observe(viewLifecycleOwner) {
            couponNotificationSwitch.isChecked = it
        }
        settingViewModel.isDarkMode.observe(viewLifecycleOwner) {
            darkModeSwitch.isChecked = it
        }
        settingViewModel.isNotificationByCategories.observe(viewLifecycleOwner) {
            notificationByCategoriesSwitch.isChecked = it
        }
        settingViewModel.isNotificationByKeywords.observe(viewLifecycleOwner) {
            notificationByKeywordsSwitch.isChecked = it
        }
        couponNotificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.updateCouponNotification(isChecked)
        }
        darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.updateDarkMode(isChecked)
        }
        notificationByCategoriesSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.updateNotificationByCategories(isChecked)
        }
        notificationByKeywordsSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.updateNotificationByKeywords(isChecked)
        }

        val preferCategoriesChipGroup =
            view.findViewById<ChipGroup>(R.id.prefer_categories_chip_group)
        settingViewModel.listPreferCategories.observe(viewLifecycleOwner) { preferCategories ->
            preferCategoriesChipGroup.removeAllViews()
            for (option in preferCategories) {
                val chip = Chip(requireContext())
                chip.apply {
                    text = option
                    applyChipStyle(this)
                    setOnCloseIconClickListener { view ->
                        settingViewModel.removePreferCategory((view as Chip).text.toString())
                    }
                }
                preferCategoriesChipGroup.addView(chip)
            }
            val addNewChip = Chip(requireContext())
            addNewChip.apply {
                applyAddChipStyle(this)
            }
            preferCategoriesChipGroup.addView(addNewChip)

            addNewChip.setOnClickListener {
                if (preferCategories.size < 5) {
                    val options = Constants.CATEGORIES - preferCategories
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle("Choose an option")
                    builder.setItems(options.toTypedArray()) { dialog, which ->
                        val selectedOption = options[which]
                        settingViewModel.addPreferCategory(selectedOption)
                    }
                    builder.show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "The maximum prefer categories is 5",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        val preferKeywordsChipGroup = view.findViewById<ChipGroup>(R.id.prefer_keywords_chip_group)
        settingViewModel.listPreferKeywords.observe(viewLifecycleOwner) { preferKeywords ->
            preferKeywordsChipGroup.removeAllViews()
            for (option in preferKeywords) {
                val chip = Chip(requireContext())
                chip.apply {
                    text = option
                    applyChipStyle(this)
                    setOnCloseIconClickListener { view ->
                        settingViewModel.removePreferKeyword((view as Chip).text.toString())
                    }
                }
                preferKeywordsChipGroup.addView(chip)
            }
            val addNewChip = Chip(requireContext())
            addNewChip.apply {
                applyAddChipStyle(this)
            }
            preferKeywordsChipGroup.addView(addNewChip)
            addNewChip.setOnClickListener {
                if (preferKeywords.size < 10) {
                    val editText = AppCompatEditText(requireContext())
                    val dialog =
                        AlertDialog.Builder(requireContext()).setTitle("Enter new chip text")
                            .setView(editText)
                            .setPositiveButton("OK") { _, _ ->
                                if (editText.text.toString().isNotEmpty()) {
                                    settingViewModel.addPreferKeyword(editText.text.toString())
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "The input text cannot be empty",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }.setNegativeButton("Cancel", null).create()
                    dialog.show()
                    editText.post {
                        editText.requestFocus()
                        val inputMethodManager =
                            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "The maximum prefer keywords is 10",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun applyChipStyle(chip: Chip) {
        chip.apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)
            setCloseIconResource(R.drawable.cross_icon)
            setPadding(0, 0, 0, 0)
            textEndPadding = 0F
            closeIconEndPadding = 0f
            closeIconStartPadding = 0f
            isCloseIconVisible = true
            isClickable = false
            setChipBackgroundColorResource(R.color.backgroundChip)
        }
    }

    private fun applyAddChipStyle(chip: Chip) {
        chip.apply {
            text = "Add New"
            isClickable = true
            setChipBackgroundColorResource(R.color.backgroundAddChip)
        }
    }
}