package com.haidev.newsapps.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("srcGlide")
fun ImageView.setGlideImage(imageUrl: String?) {
    imageUrl?.let {
        if (it.substringAfterLast(".").equals("svg", true))
            GlideToVectorYou.justLoadImage(
                getParentActivity(),
                Uri.parse(
                    it
                ),
                this
            )
        else
            Glide.with(context).load(it).into(this)
    }
}

@BindingAdapter("convertTime")
fun setTime(view: TextView, value: String?) {
    val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(value)

    val prettyTime = PrettyTime(Locale.US)
    val ago = prettyTime.format(Date(date.time))
    view.text = ago
}