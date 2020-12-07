package com.shannon.moviemvvm.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shannon.moviemvvm.utils.Constants.POSTER_BASE_URL
import java.text.NumberFormat
import java.util.*

@BindingAdapter("bindImage")
fun ImageView.loadImage(imageURL: String?) {
    Glide.with(this)
        .load(POSTER_BASE_URL + imageURL)
        .into(this)
}


@BindingAdapter("bindCurrency")
fun setCurrency(textView: TextView, price: Long) {
    val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
    textView.text = formatCurrency.format(price)
}

