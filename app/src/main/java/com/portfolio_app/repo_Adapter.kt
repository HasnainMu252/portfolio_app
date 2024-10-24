package com.portfolio_app

import Repo_data
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class repo_Adapter(val context: Context, private val repo_list: List<Repo_data>) : RecyclerView.Adapter<repo_Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repoID: TextView = itemView.findViewById(R.id.TheID)
        val nameTextView: TextView = itemView.findViewById(R.id.RepoTitle)
        val descTextView: TextView = itemView.findViewById(R.id.description)
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = repo_list[position]

        holder.repoID.text = current.id.toString()
        holder.nameTextView.text = current.name
        holder.descTextView.text = current.description ?: "No description available"

        // Optional: Add click listener for each item
        holder.itemView.setOnClickListener {
            // Handle item click, e.g., open repo details
        }
    }

    override fun getItemCount(): Int {
        return repo_list.size
    }
}
