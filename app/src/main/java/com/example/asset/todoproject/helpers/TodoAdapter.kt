package com.example.asset.todoproject.helpers

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asset.todoproject.R
import com.example.asset.todoproject.models.Todo
import kotlinx.android.synthetic.main.item_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAdapter(var context: Context, var list: ArrayList<Todo>, var listener: ButtonPressedListener): RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)

        val vh = ViewHolder(v)

        return vh
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItemById(position: Int): Todo{
        return list.get(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.todoTitle.text = list.get(position).description
        val date = Date(list.get(position).planDate)
        holder.itemView.date.text = dateFormat.format(date)
        holder.itemView.todoTag.text = list.get(position).tagId
        holder.itemView.priority.text = list.get(position).priority
        holder.itemView.delete.setOnClickListener {
            listener.onCLick("delete", position)
        }
        holder.itemView.edit.setOnClickListener {
            listener.onCLick("edit", position)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}