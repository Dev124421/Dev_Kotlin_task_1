package com.example.task_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Product(
    val id: String,
    val name: String,
    val unit: String,
    val price: String,
    val imageResId: Int
)

class ProductAdapter(
    private val productList: List<Product>,
    private val onProductClick: (Product) -> Unit,
    private val onAddClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProductImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
        val tvProductUnit: TextView = itemView.findViewById(R.id.tvProductUnit)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
        val btnAddProduct: ImageView = itemView.findViewById(R.id.btnAddProduct)
        val cardProduct: View = itemView.findViewById(R.id.cardProduct)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.ivProductImage.setImageResource(item.imageResId)
        holder.tvProductName.text = item.name
        holder.tvProductUnit.text = item.unit
        holder.tvProductPrice.text = item.price

        holder.cardProduct.setOnClickListener { onProductClick(item) }
        holder.btnAddProduct.setOnClickListener { onAddClick(item) }
    }

    override fun getItemCount(): Int = productList.size
}