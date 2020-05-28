package com.ecu.ecufloodapp.view.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ecu.ecufloodapp.R
import com.ecu.ecufloodapp.model.WellData
import kotlinx.android.synthetic.main.well_list_item.view.*
import org.w3c.dom.Text

class WellItemsAdapter(private var well_items: ArrayList<WellData>) :
    RecyclerView.Adapter<WellItemsAdapter.ViewHolder>() {

    fun updateList(list_items: List<WellData>) {
        well_items.clear()
        well_items.addAll(list_items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.well_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = well_items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.well_id.text = well_items[position].id.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val well_id = itemView.findViewById<TextView>(R.id.list_item_id)
    }
}