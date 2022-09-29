package com.example.budget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BudgetAdapter (private val listItem : ArrayList<ItemModal>, val clickInterface: ClickInterface )
    : RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BudgetAdapter.BudgetViewHolder{
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent , false )
            return BudgetViewHolder(itemView)
        }
        override fun onBindViewHolder(holder:BudgetAdapter.BudgetViewHolder, position: Int) {
            holder.itemName.text = listItem[position].itemName
            holder.image.setImageResource(listItem[position].image)
            holder.price.text = listItem[position].price.toString()

            holder.image.setOnClickListener {
                clickInterface.onItemClick(listItem[position])
            }
        }

        override fun getItemCount(): Int {
        return listItem.size
    }

    class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName : TextView = itemView.findViewById(R.id.item_name)
        var price : TextView = itemView.findViewById(R.id.item_price)
        var image : ImageView = itemView.findViewById(R.id.imageView)

    }

    interface ClickInterface {
        fun onItemClick (itemModal: ItemModal)
    }

}



