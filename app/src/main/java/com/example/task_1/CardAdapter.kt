package com.example.task_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class CartItem(
    val id: String,
    val title: String,
    val unitInfo: String,
    val unitPrice: Double,
    var quantity: Int,
    val imageRes: Int
)

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onCartUpdated: () -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val ivImage: ImageView = v.findViewById(R.id.ivCartItemImage)
        val tvTitle: TextView = v.findViewById(R.id.tvCartItemTitle)
        val tvQty: TextView = v.findViewById(R.id.tvCartItemQty)
        val tvPrice: TextView = v.findViewById(R.id.tvCartItemPrice)
        val tvCount: TextView = v.findViewById(R.id.tvItemCount)
        val btnMinus: TextView = v.findViewById(R.id.btnMinus)
        val btnPlus: TextView = v.findViewById(R.id.btnPlus)
        val btnDelete: ImageView = v.findViewById(R.id.btnDeleteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = items[position]

        holder.ivImage.setImageResource(item.imageRes)
        holder.tvTitle.text = item.title
        holder.tvQty.text = item.unitInfo
        holder.tvCount.text = item.quantity.toString()
        holder.tvPrice.text = "$${String.format("%.2f", item.unitPrice * item.quantity)}"

        holder.btnPlus.setOnClickListener {
            item.quantity++
            notifyItemChanged(holder.bindingAdapterPosition)
            onCartUpdated()
        }

        holder.btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                notifyItemChanged(holder.bindingAdapterPosition)
                onCartUpdated()
            }
        }

        holder.btnDelete.setOnClickListener {
            val currentPos = holder.bindingAdapterPosition
            if (currentPos != RecyclerView.NO_POSITION) {
                items.removeAt(currentPos)
                notifyItemRemoved(currentPos)
                notifyItemRangeChanged(currentPos, items.size - currentPos)
                onCartUpdated()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun getTotalPrice(): Double = items.sumOf { it.unitPrice * it.quantity }
}