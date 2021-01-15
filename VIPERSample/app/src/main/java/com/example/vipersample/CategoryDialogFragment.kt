package com.example.vipersample

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class CategoryDialogFragment: DialogFragment() {

    private var categories: Array<String>? = arrayOf(Constants.POPULAR_TEXT, Constants.TOP_RATED_TEXT, Constants.UPCOMING_TEXT)
    private var title: String? = "Select Category"
    private var categorySelectListener: CategorySelectListener? = null

    companion object {
        fun newInstance(): CategoryDialogFragment? {
            return CategoryDialogFragment()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setItems(categories) { dialog, which ->
                if (categorySelectListener != null) categorySelectListener!!.onCategorySelected(
                    categories!![which]
                )
            }
        return builder.create()
    }

    fun setCategorySelectListener(categorySelectListener: CategorySelectListener?) {
        this.categorySelectListener = categorySelectListener
    }

    interface CategorySelectListener {
        fun onCategorySelected(category: String?)
    }
}