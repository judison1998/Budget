package com.example.budget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.CartItem

class SelectedItemsAdapter( private val cartItems: ArrayList<CartItem>, private val context: Context)
    : RecyclerView.Adapter<SelectedItemsAdapter.SelectedItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup,  viewType: Int ): SelectedItemsAdapter.SelectedItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.budget_list_item, parent, false)
        return SelectedItemsAdapter.SelectedItemViewHolder(itemView)

    }
    override fun onBindViewHolder(  holder: SelectedItemsAdapter.SelectedItemViewHolder, position: Int ) {

        holder.itemName.text = cartItems[position].itemName

        holder.image.setImageResource(cartItems[position].image)

        holder.price.text = cartItems[position].price.toString()
    }

    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName : TextView = itemView.findViewById(R.id.itemName)
        var price : TextView = itemView.findViewById(R.id.itemPrice)
        var image : ImageView = itemView.findViewById(R.id.item_image)

    }
    override fun getItemCount(): Int {
        return cartItems.size
    }


}