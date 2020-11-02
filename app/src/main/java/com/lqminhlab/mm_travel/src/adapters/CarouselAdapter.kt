package com.lqminhlab.mm_travel.src.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.extenstions.toBitmap
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.utils.AppSizes
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
    private val sizes: AppSizes = AppSizes(view.context)
    fun bind(location: LocationModel) {
        view.image_carousel_view.layoutParams.width =
            sizes.width - ((view.context.resources.getDimension(R.dimen.padding_small) * 2).toInt())
        view.image_carousel_view.layoutParams.height = sizes.width
        val urlImage = URL(location.photo.images.large.url)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap: Bitmap? = result.await()
            if (bitmap != null)
                view.image_carousel_view.setImageBitmap(bitmap)
        }
    }
}