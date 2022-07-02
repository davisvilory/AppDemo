package com.vilocorp.appdemo.demojson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vilocorp.appdemo.databinding.ItemFirebaseBinding
import com.vilocorp.appdemo.objetos.FireBaseItem

class FirebaseItemAdapter(
    private val items: MutableList<FireBaseItem>,
    val itemClick: (item: FireBaseItem) -> Unit) :
    RecyclerView.Adapter<FirebaseItemAdapter.VHThis>() {

    private lateinit var parent: ViewGroup
    override fun onCreateViewHolder(p: ViewGroup, viewType: Int): VHThis {
        parent = p
        val binding = ItemFirebaseBinding.inflate(LayoutInflater.from(p.context), p, false)
        return VHThis(binding)
    }

    override fun onBindViewHolder(holder: VHThis, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            itemClick(items[position])
        }
    }

    override fun getItemCount() = items.size

    inner class VHThis(private val binding: ItemFirebaseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FireBaseItem) {
            with(binding) {
                listItemText.text = item.firebase_screen
                Glide.with(parent.context).load(item.imagen).into(listItemIcon)
            }
        }
    }

    fun updateList(newItems: List<FireBaseItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}