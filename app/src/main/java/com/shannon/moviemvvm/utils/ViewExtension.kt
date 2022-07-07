package com.shannon.moviemvvm.utils

import android.view.View

fun View.viewVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}