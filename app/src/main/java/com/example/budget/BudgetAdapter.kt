package com.example.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.BudgetItem
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

class BudgetAdapter(private var itemList: List<BudgetItem>,
                   val clickInterface: ClickInterface
                    )
    : RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BudgetViewHolder{
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent , false )
            return BudgetViewHolder(itemView)

        }
    fun filterList(filterlist: ArrayList<BudgetItem>) {
        itemList = filterlist
        notifyDataSetChanged()
    }

        override fun onBindViewHolder(holder:BudgetViewHolder, position: Int) {
            val data = itemList[position]
            val itemName = data.productName
            val price = data.productPrice

            val baseUrl = "http://192.168.0.127/ishop/media/"
            val pImage = baseUrl + data.productImage
//            val pImage = "http://192.168.0.127/ishop/media/Jean_trousers.jpg"




            holder.itemName.text = itemName
            holder.price.text = price.toString()


            Picasso.get().load(pImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fit().centerCrop()
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(holder.image)



            holder.cardView.setOnClickListener {
                clickInterface.onItemClick(itemList[position])
            }
            holder.image.setOnClickListener {
                clickInterface.onItemClick(itemList[position])
            }
        }
        override fun getItemCount(): Int {
        return itemList.size
    }
        class BudgetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)   {
            var itemName : TextView = itemView.findViewById(R.id.item_name)
            var price : TextView = itemView.findViewById(R.id.item_price)
            var cardView :CardView = itemView.findViewById(R.id.card_item)
            var image : ImageView = itemView.findViewById(R.id.imageView)
    }

    interface ClickInterface {

        fun onItemClick (budgetItem: BudgetItem)
    }

}



