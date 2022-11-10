package com.example.budget

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.BudgetDatabase
import com.example.budget.database.BudgetItem
import com.example.budget.database.CartItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class SelectedItemsAdapter(private val cartItems: ArrayList<CartItem>, private val application:Context) :
    RecyclerView.Adapter<SelectedItemsAdapter.SelectedItemViewHolder>() {

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

        var data = cartItems[position]

        holder.itemName.text = cartItems[position].itemName

        val baseUrl = "http://192.168.0.127/ishop/media/"
        val pImage = baseUrl + data.image

        Picasso.get().load(pImage)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fit().centerCrop()
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(holder.image)

        holder.price.text = cartItems[position].price.toString()
        holder.removeButton.setOnClickListener {
            var db = BudgetDatabase.getInstance(application)
            var d :CartItem = cartItems.get(holder.adapterPosition)
            db.budgetDao().deleteItem(d)
            var position = holder.adapterPosition
            cartItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,cartItems.size)
        }


    }

    class SelectedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemName: TextView = itemView.findViewById(R.id.itemName)
        var price: TextView = itemView.findViewById(R.id.itemPrice)
        var image: ImageView = itemView.findViewById(R.id.item_image)

        var removeButton: Button = itemView.findViewById(R.id.remove)

    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


}

