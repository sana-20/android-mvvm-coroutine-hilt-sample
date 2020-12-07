package com.shannon.moviemvvm.utils

import android.view.View


/** makes gone a view. */
fun View.visible(isVisible: Boolean) {
    visibility = if(isVisible) View.VISIBLE else View.GONE
}
