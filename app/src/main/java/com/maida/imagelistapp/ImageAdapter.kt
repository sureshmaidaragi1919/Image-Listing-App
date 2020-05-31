package com.maida.imagelistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maida.imagelistapp.database.ImageEntity

class ImageAdapter(private val context: Context) :
    RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var imagePathList = emptyList<ImageEntity>() // Cached copy of words

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagePathView: ImageView = itemView.findViewById(R.id.row_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(imagePathList[position].path).into(holder.imagePathView)

    }

    internal fun setImagePath(imagePathList: List<ImageEntity>) {
        this.imagePathList = imagePathList
        notifyDataSetChanged()
    }

    override fun getItemCount() = imagePathList.size

}