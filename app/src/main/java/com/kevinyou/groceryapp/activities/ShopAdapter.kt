package com.kevinyou.groceryapp.activities

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevinyou.groceryapp.R
import com.kevinyou.groceryapp.model.Product

class ShopAdapter(
    private val context: Context,
    private val callback: MainActivityCallback,
): RecyclerView.Adapter<ShopViewHolder>() {
    private var modelList = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder{
        val view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_item, parent, false)
        val viewHolder = ShopViewHolder(view)

        view.setOnClickListener {
            val intent = Intent(context, ShopFragment::class.java)
            context.startActivity(intent)
        }

        viewHolder.addButton.setOnClickListener {
            val product = modelList[viewHolder.bindingAdapterPosition]
            callback.onProductAdded(product)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(this.modelList[position])
    }

    override fun getItemCount() = modelList.size

    fun submitList(modelList: List<Product>){
        this.modelList = modelList
        notifyDataSetChanged()
    }


}
