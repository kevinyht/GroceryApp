package com.kevinyou.groceryapp.activities

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevinyou.groceryapp.R
import com.kevinyou.groceryapp.model.Product
import com.kevinyou.groceryapp.utils.Constants.TAG
import kotlinx.android.synthetic.main.layout_recycler_item.view.*

class ShopViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val addButton = itemView.btn_add
    private val name = itemView.rv_name
    private val profile = itemView.rv_img
    private val price = itemView.rv_price

    fun bind(myModel: Product){
        Log.d(TAG, "MyViewHolder - bind() is called")
        name.text = myModel.product_name
        price.text = myModel.price.toString()

        Glide
            //원래는 context가 들어가는데 여기에는 안들어가서
            // App.kt 만들어서 global하게 만들어서 App.instance를 씀 (싱글턴패턴)
            .with(App.instance)
            .load(myModel.url)
            .placeholder(R.mipmap.ic_launcher)
            .into(profile)
    }
}
