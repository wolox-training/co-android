package ar.com.wolox.android.example.ui.home.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R

class ItemNewViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.image)
    val time: TextView = view.findViewById(R.id.date)
    val title: TextView = view.findViewById(R.id.title)
    val description: TextView = view.findViewById(R.id.description)
    val liked: ImageView = view.findViewById(R.id.liked)
}