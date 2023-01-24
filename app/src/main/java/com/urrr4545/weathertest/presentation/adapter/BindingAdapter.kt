package com.urrr4545.weathertest.presentation.adapter

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.urrr4545.weathertest.R

@BindingAdapter("loadingState")
fun ShimmerFrameLayout.setItemLoading(isLoading: Boolean) {
    if (isLoading) {
        View.VISIBLE
        this.startShimmer()
    } else {
        View.GONE
        this.stopShimmer()
    }
}

@BindingAdapter("isVisible")
fun View.setVisible(isVisible: Boolean) {
    if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter(
    "imgCod"
)
fun ImageView.setPositionImg(
    codStr: String
) {
    var resource = when (codStr) {
        "01d" -> R.drawable.wi_day_sunny
        "02d" -> R.drawable.wi_day_cloudy
        "03d" -> R.drawable.wi_day_cloudy_high
        "04d" -> R.drawable.wi_day_sunny_overcast
        "09d" -> R.drawable.wi_day_showers
        "10d" -> R.drawable.wi_day_rain
        "11d" -> R.drawable.wi_day_lightning
        "13d" -> R.drawable.wi_day_snow
        "50d" -> R.drawable.wi_day_haze
        "01n" -> R.drawable.wi_night_clear
        "02n" -> R.drawable.wi_night_alt_cloudy
        "03n" -> R.drawable.wi_night_alt_cloudy_high
        "04n" -> R.drawable.wi_night_partly_cloudy
        "09n" -> R.drawable.wi_night_alt_showers
        "10n" -> R.drawable.wi_night_rain
        "11n" -> R.drawable.wi_night_alt_lightning
        "13n" -> R.drawable.wi_night_alt_snow
        "50n" -> R.drawable.wi_night_fog
        else -> R.drawable.wi_day_sunny
    }

    Glide.with(context).clear(this)
    Glide.with(this).load(resource).let { request ->
        request.into(this)
    }
}
