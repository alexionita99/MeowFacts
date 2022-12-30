package com.android.mc.project.meowfacts.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mc.project.meowfacts.R

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    var data =  listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val factText: TextView = itemView.findViewById(R.id.fact)
        val pawImage: ImageView = itemView.findViewById(R.id.paw_image)

        fun bind(item: String) {
            factText.text = item
            pawImage.setImageResource(R.drawable.paws)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_layout, parent, false)

                return ViewHolder(view)
            }
        }
    }
}