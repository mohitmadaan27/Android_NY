package com.android_demo.ui.mainscrenn.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android_demo.R
import com.android_demo.model.DataModel
import com.android_demo.ui.detailscreen.DetailActivity
import com.android_demo.ui.mainscrenn.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*


/**
 * ListAdapter
 * @desc - View for displaying data in recycler view
 * @param listData - List of items to display
 * */
class ListAdapter(
    private var mCtx: Context,
    private var listData: List<DataModel.Result?>,
    private var model: MainViewModel
) :
    RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.listview_layout, parent, false)
        return ListViewHolder(view)

    }

    /*Set data in view*/
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]

        holder.tvTitle.text = data?.title
        holder.tvDescription.text = data?.source
        holder.tvDate.text = data?.publishedDate
//        https:\/\/static01.nyt.com\/images\/2019\/12\/16\/opinion\/16webster\/merlin_163910355_fa50244e-1882-4add-bad1-ea314264bba0-thumbStandard.jpg
        /*Images url not working in json data, So displaying placeholder*/
//        val url = data?.url
        Glide.with(mCtx)
            .load("")
            .placeholder(R.mipmap.ic_launcher_round)
            .apply(RequestOptions.circleCropTransform())//load image in circular shape
            .into(holder.ivAvatar)
        holder.itemView.setOnClickListener {
            onDetailScreen(data?.url)
        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.title)
        var tvDescription: TextView = itemView.findViewById(R.id.description)
        var tvDate: TextView = itemView.findViewById(R.id.date)
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)

    }



    private fun onDetailScreen(url: String?) {
        mCtx.startActivity(Intent(mCtx, DetailActivity::class.java).putExtra("url", url))
    }


}

