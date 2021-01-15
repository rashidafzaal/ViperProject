package com.example.vipersample

class Utils {

    companion object {
        fun setApiTextAgainstSimpleText(category: String): String {
            if (category.equals(Constants.POPULAR_TEXT)) {
                return Constants.CATEGORY_POPULAR
            } else if (category.equals(Constants.TOP_RATED_TEXT)) {
                return Constants.CATEGORY_TOP_RATED
            } else if (category.equals(Constants.UPCOMING_TEXT)) {
                return Constants.CATEGORY_UPCOMING
            } else {
                return Constants.CATEGORY_POPULAR
            }
        }
    }
}