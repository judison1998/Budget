package com.example.budget


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budget.database.BudgetItem
import com.example.budget.databinding.ItemBinding

class BudgetAdapter(val onClickListener: OnClickListener) :
    ListAdapter<BudgetItem, BudgetAdapter.BudgetViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<BudgetItem>() {
        override fun areItemsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BudgetItem, newItem: BudgetItem): Boolean {
            return oldItem.productID == newItem.productID
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BudgetAdapter.BudgetViewHolder {
        return BudgetViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: BudgetAdapter.BudgetViewHolder, position: Int) {
        val budgetItem = getItem(position)
        holder.bind(budgetItem)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(budgetItem)
        }
    }

    class BudgetViewHolder(private var binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(budgetItem: BudgetItem) {
            binding.myItem = budgetItem
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (budgetItem: BudgetItem) -> Unit) {
        fun onClick(budgetItem: BudgetItem) = clickListener(budgetItem)
    }
}




