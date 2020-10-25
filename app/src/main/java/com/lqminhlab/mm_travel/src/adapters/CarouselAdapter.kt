package com.lqminhlab.mm_travel.src.adapters

import android.app.Activity
import android.graphics.Bitmap
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.extenstions.toBitmap
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import kotlinx.android.synthetic.main.carousel_view.view.*
import kotlinx.coroutines.*
import java.net.URL


class CarouselAdapter(val onItemClick: (position: Int, location: LocationModel) -> Unit) :
    RecyclerView.Adapter<CarouselViewHolder>() {
    private var items: List<LocationModel> = listOf()

    fun setData(data: List<LocationModel>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.carousel_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onItemClick(position, items[position])
        }
    }

    override fun getItemCount(): Int = items.size

}

class CarouselViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(location: LocationModel) {
        val displaymetrics = DisplayMetrics()
        (view.context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)

        view.image_carousel_view.layoutParams.width = displaymetrics.widthPixels
        view.image_carousel_view.layoutParams.height = displaymetrics.widthPixels
        val urlImage: URL = URL(location.photo.images.large.url)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main){
            val bitmap: Bitmap? = result.await()
            if (bitmap != null)
                view.image_carousel_view.setImageBitmap(bitmap)
        }
    }
}