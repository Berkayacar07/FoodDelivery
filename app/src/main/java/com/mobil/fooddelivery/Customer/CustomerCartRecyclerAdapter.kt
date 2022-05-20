package com.mobil.fooddelivery.Customer


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobil.fooddelivery.R
import java.text.DecimalFormat

class CustomerCartRecyclerAdapter (private var cartList : ArrayList<FoodCart> ):
    RecyclerView.Adapter<CustomerCartRecyclerAdapter.CartFoodHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartFoodHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_row_customer_cart, parent, false)
        return CartFoodHolder(view)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    override fun onBindViewHolder(holder: CartFoodHolder, position: Int) {
        val currentItem = cartList[position]
        holder.foodName.text = currentItem.foodName
        val numberFormat = DecimalFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
        println(5)
        holder.foodPrice.text = numberFormat.format((currentItem.foodPrice.toDouble()) * (currentItem.foodCount.toInt()))
        holder.foodCount.text = currentItem.foodCount

        holder.foodPlusButton.setOnClickListener {
            holder.foodPrice.text = numberFormat.format((currentItem.foodPrice.toDouble()) * (currentItem.foodCount.toInt()+1))
            holder.foodCount.text = (currentItem.foodCount.toInt()+1).toString()
            currentItem.foodCount = (currentItem.foodCount.toInt() + 1).toString()
        }

        holder.foodMinusButton.setOnClickListener {
            if (holder.foodCount.text.toString().toInt() >= 2) {
                holder.foodPrice.text = numberFormat.format((currentItem.foodPrice.toDouble()) * (currentItem.foodCount.toInt()-1))
                holder.foodCount.text = (currentItem.foodCount.toInt() - 1).toString()
                currentItem.foodCount = (currentItem.foodCount.toInt() - 1).toString()
            }
        }
    }

    class CartFoodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodName : TextView = itemView.findViewById(R.id.textView_name_cart_page)
        val foodPrice: TextView = itemView.findViewById(R.id.textView_price_cart_page)
        val foodCount : TextView = itemView.findViewById(R.id.editText_cart_countOfFood)
        val foodPlusButton : Button = itemView.findViewById(R.id.button_cart_foodPlus)
        val foodMinusButton : Button = itemView.findViewById(R.id.button_cart_foodMinus)
    }

}