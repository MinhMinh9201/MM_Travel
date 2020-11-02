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
import kotlinx.android.synthetic.main.explore_view.view.*
import kotlinx.android.synthetic.main.loading_normal.view.*
import kotlinx.coroutines.*
import java.net.URL

class ExploreAdapter(
    val orientation: Int,
    val onItemClick: (position: Int, locationModel: LocationModel) -> Unit
) : RecyclerView.Adapter<ExploreViewHolder>() {

    private var items: List<LocationModel> = listOf()

    fun setData(data: List<LocationModel>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.explore_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(items[position], orientation)
        holder.itemView.setOnClickListener {
            onItemClick(position, items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    companion object {
        const val HORIZONTAL: Int = 0
        const val VERTICAL: Int = 1
    }
}

class ExploreViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val sizes: AppSizes = AppSizes(view.context)
    fun bind(location: LocationModel, orientation: Int) {
        view.image_explore_view.layoutParams.width =
            if (orientation == ExploreAdapter.HORIZONTAL) sizes.width * 2 / 3 else sizes.width / 3
        view.image_explore_view.layoutParams.height =
            if (orientation == ExploreAdapter.HORIZONTAL) sizes.width / 3 else sizes.width * 2 / 3
        val urlImage = URL(location.photo.images.large.url)
        val result: Deferred<Bitmap?> = GlobalScope.async {
            urlImage.toBitmap()
        }
        GlobalScope.launch(Dispatchers.Main) {
            view.loading_normal.visibility = View.VISIBLE
            val bitmap: Bitmap? = result.await()
            if (bitmap != null && view.image_explore_view != null)
                view.image_explore_view.setImageBitmap(bitmap)
            view.loading_normal.visibility = View.GONE
        }
    }
}