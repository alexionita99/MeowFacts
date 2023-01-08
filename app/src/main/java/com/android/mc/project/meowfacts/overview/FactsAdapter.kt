package com.android.mc.project.meowfacts.overview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mc.project.meowfacts.R
import com.android.mc.project.meowfacts.database.MeowFact

// The Adapter used for the Recycler View
class FactsAdapter: RecyclerView.Adapter<FactsAdapter.ViewHolder>() {

    var data =  listOf<MeowFact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    // Bind the data for each element
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

        // Bind function, for the text area and image of each fact
        fun bind(item: MeowFact) {
            factText.text = item.fact
            pawImage.setImageResource(R.drawable.paws)
        }

        companion object {
            // Inflate the item layout
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.item_layout, parent, false)

                return ViewHolder(view)
            }
        }
    }
}