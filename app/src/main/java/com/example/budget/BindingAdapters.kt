
package com.example.budget
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.budget.database.BudgetItem


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<BudgetItem>?) {
    val adapter = recyclerView.adapter as BudgetAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.fb_icon)
                .error(R.drawable.email))
            .into(imgView)
    }
}


