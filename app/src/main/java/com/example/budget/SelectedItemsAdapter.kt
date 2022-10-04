package com.example.budget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectedItemsAdapter(
    private val selectedItems: ArrayList<ItemModal>,
    private val context: Context
) : RecyclerView.Adapter<SelectedItemsAdapter.SelectedItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedItemsAdapter.SelectedItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.budget_list_item, parent, false)
        return SelectedItemsAdapter.SelectedItemViewHolder(itemView)

    }

    override fun onBindViewHolder(
        holder: SelectedItemsAdapter.SelectedItemViewHolder,
        position: Int
    ) {

    }

    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun getItemCount(): Int {
        return selectedItems.size
    }


}