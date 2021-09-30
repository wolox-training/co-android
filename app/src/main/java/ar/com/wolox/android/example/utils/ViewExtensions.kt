package ar.com.wolox.android.example.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import ar.com.wolox.android.R
import com.bumptech.glide.Glide
import org.ocpsoft.prettytime.PrettyTime
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Toggles [View] visibility, according to [visible], between [View.VISIBLE] and [View.INVISIBLE].
 */
fun View.toggleVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

/**
 * Toggles [View] visibility, according to [present], between [View.VISIBLE] and [View.GONE].
 */
fun View.togglePresence(present: Boolean) {
    visibility = if (present) View.VISIBLE else View.GONE
}

const val TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
fun String.abbreviationDayFormat(): String = PrettyTime().format(Date(SimpleDateFormat(TIME_FORMAT).parse(this).time))

fun ImageView.glideImage(url: String) =
    Glide.with(this).load(url)
        .placeholder(ColorDrawable(Color.GRAY))
        .error(R.drawable.wolox_logo)
        .into(this)
