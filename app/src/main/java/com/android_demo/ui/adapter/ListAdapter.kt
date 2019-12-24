package com.android_demo.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android_demo.model.DataModel
import com.android_demo.R
import com.bumptech.glide.Glide
/**
 * ListAdapter
 * @desc - View for displaying data in recycler view
 * @param listData - List of items to display
 * */
class ListAdapter(private var mCtx: Context, private var listData: List<DataModel.Row?>) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.listview_layout, parent, false)
        return ListViewHolder(view)
    }

    /*Set data in view*/
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        Glide.with(mCtx)
            .load(data?.imageHref)
            .into(holder.ivAvatar)
        holder.tvTitle.text = data?.title
        holder.tvDiscription.text = data?.description

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvDiscription: TextView = itemView.findViewById(R.id.tv_desc)

    }
}

