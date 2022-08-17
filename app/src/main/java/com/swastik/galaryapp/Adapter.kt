package com.swastik.galaryapp

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.view.view.*

class Adapter(private var context: Context, private var viewData: ArrayList<viewData>) : RecyclerView.Adapter<Adapter.viewholder>() {
    class viewholder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.image_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false)

        return viewholder(itemview)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val currentimgae = viewData[position]

        Glide.with(context).load(currentimgae.image)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
        holder.image.setOnClickListener{
            val intent = Intent(context,PhotoOpen::class.java)
            intent.putExtra("path",currentimgae.image)
            intent.putExtra("name",currentimgae.image_name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return viewData.size
    }
}