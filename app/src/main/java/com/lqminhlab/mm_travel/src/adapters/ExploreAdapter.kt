package com.lqminhlab.mm_travel.src.adapters

import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.extenstions.margin
import com.lqminhlab.mm_travel.src.extenstions.toBitmap
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.utils.AppSizes
import kotlinx.android.synthetic.main.explore_view.view.*
import kotlinx.android.synthetic.main.loading_normal.view.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.URL

class ExploreAdapter(
    val orientation: Int,
    val onItemClick: (position: Int, locationModel: LocationModel) -> Unit
) : RecyclerView.Adapter<ExploreViewHolder>() {

    private var items: List<LocationModel> = listOf()
    var isLarge: Boolean = true
    var width: Int? = null
    var height: Int? = null

    fun setData(data: List<LocationModel>) {
        items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.explore_view, parent, false), width, height, isLarge
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

class ExploreViewHolder(
    private val view: View,
    private val width: Int?,
    private val height: Int?,
    private val isLarge: Boolean = true
) :
    RecyclerView.ViewHolder(view) {
    private val sizes: AppSizes = AppSizes(view.context)
    fun bind(location: LocationModel, orientation: Int) {
        if (orientation == ExploreAdapter.VERTICAL)
            view.margin(
                right = view.context.resources.getDimension(R.dimen.padding_very_small)
            )
        else view.margin(
            bottom = view.context.resources.getDimension(R.dimen.padding_very_small)
        )
        view.image_explore_view.layoutParams.width = width
            ?: if (orientation == ExploreAdapter.HORIZONTAL) sizes.width else (sizes.width / 2.25).toInt()
        view.image_explore_view.layoutParams.height = height
            ?: if (orientation == ExploreAdapter.HORIZONTAL) sizes.width / 3 else sizes.width * 2 / 3
        view.text_title_explore_view.text =
            if (location.name.length < 16) location.name else "${location.name.substring(0, 14)}.."
        try {
            val urlImage =
                URL(
                    if (isLarge) location.photo.images.large?.url
                        ?: location.photo.images.original?.url
                    else location.photo.images.medium?.url
                )
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
        } catch (e: Exception) {
            Log.e("ERRRRRR:", "$e")
        }
    }
}