package ar.com.wolox.android.example.utils

import android.view.View

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